package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IPayChannelBillService;
import cj.netos.gateway.wallet.mapper.ChannelBillMapper;
import cj.netos.gateway.wallet.model.ChannelBill;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjService(name = "payChannelBillService")
public class PayChannelBillService implements IPayChannelBillService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ChannelBillMapper")
    ChannelBillMapper channelBillMapper;

    @CjTransaction
    @Override
    public List<ChannelBill> pageBill(int limit, long offset) {
        return channelBillMapper.pageBill(limit, offset);
    }

    @CjTransaction
    @Override
    public List<ChannelBill> monthBill(int year, int month, int limit, long offset) {
        return channelBillMapper.monthBill(year,month,limit, offset);
    }

    @CjTransaction
    @Override
    public long totalMonthBill(int order, int year, int month) {
        return channelBillMapper.totalMonthBill(order, year, month);
    }

    @CjTransaction
    @Override
    public List<ChannelBill> pageBillByAccount(String channelAccount, int limit, long offset) {
        return channelBillMapper.pageBillByAccount(channelAccount, limit, offset);
    }

    @CjTransaction
    @Override
    public List<ChannelBill> monthBillByAccount(String channelAccount, int year, int month, int limit, long offset) {
        return channelBillMapper.monthBillByAccount(channelAccount, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public long totalMonthBillByAccount(String channelAccount, int order, int year, int month) {
        return channelBillMapper.totalMonthBillByAccount(channelAccount, order, year, month);
    }
}
