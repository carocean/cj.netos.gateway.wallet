package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.ExchangingResult;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;

public interface IReceiptTradeService {
    RechargeRecord recharge(String principal, String personName, String currency, long amount, PayChannel payChannel, String note);

    WithdrawRecord withdraw(String principal, String personName, long amount, String payChannelID, String note) throws CircuitException;

    WenyPurchRecord purchaseWeny(String principal, String personName, String wenyBankID, long amount, String note) throws CircuitException;

    WenyExchangeRecord exchangeWeny(String principal, String personName, WenyPurchRecord purchRecord, String note) throws CircuitException;


    WenyPurchRecord getPurchaseRecord(String purchase_sn);

    RechargeRecord getRechargeRecord(String sn);

    WithdrawRecord getWithdrawRecord(String sn);

    WithdrawActivity getLastWithdrawActivity(String sn);

}
