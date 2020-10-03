package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IPayChannelBillService;
import cj.netos.gateway.wallet.model.ChannelBill;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/partner/channel/bill.ports")
public class PayChannelBillPorts implements IPayChannelBillPorts {
    @CjServiceRef
    IPayChannelBillService payChannelBillService;

    @Override
    public List<ChannelBill> pageBill(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return payChannelBillService.pageBill(limit, offset);
    }

    @Override
    public List<ChannelBill> monthBill(ISecuritySession securitySession, int year, int month, int limit, long offset) throws CircuitException {
        return payChannelBillService.monthBill(year, month, limit, offset);
    }

    @Override
    public long totalMonthBill(ISecuritySession securitySession, int order, int year, int month) throws CircuitException {
        return payChannelBillService.totalMonthBill(order, year, month);
    }

    @Override
    public List<ChannelBill> pageBillByAccount(ISecuritySession securitySession, String channelAccount, int limit, long offset) throws CircuitException {
        return payChannelBillService.pageBillByAccount(channelAccount, limit, offset);
    }

    @Override
    public List<ChannelBill> monthBillByAccount(ISecuritySession securitySession, String channelAccount, int year, int month, int limit, long offset) throws CircuitException {
        return payChannelBillService.monthBillByAccount(channelAccount, year, month, limit, offset);
    }

    @Override
    public long totalMonthBillByAccount(ISecuritySession securitySession, String channelAccount, int order, int year, int month) throws CircuitException {
        return payChannelBillService.totalMonthBillByAccount(channelAccount, order, year, month);
    }
}
