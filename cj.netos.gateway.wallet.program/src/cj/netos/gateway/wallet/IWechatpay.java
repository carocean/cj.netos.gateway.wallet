package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.studio.ecm.net.CircuitException;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayUserInfoShareResponse;

public interface IWechatpay {
    String pay(RechargeRecord record) throws CircuitException;


    String transfer(WithdrawRecord record, ChannelAccount account, PersonCard card) throws CircuitException;

}
