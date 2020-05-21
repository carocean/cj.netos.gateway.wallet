package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.*;

import java.math.BigDecimal;

public interface IReceiptTradeService {
    RechargeRecord recharge(String principal, String personName, String currency, long amount, PayChannel payChannel, String note);

    WithdrawRecord withdraw(String principal, String personName, long amount, String payChannelID, String note);

    WenyPurchRecord purchaseWeny(String principal, String personName, String wenyBankID, long amount, String note);

    WenyExchangeRecord exchangeWeny(String principal, String personName, WenyPurchRecord purchRecord, String note);


    WenyPurchRecord getPurchaseRecord(String purchase_sn);

}
