/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Goods
 * Author:   李辉
 * Date:     2019/9/18 19:16
 * Description: a
 * History:
 * <author>          <time>          <version>          <desc>
 * 李辉           修改时间           版本号              描述
 */
package com.jk.model;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 李辉
 * @create 2019/9/18
 * @since 1.0.0
 */

public class Goods implements Serializable {
    private  Integer goodsId;
    private  String videoName;//电影名称
    private  String videoGenus;//视频种类   如 ：视频公开课
    private  String teacherName;//讲师名称
    private  String school;//讲师学校
    private  String picture;//讲师图片
    private  String videoType;//课程分类
    private  Integer videoid;//视频id

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
}