package com.jk.model;

import java.io.Serializable;


public class Teacher implements Serializable {
    private Integer teacherId;
    private  String videoName;//电影名称
    private  String videoGenus;//视频种类   如 ：视频公开课
    private  String teacherName;//讲师名称
    private  String school;//讲师学校
    private  String picture;//讲师图片 头像
    private  String videoType;//课程分类 主营科目
    private  Integer videoid;//视频id
    private  String idCard;//身份证
    private  String idCardPhone;//身份证照片
    private  String persionInfo;//个人简介
    private  String phone;//联系方式
    private  Integer experience;//教学经验
    private Integer status;         // 0.审核中  1.审核通过  2.审核失败
    private  String videoUrl;
    private Integer vid;
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoGenus() {
        return videoGenus;
    }

    public void setVideoGenus(String videoGenus) {
        this.videoGenus = videoGenus;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardPhone() {
        return idCardPhone;
    }

    public void setIdCardPhone(String idCardPhone) {
        this.idCardPhone = idCardPhone;
    }

    public String getPersionInfo() {
        return persionInfo;
    }

    public void setPersionInfo(String persionInfo) {
        this.persionInfo = persionInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }
}
