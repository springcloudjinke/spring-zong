package com.jk.controller;

import com.jk.model.Student;
import com.jk.model.Video;
import com.jk.service.LoveService;
import com.jk.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("love")
@Controller
public class LoveController {

    @Autowired
    private LoveService loveService;

// 一个用户只能点击一次喜欢
        @RequestMapping("addCount")
        @ResponseBody
        public Map addCount(HttpServletRequest request,Integer vid1) {
            Student stu = (Student) request.getSession().getAttribute("luser");
            HashMap<String, Object> result = new HashMap<>();
            if (stu == null){
                result.put("code",1);
                result.put("msg","请登录");
                return result;
            }
            //通过userId去关联表查询有没有点赞 为null则没点
            int count =0;
            List<Integer> list=loveService.findLoveByUserId(stu.getId());
            for (int i=0; i<list.size();i++){
                      if (list.get(i)==vid1){
                          count++;
                      }
            }
            System.out.println(count);
            if (count >0){
                result.put("code",2);
                result.put("msg","该用户以点过");
                return result;
            }

            //点赞新增关联表
            loveService.saveLoveByUserId(stu.getId(),vid1);
            //修改点赞数量
            loveService.updateVideo();
            result.put("code",0);
            result.put("msg","点赞成功");
            return result;
        }

         @RequestMapping("queryUrlByVid")
         @ResponseBody
            public List<Video> queryUrlByVid(String videoUrl){

            List<Video> list=loveService.queryUrlByVid(videoUrl);

            return list;
    }
    @RequestMapping("addByIdVideo")
    @ResponseBody
    public String addByIdVideo(HttpServletRequest request,Integer vid1,String videoName1,String teacherName1){

        Student stu = (Student) request.getSession().getAttribute("luser");

        loveService.addByIdVideo(stu.getId(),vid1,videoName1,teacherName1);
        return "123";
    }
}