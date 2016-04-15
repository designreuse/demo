package com.ozh.sdk.controller;

import com.ozh.common.Global;
import com.ozh.common.utils.ImageUtil;
import com.ozh.web.WebContextFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by ozh on 2016/4/15.
 */
@Controller
@RequestMapping("/member")
public class UserController {
    private static final String SUCCESS = "success";
    private  static final String TMP="tmp_";



    @RequestMapping(value = "/uploadPhoto",method = RequestMethod.POST)
    @ResponseBody
    public String uploadPhoto(ModelMap model, @RequestParam("imageFile") MultipartFile uploadFile) throws  IOException {
        String orig = uploadFile.getOriginalFilename();
        String suffix = orig.substring(orig.lastIndexOf('.')).toLowerCase(Locale.US);
        String folder = WebContextFactory.getWebRealPath() + File.separatorChar + Global.UPLOAD_DIR + File.separatorChar + "temp" + File.separatorChar;
        if(!suffix.equalsIgnoreCase("."+ ImageUtil.IMAGE_TYPE_GIF)
                && !suffix.equalsIgnoreCase("."+ImageUtil.IMAGE_TYPE_JPEG)
                && !suffix.equalsIgnoreCase("."+ImageUtil.IMAGE_TYPE_JPG)
                && !suffix.equalsIgnoreCase("."+ImageUtil.IMAGE_TYPE_PNG)
                ){
            model.put(SUCCESS, false);
        }

        //保存临时文件
        String fileId = folder + TMP + System.currentTimeMillis() + suffix;
        File file = new File(fileId);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        uploadFile.transferTo(file);
        String filePath = fileId.substring(0, fileId.lastIndexOf('.'));

        //删除临时文件
//        file.delete();//NOSONAR
        model.put("fileId", filePath);
//        model.put("url", FileSystemEngin.getFileSystem().getUrl(com.iloosen.imall.client.commons.StringUtils.insertFileNameSuffixToUrl(fileId, "_100X100")));
        model.put(SUCCESS, true);
        return SUCCESS;
    }
}
