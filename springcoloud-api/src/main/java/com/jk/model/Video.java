package com.jk.model;

import java.io.Serializable;

/**
 * ClassName: Video
 * describe:
 *
 * @author wanghang
 * @date 2019/09/18 11:50
 */

public class Video implements Serializable {

    private Integer vid;            //视频表;
    private String videoName;           //视频名称;
    private String videoUrl;            //视频路径;
    private Integer videoTypeId;        //视频类型id;
    private Integer tid;          //讲师id;

    private String  courseIntroduction;//课程介绍

    private String  introduction;//本讲介绍
    public Integer getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(Integer videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        this.courseIntroduction = courseIntroduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
