package com.baway.day13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.baway.day13.adapter.MyBase;
import com.baway.day13.bean.UserBean;
import com.baway.day13.utils.HttpUrl;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView pull;
    private String path="http://172.17.8.100/small/commodity/v1/commodityList?page=";
    private int pager=1;
    private MyBase myBase;
    private  List<UserBean.Result.Mlss.Mydata> list=new ArrayList<>();
    private List<UserBean.Result.Mlss.Mydata> commodityList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFrom();
        getDataFrom();
    }
    public void initFrom(){
        pull=findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout loadingLayoutProxy = pull.getLoadingLayoutProxy(true, false);
        loadingLayoutProxy.setPullLabel("上拉加载");
        loadingLayoutProxy.setReleaseLabel("松开加载");
        loadingLayoutProxy.setRefreshingLabel("正在刷新");
        ILoadingLayout Proxy = pull.getLoadingLayoutProxy(false, true);
        Proxy.setPullLabel("上拉加载");
        Proxy.setReleaseLabel("松开加载");
        Proxy.setRefreshingLabel("正在刷新");

        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                 pager=1;
                 getDataFrom();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                 pager++;
                 getDataFrom();
            }
        });
        myBase=new MyBase(list,MainActivity.this);
        pull.setAdapter(myBase);

    }
    public void getDataFrom(){
        HttpUrl httpUrl=HttpUrl.getInstance();
        String preams=path+pager;
        httpUrl.getAsyncTask(preams);
        httpUrl.getCallBack(new HttpUrl.CallBack() {
            @Override
            public void getData(String json) {
                Log.i("sss",json);
                Gson gson=new Gson();
                UserBean userBean = gson.fromJson(json, UserBean.class);
                UserBean.Result result = userBean.getResult();
                UserBean.Result.Mlss mlss = result.getMlss();
                 commodityList = mlss.getCommodityList();
                 Log.i("ttt",commodityList.size()+"");
                if (pager==1){
                      list.clear();
                }
                list.addAll(commodityList);
                myBase.notifyDataSetChanged();
                pull.onRefreshComplete();


            }
        });
    }




}
