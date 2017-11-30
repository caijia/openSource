package com.caijia.analysisopensource.ebusiness.sample.service.model;

/**
 * Created by cai.jia on 2017/4/14 0014
 */

public class SampleResult {

    private String id;
    private String title;
    private String summary;
    private String smallCover;
    private String bigCover;
    private String showType;
    private String visitCount;
    private String liveStatus;
    private String type;
    private String chatRoom;
    private String liveId;
    private String hasConnect;
    private String hasGraphicLive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSmallCover() {
        return smallCover;
    }

    public void setSmallCover(String smallCover) {
        this.smallCover = smallCover;
    }

    public String getBigCover() {
        return bigCover;
    }

    public void setBigCover(String bigCover) {
        this.bigCover = bigCover;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getHasConnect() {
        return hasConnect;
    }

    public void setHasConnect(String hasConnect) {
        this.hasConnect = hasConnect;
    }

    public String getHasGraphicLive() {
        return hasGraphicLive;
    }

    public void setHasGraphicLive(String hasGraphicLive) {
        this.hasGraphicLive = hasGraphicLive;
    }

    @Override
    public String toString() {
        return "SampleResult{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", smallCover='" + smallCover + '\'' +
                ", bigCover='" + bigCover + '\'' +
                ", showType='" + showType + '\'' +
                ", visitCount='" + visitCount + '\'' +
                ", liveStatus='" + liveStatus + '\'' +
                ", type='" + type + '\'' +
                ", chatRoom='" + chatRoom + '\'' +
                ", liveId='" + liveId + '\'' +
                ", hasConnect='" + hasConnect + '\'' +
                ", hasGraphicLive='" + hasGraphicLive + '\'' +
                '}';
    }
}
