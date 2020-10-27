package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.studio.ecm.net.CircuitException;

public interface IPayChannelFactory {


    String pay(RechargeRecord record) throws CircuitException;

    PayChannelTransferResult transfer(WithdrawRecord record, ChannelAccount account, PersonCard card) throws CircuitException;

}
