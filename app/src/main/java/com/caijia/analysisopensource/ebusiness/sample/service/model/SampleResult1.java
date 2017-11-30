package com.caijia.analysisopensource.ebusiness.sample.service.model;

import java.util.List;

/**
 * Created by cai.jia on 2017/11/27.
 */

public class SampleResult1 {

    private int repCode;
    private String repMsg;
    private KillAreaBean killArea;
    private List<AdSlideListBean> adSlideList;
    private List<FloorListBean> floorList;
    private List<?> noticeList;

    public int getRepCode() {
        return repCode;
    }

    public void setRepCode(int repCode) {
        this.repCode = repCode;
    }

    public String getRepMsg() {
        return repMsg;
    }

    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }

    public KillAreaBean getKillArea() {
        return killArea;
    }

    public void setKillArea(KillAreaBean killArea) {
        this.killArea = killArea;
    }

    public List<AdSlideListBean> getAdSlideList() {
        return adSlideList;
    }

    public void setAdSlideList(List<AdSlideListBean> adSlideList) {
        this.adSlideList = adSlideList;
    }

    public List<FloorListBean> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorListBean> floorList) {
        this.floorList = floorList;
    }

    public List<?> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<?> noticeList) {
        this.noticeList = noticeList;
    }

    public static class KillAreaBean {

        private int repCode;
        private String repMsg;
        private int show;
        private String kingTitle;
        private String secondTitle;
        private String thirdTitle;
        private List<?> kingList;
        private List<?> secondList;
        private List<?> thirdList;

        public int getRepCode() {
            return repCode;
        }

        public void setRepCode(int repCode) {
            this.repCode = repCode;
        }

        public String getRepMsg() {
            return repMsg;
        }

        public void setRepMsg(String repMsg) {
            this.repMsg = repMsg;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public String getKingTitle() {
            return kingTitle;
        }

        public void setKingTitle(String kingTitle) {
            this.kingTitle = kingTitle;
        }

        public String getSecondTitle() {
            return secondTitle;
        }

        public void setSecondTitle(String secondTitle) {
            this.secondTitle = secondTitle;
        }

        public String getThirdTitle() {
            return thirdTitle;
        }

        public void setThirdTitle(String thirdTitle) {
            this.thirdTitle = thirdTitle;
        }

        public List<?> getKingList() {
            return kingList;
        }

        public void setKingList(List<?> kingList) {
            this.kingList = kingList;
        }

        public List<?> getSecondList() {
            return secondList;
        }

        public void setSecondList(List<?> secondList) {
            this.secondList = secondList;
        }

        public List<?> getThirdList() {
            return thirdList;
        }

        public void setThirdList(List<?> thirdList) {
            this.thirdList = thirdList;
        }
    }

    public static class AdSlideListBean {

        private String url;
        private int adClick;
        private String clickUrl;
        private String prodId;
        private String noticeId;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getAdClick() {
            return adClick;
        }

        public void setAdClick(int adClick) {
            this.adClick = adClick;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }

        public String getProdId() {
            return prodId;
        }

        public void setProdId(String prodId) {
            this.prodId = prodId;
        }

        public String getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class FloorListBean {

        private int id;
        private String title;
        private String url;
        private int clazz;
        private String startTime;
        private String endTime;
        private String curTime;
        private List<ContentListBean> contentList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getClazz() {
            return clazz;
        }

        public void setClazz(int clazz) {
            this.clazz = clazz;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getCurTime() {
            return curTime;
        }

        public void setCurTime(String curTime) {
            this.curTime = curTime;
        }

        public List<ContentListBean> getContentList() {
            return contentList;
        }

        public void setContentList(List<ContentListBean> contentList) {
            this.contentList = contentList;
        }

        public static class ContentListBean {
            /**
             * url : http://izhongpei.com/static/uploads/brand/logo_1.jpg
             * title : 一汽伊顿
             * queryType : 0
             * queryParam : 1
             */

            private String url;
            private String title;
            private int queryType;
            private String queryParam;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getQueryType() {
                return queryType;
            }

            public void setQueryType(int queryType) {
                this.queryType = queryType;
            }

            public String getQueryParam() {
                return queryParam;
            }

            public void setQueryParam(String queryParam) {
                this.queryParam = queryParam;
            }
        }
    }
}
