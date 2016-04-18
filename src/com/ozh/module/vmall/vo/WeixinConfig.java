package com.ozh.module.vmall.vo;

/**
 * Created by ozh on 2016/4/18.
 */

public class WeixinConfig {

    private String appId;
    private String appSecret;
    private String isEnablePay;       //是否开启微信支付
    private String partnerKey;
    private String partnerId;
    private String paySignKey;
    private String isEnableQRCodeBuy; //是否开启二维码扫购功能
    private String isEnableQRCodePay;  //是否开启二维码扫码支付功能
    private String isEnableCustomerService; //是否开启多客服系统

    //微信支付接口v3.3.6
    private String mchid;
    private String key;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getIsEnablePay() {
        return isEnablePay;
    }

    public void setIsEnablePay(String enablePay) {
        isEnablePay = enablePay;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPaySignKey() {
        return paySignKey;
    }

    public void setPaySignKey(String paySignKey) {
        this.paySignKey = paySignKey;
    }

    public String getIsEnableQRCodeBuy() {
        return isEnableQRCodeBuy;
    }

    public void setIsEnableQRCodeBuy(String enableQRCodeBuy) {
        isEnableQRCodeBuy = enableQRCodeBuy;
    }

    public String getIsEnableQRCodePay() {
        return isEnableQRCodePay;
    }

    public void setIsEnableQRCodePay(String enableQRCodePay) {
        isEnableQRCodePay = enableQRCodePay;
    }

    public String getIsEnableCustomerService() {
        return isEnableCustomerService;
    }

    public void setIsEnableCustomerService(String isEnableCustomerService) {
        this.isEnableCustomerService = isEnableCustomerService;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

