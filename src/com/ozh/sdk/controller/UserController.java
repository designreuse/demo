package com.ozh.sdk.controller;

import com.ozh.common.Global;
import com.ozh.common.utils.BusinessException;
import com.ozh.common.utils.ImageUtil;
import com.ozh.common.utils.StringUtils;
import com.ozh.core.entity.SysUser;
import com.ozh.core.service.SysUserService;
import com.ozh.utils.SpringContextHolder;
import com.ozh.web.WebContextFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by ozh on 2016/4/15.
 */
@Controller
@RequestMapping("/member")
public class UserController {
    private static final String SUCCESS = "success";
    private  static final String TMP="tmp_";
    protected static final String RESULT_ACTION = "redirect:/result";
    private static final String RESULT = "result";


    @RequestMapping(value = "/loginValidateCode", method = RequestMethod.POST)
    @ResponseBody
    public Object loginToFront(String loginId, String userPsw) throws BusinessException {
        try {
            SysUser user =SpringContextHolder.getBean(SysUserService.class).findByLoginId(loginId);
            if (user==null){
                user = SpringContextHolder.getBean(SysUserService.class).findByUserMobile(loginId).get(0);
            }
            //检查角色
            user = SpringContextHolder.getBean(SysUserService.class).loginToFrontByIdEmailMoble(loginId, userPsw);

            return user;
        } catch (BusinessException ex) {
//            doLoginFail(loginId);
            throw ex;
        }
    }


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

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public  Object registerUserFromSysUser(SysUser sysUser){
        SysUser sysUser1 = SpringContextHolder.getBean(SysUserService.class).registerUser(sysUser);
        ModelMap model = new ModelMap();
        model.put(SUCCESS,true);
        model.put("user",sysUser1);
        return model;
    }

    /**
     * 检查用户的mobile是否存在，存在则返回 {success:true}
     *
     * @param
     * @param mobile
     * @return
     * @throws
     */
    @RequestMapping(value = "/isExistMobile", method = RequestMethod.GET)
    @ResponseBody
    public Object isExistMobile(String mobile) {
        List<SysUser> list = SpringContextHolder.getBean(SysUserService.class).findByUserMobile(mobile);
        ModelMap model = new ModelMap();
        model.put(SUCCESS, list.size() > 0);
        return model;
    }

    //发送验证码(来自注册页面)
    @RequestMapping(value = "/sendValidateNum", method = RequestMethod.POST)
    public Object sendValidateNum(ModelMap model,String mobileNum) throws  IOException {

        if (StringUtils.isBlank(mobileNum)) {
            //这种情况表明来自注册页面
            throw new BusinessException(Global.ERRORS_DEFINED, new String[]{"手机号码不能为空"});
        }
        //发送短信验证码
        sendValidateNum(mobileNum);
        return RESULT;
    }
    //发送短信验证码
    public void sendValidateNum(String mobileNum) {
        if (org.apache.commons.lang.StringUtils.isNotBlank(mobileNum)) {
            mobileNum = mobileNum.trim();
        }

        // 取随机产生的认证码(6位数字)
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            stringBuffer.append(rand);
        }

//        java.util.List<SysUserPswValidate> sysUserPswValidateList = ServiceManager.sysUserPswValidateService.findByKey(ISysUserPswValidate.MOBILE_NUM, mobileNum);
//        if (sysUserPswValidateList != null && sysUserPswValidateList.size() > 0) {
//            SysUserPswValidate sysUserPswValidate = sysUserPswValidateList.get(0);
//            sysUserPswValidate.setValidateInfCode(stringBuffer.toString());
//            sysUserPswValidate.setValidateTime(new Date());
//            ServiceManager.sysUserPswValidateService.update(sysUserPswValidate);
//        } else {
//            SysUserPswValidate sysUserPswValidate = new SysUserPswValidate();
//            sysUserPswValidate.setSysUserId(1);//手机验证码因为用户还没有注册也就没有userId，所以这里固定一个
//            sysUserPswValidate.setValidateInfCode(stringBuffer.toString());
//            sysUserPswValidate.setValidateTime(new Date());
//            sysUserPswValidate.setValidateTypeCode(ValidateTypeCodeEnum.MOBILE.toCode());
//            sysUserPswValidate.setMobileNum(mobileNum);
//            ServiceManager.sysUserPswValidateService.save(sysUserPswValidate);
//        }
//        SysParamInf sysParamInf = ServiceManager.paramInfService.getByInnerCode(Global.WEB_NAME);
//        String webName="";
//        if(sysParamInf!=null){
//            webName=sysParamInf.getParamValueStr();
//        }
        //zchtodo 发送到短信队列里面
        String smsContent = "您的验证码为:" + stringBuffer.toString() + "，请输入后进行验证，谢谢！";
        System.out.println(smsContent);
//        sendSms(mobileNum, smsContent);

    }


}
