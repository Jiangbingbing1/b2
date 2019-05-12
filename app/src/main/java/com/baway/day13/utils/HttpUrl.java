package com.baway.day13.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUrl {
    private static final HttpUrl ourInstance = new HttpUrl();
    private CallBack callBack;

    public static HttpUrl getInstance() {
        return ourInstance;
    }

    private HttpUrl() {
    }

    public void getAsyncTask(final String path){
        AsyncTask<String,Void,String> asyncTask=new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String s = SystemFromData(inputStream);
                        return s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                 callBack.getData(s);
            }
        }.execute(path);
    }

    public interface CallBack{
        void getData(String json);
    }
    public void getCallBack(CallBack callBack){
         this.callBack=callBack;
    }

    public String SystemFromData(InputStream inputStream) throws IOException {
           int len=-1;
           byte[] bytes=new byte[1024];
           StringBuffer stringBuffer=new StringBuffer();
           while ((len=inputStream.read(bytes))!=-1){
                      String s=new String(bytes,0,len);
                      stringBuffer.append(s);
           }
           return stringBuffer.toString();
    }

}
