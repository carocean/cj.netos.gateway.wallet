package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.model.DepositTrialRecord;
import cj.studio.ecm.net.CircuitException;

/**
 * 推送到洇金聊天室
 */
public interface IPushToAbsorbChatroom {
    public static  final  String _KEY_ABSORB_CHATROOM="/chatroom/system/absorbs";
    public static  final  String _KEY_TRIAL_CHATROOM="/chatroom/system/trials";
    void pushAbsorb(DepositAbsorbRecord record) throws CircuitException;

    void pushTrial(DepositTrialRecord record) throws CircuitException;

}
