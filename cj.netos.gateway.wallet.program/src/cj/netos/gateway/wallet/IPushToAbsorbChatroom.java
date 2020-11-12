package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.studio.ecm.net.CircuitException;

/**
 * 推送到洇金聊天室
 */
public interface IPushToAbsorbChatroom {
    public static  final  String _KEY_ABSORB_CHATROOM="/chatroom/system/absorbs";
    void push(DepositAbsorbRecord record) throws CircuitException;

}
