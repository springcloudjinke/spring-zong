package com.jk.controller;

import com.jk.model.Goods;
import com.jk.model.Video;
import com.jk.service.VideoService;
import com.jk.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("video")
@Controller
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("test")
    public String test(){
        return "html/dhy/test";
    }

    @RequestMapping("index")
    public String index(){
        return "html/dhy/index";
    }

    //查询goods表
    @RequestMapping("queryTeacher")
    @ResponseBody
    public DataGridResult queryTeacher(ParameUtil parameUtil) {

        DataGridResult dataGridResult = new DataGridResult();

        PageUtil pageUtil = videoService.queryTeacher(parameUtil);

        dataGridResult.setTotal(pageUtil.getSumSize());
        dataGridResult.setRows(pageUtil.getList());

        return dataGridResult;

    }


    //跳转到新增页面
    @RequestMapping("toAddTeaPage")
    public String toAddTeaPage() {
        return "html/dhy/addTea";
    }

    //跳转到 新增video页面
    @RequestMapping("toAddvideoPage")
    public ModelAndView toAddvideoPage(Integer id, ModelAndView model) {
        model.addObject("id", id);
        model.setViewName("html/dhy/addVideo");
        return model;
    }

/*    //新增goods表
    @RequestMapping("addVideo")
    @ResponseBody
    public void addVideo(Goods goods){
        videoService.addVideo(goods);
    }*/

    //新增video表
    @RequestMapping("addVideo")
    @ResponseBody
    public void addVideo(Video video) {
        videoService.addVideo(video);
    }

    //上传图片
    @RequestMapping("updaloadImg")
    @ResponseBody
    public String uploadImg(MultipartFile imgg) throws IOException {
        if (imgg == null || imgg.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(imgg);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        System.out.println(split[0]);
        return split[0];
    }


    //ftp处理文件上传视频
    @RequestMapping(value = "/ftpuploadimg", method = RequestMethod.POST)
    public @ResponseBody
    String uploadVideo(@RequestParam("file") MultipartFile file,
                       HttpServletRequest request, String videoUrl) throws IOException {
        long t=System.currentTimeMillis();

        String fileName= String.valueOf(t)+".mp4";

        //String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String filePath = null;

        videoUrl = fileName;

        Boolean flag = FtpFileUtil.uploadFile(videoUrl, inputStream);
        if (flag == true) {
            System.out.println("ftp上传成功！");

            filePath = fileName;
        }

        System.out.println(filePath);
        return filePath;  //该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
    }


    //查看视频
    @RequestMapping("queryVideo")
    public ModelAndView queryVideo(ModelAndView modelAndView, Integer id) {

        List<Video> list = videoService.queryVideo(id);

        modelAndView.addObject("v", list);

        modelAndView.setViewName("html/dhy/showVideo");

        return modelAndView;
    }

}
