# 支付渠道
第三方支付渠道的定义

## 属性
```java
/**
     * Column: code
     * Remark: 渠道类型代码， WeChat:微信 Alipay:支付宝 yinLan:银联
     */
    private String code;

    /**
     * Column: url
     * Remark: 渠道的服务地址
     */
    private String url;

    /**
     * Column: appid
     * Remark: 渠道给的aped
     */
    private String appid;

    /**
     * Column: appSecret
     * Remark: 渠道给的密钥
     */
    private String appsecret;

    /**
     * Column: mch_id
     * Remark: 商户号
     */
    private String mchId;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    /**
     * Column: limit_amount
     * Remark: 渠道限额
     */
    private Long limitAmount;
    /**
     * Column: channel_name
     * Remark: 支付渠道名称
     */
    private String channelName;
```

## 示例

```json
{
  "code": "alipay",
  "channelName": "支付宝",
  "appid": "xx",
  "appsecret": "xx",
  "mchId": "xx",
  "limitAmount":"500000",
  "url": "/p/s",
  "note": ""
}
```