# 支付渠道
第三方支付渠道的定义

## 属性
```java
class PayChannel {
    /**
         * Column: id
         */
        private String id;
    
        /**
         * Column: app_id
         * Remark: 即渠道给的appid
         */
        private String appId;
    
        /**
         * Column: channel
         * Remark: 归属的渠道代码，对应表pay_channel的code
         */
        private String channel;
    
        /**
         * Column: balance_amount
         * Remark: 余额，单位为分
         */
        private Long balanceAmount;
    
        /**
         * Column: balance_utime
         * Remark: 余额更新时间
         */
        private String balanceUtime;
    
        /**
         * Column: limit_amount
         * Remark: 渠道限额,单位为分,0表示无限额
         */
        private Long limitAmount;
    
        /**
         * Column: service_url
         * Remark: 渠道的服务地址 全部以icon表示，但也为了兼容http格式，设定icons的格式为： icon:0xf1d7
         */
        private String serviceUrl;
    
        /**
         * Column: notify_url
         * Remark: 通知地址
         */
        private String notifyUrl;
    
        /**
         * Column: key_pubtime
         * Remark: 钥匙的发布时间
         */
        private String keyPubtime;
    
        /**
         * Column: key_expire
         * Remark: 钥匙的过期时间，用于提醒管理员，如果有，0表示永不过期
         */
        private Long keyExpire;
    
        /**
         * Column: use_cert
         * Remark: 是否采用证书或采用公钥 0为采用公钥 1为采用证书
         */
        private Integer useCert;
    
        /**
         * Column: public_key
         * Remark: 渠道给的公钥，一般是应用(app_id)的对应公钥
         */
        private String publicKey;
    
        /**
         * Column: private_key
         * Remark: 渠道给的私钥，一般是应用(app_id)的对应私钥
         */
        private String privateKey;
    
        /**
         * Column: cert_path1
         * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
         */
        private String certPath1;
    
        /**
         * Column: cert_path2
         * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
         */
        private String certPath2;
    
        /**
         * Column: cert_path3
         * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
         */
        private String certPath3;
    
        /**
         * Column: cert_path4
         * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
         */
        private String certPath4;
    
        /**
         * Column: note
         * Remark: 备注
         */
        private String note;
}
```

## 示例

```json
[
{
  "code": "alipay",
  "name": "支付宝",
  "note": "",
  "accounts": [
    {
      "appId": "",
      "serviceUrl": "",
      "notifyUrl": "",
      "useCert": 0,
      "publicKey": "",
      "privateKey": "",
      "certPath1": "",
      "certPath2": "",
      "certPath3": "",
      "certPath4": "",
      "keyPubtime": "",
      "keyExpire": 0,
      "limitAmount": 0,
      "weight": 1,
      "note": ""
    }
  ]
}
]
```