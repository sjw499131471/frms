package com.sjw.frms.controller;

import com.sjw.frms.model.Resource;
import com.sjw.frms.service.IResourceService;
import com.sjw.frms.service.IResourceTypeService;
import com.sjw.frms.utils.ParamHandler;
import com.sun.javafx.binding.StringFormatter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private static String strSeparator =System.getProperty("file.separator");

    @RequestMapping
    public String resource(Model model,HttpServletRequest request){
        Map<String, Object> params = ParamHandler.getParamMap(request);
        params.put("page",0);
        params.put("limit",100);
        model.addAttribute("resources",resourceService.selectList(params));
        return "resources";
    }

    @GetMapping("/{memberId}/{resourceId}")
    public void getImage(@PathVariable String memberId, @PathVariable String resourceId, HttpServletResponse response){
        String rootPath = System.getenv( "SystemRoot").split(strSeparator+strSeparator)[0];
        String path = rootPath+strSeparator+"resource"+strSeparator+memberId+strSeparator+resourceId+".png";
        try {
            final InputStream inputStream = new FileInputStream(new File(path));
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        }
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
            result.put("message","??????");
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
        //??????????????????????????????????????????
        if(!file.isEmpty()) {
            //??????????????????
            String rootPath = System.getenv( "SystemRoot").split(strSeparator+strSeparator)[0];
            String path = rootPath+strSeparator+"resource"+strSeparator+memberId;
//            String path = request.getServletContext().getRealPath("/img/resource/"+memberId);

            File filepath = new File(path,resourceId+".png");
            logger.info(filepath.getAbsolutePath());
            //?????????????????????????????????????????????????????????
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //????????????????????????????????????????????????
            //file.transferTo(new File(path + File.separator + filename));
            InputStream iStream = file.getInputStream();
            OutputStream out = new FileOutputStream(filepath);
            //???????????????
            byte[] bytes2= new byte[1024];
            //??????????????????????????????????????????????????????????????????-1????????????????????????????????????????????????????????????
            while(iStream.read(bytes2)!=-1){
                //??????????????????????????????afterfile?????????
                out.write(bytes2);
            }
            out.flush();
            out.close();
        }else {

        }

        return result;
    }
}
