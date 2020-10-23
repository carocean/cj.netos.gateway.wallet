package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IChannelAccountService;
import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.IPersonCardService;
import cj.netos.gateway.wallet.bo.PayChannelBO;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.result.PayChannelResult;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.gson2.com.google.gson.reflect.TypeToken;
import cj.ultimate.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CjService(name = "/partner/payChannel.ports")
public class PayChannelPorts implements IPayChannelPorts {

    @CjServiceRef
    IPayChannelService payChannelService;
    @CjServiceRef
    IChannelAccountService channelAccountService;
    @CjServiceRef
    IPersonCardService personCardService;

    private void _checkRights(ISecuritySession securitySession) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators") && !securitySession.roleIn("tenant:administrators")) {
            throw new CircuitException("801", "拒绝访问");
        }
    }

    @Override
    public void removePayChannel(ISecuritySession securitySession, String payChannelID) throws CircuitException {
        _checkRights(securitySession);
        payChannelService.removePayChannel(payChannelID);
    }

    @Override
    public PayChannel getPayChannel(ISecuritySession securitySession, String code) throws CircuitException {
        return payChannelService.getPayChannel(code);
    }

    @Override
    public List<PayChannelResult> pagePayChannel(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
//        _checkRights(securitySession);
        List<PayChannel> list = payChannelService.pagePayChannel(limit, offset);
        List<PayChannelResult> results = new ArrayList<>();
        for (PayChannel channel : list) {
            PayChannelResult result = new PayChannelResult();
            result.copy(channel);
            results.add(result);
        }
        return results;
    }


    @Override
    public void addPayChannel(ISecuritySession securitySession, String code, String name, String note) throws CircuitException {
        _checkRights(securitySession);
        if (StringUtil.isEmpty(code)) {
            throw new CircuitException("404", "code 参数为空");
        }
        if (StringUtil.isEmpty(name)) {
            throw new CircuitException("404", "name 参数为空");
        }
        if (payChannelService.exists(code)) {
            throw new CircuitException("500", String.format("已存在支付渠道:%s", code));
        }
        PayChannel channel = new PayChannel();
        channel.setCode(code);
        channel.setNote(note);
        channel.setName(name);
        channel.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        payChannelService.addPayChannel(channel);
    }

    @Override
    public void addPersonCard(ISecuritySession securitySession, String cardSn, String cardHolder, String cardArriBank, String cardPubBank, int cardType, String cardPhone, String payChannel, String payPwd) throws CircuitException {
        if (StringUtil.isEmpty(cardSn)) {
            throw new CircuitException("404", "cardSn 参数为空");
        }
        if (StringUtil.isEmpty(cardHolder)) {
            throw new CircuitException("404", "cardHolder 参数为空");
        }
        if (personCardService.existsCardBySn(securitySession.principal(), cardSn)) {
            throw new CircuitException("500", "用户名下已存在该卡:" + cardSn);
        }
        PersonCard card = new PersonCard();
        card.setCardAttrBank(cardArriBank);
        card.setCardHolder(cardHolder);
        card.setCardPhone(cardPhone);
        card.setCardPubBank(cardPubBank);
        card.setCardType(cardType);
        card.setCardSn(cardSn);
        card.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        card.setId(new IdWorker().nextId());
        card.setPayChannel(payChannel);
        card.setPayPwd(payPwd);
        card.setPerson(securitySession.principal());
        personCardService.addPersonCard(card);
    }

    @Override
    public PersonCard getPersonCard(ISecuritySession securitySession, String id) throws CircuitException {
        return personCardService.getPersonCard(securitySession.principal(), id);
    }

    @Override
    public long totalPersonCard(ISecuritySession securitySession) throws CircuitException {
        return personCardService.totalPersonCard(securitySession.principal());
    }

    @Override
    public List<PersonCard> pagePersonCard(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return personCardService.pagePersonCard(securitySession.principal(), limit, offset);
    }

    @Override
    public void removePersonCard(ISecuritySession securitySession, String id) throws CircuitException {
        personCardService.removePersonCard(securitySession.principal(), id);
    }

    @Override
    public void addAccount(ISecuritySession securitySession, String channel, String appid, String serviceUrl, String notifyUrl, int useCert, String publicKey, String privateKey, String certPath1, String certPath2, String certPath3, String certPath4, String keyPubtime, long keyExpire, int weight, long limitAmount, String note) throws CircuitException {
        _checkRights(securitySession);
        if (StringUtil.isEmpty(channel)) {
            throw new CircuitException("404", "channel 参数为空");
        }
        if (StringUtil.isEmpty(appid)) {
            throw new CircuitException("404", "appid 参数为空");
        }
        if (!payChannelService.exists(channel)) {
            throw new CircuitException("404", String.format("不存在支付渠道:%s", channel));
        }
        ChannelAccount account = new ChannelAccount();
        account.setAppId(appid);
        account.setBalanceAmount(0L);
        account.setBalanceUtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        account.setChannel(channel);
        account.setId(Encript.md5(UUID.randomUUID().toString()));
        account.setKeyExpire(keyExpire);
        account.setPublicKey(publicKey);
        account.setPrivateKey(privateKey);
        account.setLimitAmount(limitAmount);
        account.setNote(note);
        account.setServiceUrl(serviceUrl);
        account.setNotifyUrl(notifyUrl);
        account.setUseCert(useCert);
        account.setCertPath1(certPath1);
        account.setCertPath2(certPath2);
        account.setCertPath3(certPath3);
        account.setCertPath4(certPath4);
        channelAccountService.addAccount(account);
    }

    @Override
    public void removeAccount(ISecuritySession securitySession, String accountid) throws CircuitException {
        _checkRights(securitySession);
        channelAccountService.removeAccount(accountid);
    }

    @Override
    public ChannelAccount getAccount(ISecuritySession securitySession, String accountid) throws CircuitException {
        _checkRights(securitySession);
        return channelAccountService.getAccount(accountid);
    }

    @Override
    public List<ChannelAccount> pageAccountOfChannel(ISecuritySession securitySession, String channel, int limit, long offset) throws CircuitException {
        _checkRights(securitySession);
        return channelAccountService.pageAccountOfChannel(channel, limit, offset);
    }

    @Override
    public List<ChannelAccount> pageAccount(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        _checkRights(securitySession);
        return channelAccountService.pageAccount(limit, offset);
    }

    @Override
    public long totalAccountBalance(ISecuritySession securitySession, String channel) throws CircuitException {
        _checkRights(securitySession);
        return channelAccountService.totalAccountBalance(channel);
    }

    @Override
    public void config(ISecuritySession securitySession, String data) throws CircuitException {
        _checkRights(securitySession);
        if (StringUtil.isEmpty(data)) {
            throw new CircuitException("404", "data 参数为空");
        }
        List<PayChannelBO> list = new Gson().fromJson(data, new TypeToken<ArrayList<PayChannelBO>>() {
        }.getType());
        payChannelService.config(list);
    }
}
