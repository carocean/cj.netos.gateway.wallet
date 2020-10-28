package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.bo.PayChannelBO;
import cj.netos.gateway.wallet.mapper.ChannelAccountMapper;
import cj.netos.gateway.wallet.mapper.ChannelRatioMapper;
import cj.netos.gateway.wallet.mapper.PayChannelMapper;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.util.Encript;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@CjBridge(aspects = "@transaction")
@CjService(name = "payChannelService")
public class PayChannelService implements IPayChannelService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayChannelMapper")
    PayChannelMapper payChannelMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ChannelAccountMapper")
    ChannelAccountMapper channelAccountMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ChannelRatioMapper")
    ChannelRatioMapper channelRatioMapper;

    @CjTransaction
    @Override
    public void addPayChannel(PayChannel payChannel) throws CircuitException {
        payChannelMapper.insert(payChannel);
    }

    @CjTransaction
    @Override
    public void removePayChannel(String id) throws CircuitException {
        payChannelMapper.deleteByPrimaryKey(id);
    }

    @CjTransaction
    @Override
    public void emptyPayChannel(String code) {
        payChannelMapper.deleteByPrimaryKey(code);
        ChannelAccountExample example = new ChannelAccountExample();
        example.createCriteria().andChannelEqualTo(code);
        channelAccountMapper.deleteByExample(example);
        ChannelRatioExample ratioExample = new ChannelRatioExample();
        ratioExample.createCriteria().andChannelEqualTo(code);
        channelRatioMapper.deleteByExample(ratioExample);
    }

    @CjTransaction
    @Override
    public void config(List<PayChannelBO> list) throws CircuitException {
        for (PayChannelBO bo : list) {
            if (StringUtil.isEmpty(bo.getCode())) {
                throw new CircuitException("404", "code 参数为空");
            }
            if (exists(bo.getCode())) {
                emptyPayChannel(bo.getCode());
            }
            bo.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
            payChannelMapper.insert(bo.toPayChannel());
            for (ChannelAccount account : bo.getAccounts()) {
                if (account.getBalanceAmount() == null) {
                    account.setBalanceAmount(0L);
                }
                account.setBalanceUtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
                if (StringUtil.isEmpty(account.getChannel())) {
                    account.setChannel(bo.getCode());
                }
                if (account.getLimitAmount() == null) {
                    account.setLimitAmount(0L);
                }

                if (account.getKeyExpire() == null) {
                    account.setKeyExpire(0L);
                }
                account.setId(Encript.md5(UUID.randomUUID().toString()));
                channelAccountMapper.insert(account);
            }
            for (ChannelRatio ratio : bo.getFeeRatios()) {
                ratio.setChannel(bo.getCode());
                if (ratio.getMaxBound() == null) {
                    ratio.setMaxBound(0L);
                }
                if (ratio.getMinBound() == null) {
                    ratio.setMinBound(0L);
                }
                if (ratio.getFeeRatio() == null) {
                    ratio.setFeeRatio(BigDecimal.ZERO);
                }

                ratio.setId(Encript.md5(UUID.randomUUID().toString()));
                channelRatioMapper.insert(ratio);
            }
        }
    }

    @CjTransaction
    @Override
    public List<PayChannel> pagePayChannel(int limit, long offset) throws CircuitException {
        return payChannelMapper.page(limit, offset);
    }

    @CjTransaction
    @Override
    public PayChannel getPayChannel(String payChannelID) {
        return payChannelMapper.selectByPrimaryKey(payChannelID);
    }

    @CjTransaction
    @Override
    public boolean exists(String code) {
        PayChannelExample example = new PayChannelExample();
        example.createCriteria().andCodeEqualTo(code);
        return payChannelMapper.countByExample(example) > 0;
    }
}
