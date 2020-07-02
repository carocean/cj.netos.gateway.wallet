package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.*;
import cj.studio.ecm.net.CircuitException;

public interface IReceiptTradeService {
    RechargeRecord recharge(String principal, String personName, String currency, long amount, PayChannel payChannel, String note);

    WithdrawRecord withdraw(String principal, String personName, long amount, String payChannelID, String note) throws CircuitException;

    WenyPurchRecord purchaseWeny(String principal, String personName, String wenyBankID, long amount, String note) throws CircuitException;

    WenyExchangeRecord exchangeWeny(String principal, String personName, WenyPurchRecord purchRecord, String note) throws CircuitException;


    WenyPurchRecord getPurchaseRecord(String purchase_sn);

    RechargeRecord getRechargeRecord(String sn);

    WithdrawRecord getWithdrawRecord(String sn);

    WithdrawActivity getLastWithdrawActivity(String sn);

    DepositAbsorbRecord depositAbsorb(String principal, String personName, long amount, String sourceCode, String sourceTitle, String note);

    TransAbsorbRecord transAbsorb(String principal, String personName, long amount, String note);

    TransProfitRecord transProfit(String principal, String personName, String wenyBankID, long amount, String note);

    PayRecord payTrade(String principal, String personName, long amount, int type, PayDetailsBO details, String note);

    TransShunterRecord transShunter(String principal, String personName, String wenyBankID, String shunter, long amount, String note);

    P2pRecord p2p(String payer, String payerName, String payee, String payeeName, long amount, int type,String direct, String note);

}
