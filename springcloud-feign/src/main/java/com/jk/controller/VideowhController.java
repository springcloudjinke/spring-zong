package com.jk.controller;

import com.jk.model.Goods;
import com.jk.model.Student;
import com.jk.model.Teacher;
import com.jk.model.Video;
import com.jk.service.VideowhService;
import com.jk.util.DataGridResult;
import com.jk.util.OSSClientUtil;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import feign.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: VideowhController
 * describe:
 *
 * @author wanghang
 * @date 2019/09/17 20:49
 */

@Controller
@RequestMapping("videowh")
public class VideowhController {

    @Autowired
    private VideowhService VideowhService;

    @Autowired
    private RedisTemplate redisTemplate;


   //查询首页
    @RequestMapping(value ="/queryvideowh")
    @ResponseBody
    public List<Teacher> queryvideowh(Model model) {
        List<Teacher> list = new ArrayList<>();
      /*  String key = "Video";
        if (redisTemplate.hasKey(key)){
            list = (List<Goods>) redisTemplate.opsForValue().get(key);
        }else {*/
        list = VideowhService.queryvideowh();
          /*  redisTemplate.opsForValue().set(key,list);
            redisTemplate.expire(key, 10, TimeUnit.MINUTES);
        }
        System.out.println(111111);*/
        return list;
    }
        //身份证照片
    @RequestMapping("updaloadImg")
    @ResponseBody
    public String uploadImg(MultipartFile imgg)throws IOException {
        if (imgg == null || imgg.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(imgg);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //System.out.println(split[0]);
        return split[0];
    }


    //查询 每个老师的视频
    @RequestMapping(value ="/queryTeacher")
    @ResponseBody
    public List<Teacher> queryTeacher(Integer teacherId) {


        List<Teacher> list = VideowhService.queryTeacher(teacherId);
        return list;
    }
        //查询 每个老师的视频
            //queryvideowhById
        @RequestMapping(value ="/queryvideowhById")
        @ResponseBody
        public List<Teacher> queryvideowhById(Integer teacherId) {



    List<Teacher> list = VideowhService.queryvideowhById(teacherId);
    return list;
}


    //头像照片
    @RequestMapping("updaloadImg1")
    @ResponseBody
    public String updaloadImg1(MultipartFile img)throws IOException {
        if (img == null || img.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(img);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //System.out.println(split[0]);
        return split[0];
    }

    //讲师入驻
    @RequestMapping("addTeacher")
    @ResponseBody
    public void addTeacher(Teacher teacher){

        VideowhService.addTeacher(teacher);
    }


    //个人中心 -- 个人资料修改
    @RequestMapping("/updData")
    @ResponseBody
    public void updData( Student student) {

        VideowhService.updData(student);

    }

    //新增会员
    @RequestMapping("addHuiYuan")
    public String addHuiYuan(HttpServletRequest request,Integer day){
        Student luser =(Student) request.getSession().getAttribute("luser");

        VideowhService.addHuiYuan(luser.getId(),day,luser.getMember());
        return "redirect:/page/main";

    }


    //个人中心--查询我的课程  mongodb
    @RequestMapping("queryMyCourse")
    @ResponseBody
    public DataGridResult queryCust(@RequestBody ParameUtil param){
        DataGridResult result = new DataGridResult();
        PageUtil pageUtil = VideowhService.queryMyCourse(param);
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;

    }
    //会员到期
    @RequestMapping("queryHuiYuan")
    @ResponseBody
    public void queryHuiYuan(HttpServletRequest request){
        Student luser = (Student) request.getSession().getAttribute("luser");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(new Date());
        String format1 = df.format(luser.getMemberTime());
        if (format.equals(format1)) {
            VideowhService.updateHuiYuan(luser.getId());
        }
    }

    //queryMember  查用户会员等级
    @RequestMapping("queryMember")
    @ResponseBody
    public Integer queryMember(HttpServletRequest request){
        Student luser =(Student) request.getSession().getAttribute("luser");
       Integer data=  VideowhService.queryMember(luser.getId());
//Integer data=luser.getMember();
        System.out.println(data);
        return  data;
    }
}
