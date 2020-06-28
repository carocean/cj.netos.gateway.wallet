package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.TransShunterRecord;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.ExchangedResult;
import cj.studio.ecm.net.CircuitException;

public interface IWenyBankTradeCaller {
    void exchange(WenyExchangeRecord record) throws CircuitException;

    void purchase(WenyPurchRecord purchaseBO)throws CircuitException;


    void transShunter(TransShunterRecord record) throws CircuitException;

}
