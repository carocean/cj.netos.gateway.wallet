# PayDetails

```text
    String payeeCode;//收款人代码
    String payeeName;//收款人名称
    String payeeType;//收款人类型，如：商户、洇取器等
    String orderno;
    String orderTitle;
    String serviceid;//使用什么服务导致的交易，如发红包服务、转账服务等等
    String serviceName;
    String salesman;//业务员或经手人
    String note;
```
## json示例：
```json
{
"payeeCode":"0283883838223",
"payeeName":"北京汤面王",
"payeeType": "merchant",
"orderno":"x-2838383822",
"orderTitle":"牛肉面1碗",
"serviceid":"geo",
"serviceName":"地圈",
"salesman": "",
"note":"欢迎惠顾"
}
```