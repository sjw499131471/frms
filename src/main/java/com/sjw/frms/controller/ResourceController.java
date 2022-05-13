package com.sjw.frms.controller;

import com.sjw.frms.model.Resource;
import com.sjw.frms.service.IResourceService;
import com.sjw.frms.service.IResourceTypeService;
import com.sjw.frms.utils.ParamHandler;
import com.sun.javafx.binding.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/resource")
@Controller
public class ResourceController {
    @Autowired
    IResourceService resourceService;
    @Autowired
    IResourceTypeService resourceTypeService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping
    public String resource(Model model,HttpServletRequest request){
        Map<String, Object> params = ParamHandler.getParamMap(request);
        params.put("page",0);
        params.put("limit",100);
        model.addAttribute("resources",resourceService.selectList(params));
        return "resources";
    }

    @RequestMapping("addOrEditPage")
    public String addOrEditPage(Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",100);
        model.addAttribute("types",resourceTypeService.selectList(map));
        model.addAttribute("memberId",request.getParameter("memberId"));
        return "resource";
    }

    @RequestMapping("addOrEdit")
    @ResponseBody
    public Map<String,Object> addOrEdit(@ModelAttribute Resource resource, BindingResult bindingResult){
        Map<String, Object> result = new HashMap<String, Object>();
        int count = 0;
        if (resource != null){
            if (StringUtils.hasText(resource.getResourceId())){
                count = resourceService.updateByPrimaryKey(resource);
            }else {
                resource.setCreatedTime(new Date());
                count = resourceService.insert(resource);
            }
        }
        if (count>0){
            result.put("message","1");
            result.put("resourceId",resource.getResourceId());
        }else {
            result.put("message","失败");
        }
        return result;
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();


        return result;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request,@RequestParam("resourceId") String resourceId,@RequestParam("memberId") String memberId,@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/img/resource/"+memberId);

            File filepath = new File(path,resourceId+".png");
            logger.info(filepath.getAbsolutePath());
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            //file.transferTo(new File(path + File.separator + filename));
            InputStream iStream = file.getInputStream();
            OutputStream out = new FileOutputStream(filepath);
            //文件缓存区
            byte[] bytes2= new byte[1024];
            //将文件流信息读取文件缓存区，如果读取结果不为-1就代表文件没有读取完毕，反之已经读取完毕
            while(iStream.read(bytes2)!=-1){
                //将缓存区中的内容写到afterfile文件中
                out.write(bytes2);
            }
            out.flush();
            out.close();
        }else {

        }

        return result;
    }
}
