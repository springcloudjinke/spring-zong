package com.jk.service.impl;

import com.jk.dao.LoveDao;
import com.jk.model.Uvideo;
import com.jk.model.Video;
import com.jk.service.LoveServiceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoveServiceImpl implements LoveServiceApi{


    @Autowired
    private LoveDao loveDao;
    @Autowired
    public MongoTemplate mongoTemplate;


    @Override
    @RequestMapping(value = "/findLoveByUserId")
    public List<Integer> findLoveByUserId(@RequestParam("id")Integer id) {
        return loveDao.findLoveByUserId(id);
    }

    @Override
    @RequestMapping(value = "/saveLoveByUserId")
    public void saveLoveByUserId(@RequestParam("id")Integer id, @RequestParam("vid1")Integer vid1) {
        loveDao.saveLoveByUserId(id,vid1);
    }

    @Override
    @RequestMapping(value = "/updateVideo")
    public void updateVideo() {
        loveDao.updateVideo();
    }

    @Override
    @RequestMapping(value = "/queryUrlByVid")
    public List<Video> queryUrlByVid(@RequestParam("videoUrl")String videoUrl) {
        return loveDao.queryUrlByVid(videoUrl);
    }

    @Override
    @RequestMapping(value = "/addByIdVideo")
    public void addByIdVideo(@RequestParam("id")Integer id,@RequestParam("vid1") Integer vid1, @RequestParam("videoName1")String videoName1, @RequestParam("teacherName1")String teacherName1) {
        Uvideo uideo = new Uvideo();
        uideo.setVid(vid1);
        uideo.setTeacherName(teacherName1);
        uideo.setVideoName(videoName1);
        mongoTemplate.insert(uideo, "uvideo"+id);




    }

}