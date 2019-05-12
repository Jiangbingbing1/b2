package com.baway.day13.bean;

import java.util.List;

public class UserBean{

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
        private Mlss mlss;

        public Mlss getMlss() {
            return mlss;
        }

        public void setMlss(Mlss mlss) {
            this.mlss = mlss;
        }

        public class Mlss{
            private List<Mydata> commodityList;

            public List<Mydata> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<Mydata> commodityList) {
                this.commodityList = commodityList;
            }

            public class Mydata{
                 private String commodityName;
                 private String masterPic;

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }
            }
        }

    }

}
