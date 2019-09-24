package com.jk.service;

import com.jk.model.Video;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LoveServiceApi {
    @RequestMapping(value = "/findLoveByUserId")
    List<Integer> findLoveByUserId(@RequestParam("id")Integer id);
    @RequestMapping(value = "/saveLoveByUserId")
    void saveLoveByUserId(@RequestParam("id")Integer id, @RequestParam("vid1")Integer vid1);
    @RequestMapping(value = "/updateVideo")
    void updateVideo();
    @RequestMapping(value = "/queryUrlByVid")
    List<Video> queryUrlByVid(@RequestParam("videoUrl") String videoUrl);
    @RequestMapping(value = "/addByIdVideo")
    void addByIdVideo(@RequestParam("id")Integer id,@RequestParam("vid1") Integer vid1,@RequestParam("videoName1") String videoName1,@RequestParam("teacherName1") String teacherName1);
}
