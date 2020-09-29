# 支付渠道
第三方支付渠道的定义

## 属性
```java
class PayChannel {
    /**
     * Column: code
     * Remark: 渠道类型代码， WeChat:微信 Alipay:支付宝 yinLan:银联
     */
    private String code;

    /**
     * Column: name
     * Remark: 渠道的名称，如支付宝、微信、网联卡等
     */
    private String name;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;
}

class ChannelAccount {
    
    /**
     * Column: app_id
     * Remark: 即渠道给的appid
     */
    private String appId;

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
     * Column: weight
     * Remark: 权重，即在充值时系统按权重选择渠道账户以接受充值的概率。算法：如https://blog.csdn.net/qq_35923749/article/details/89214611
     */
    private Integer weight;

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
      "publicKey": "",
      "privateKey": "",
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