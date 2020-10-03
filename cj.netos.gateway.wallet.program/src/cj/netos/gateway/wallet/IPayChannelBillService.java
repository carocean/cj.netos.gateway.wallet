package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.ChannelBill;

import java.util.List;

public interface IPayChannelBillService {
    List<ChannelBill> pageBill(int limit, long offset);

    List<ChannelBill> monthBill(int year, int month, int limit, long offset);

    long totalMonthBill(int order, int year, int month);

    List<ChannelBill> pageBillByAccount(String channelAccount, int limit, long offset);

    List<ChannelBill> monthBillByAccount(String channelAccount, int year, int month, int limit, long offset);

    long totalMonthBillByAccount(String channelAccount, int order, int year, int month);
}
