package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IPushToAbsorbChatroom;
import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.model.DepositTrialRecord;
import cj.netos.jpush.JPushFrame;
import cj.netos.jpush.pusher.IJPusher;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.UUID;

@CjService(name = "pushToAbsorbChatroom")
public class PushToAbsorbChatroom implements IPushToAbsorbChatroom {
    @CjServiceRef(refByName = "@.jpusher")
    IJPusher pusher;

    @Override
    public void pushAbsorb(DepositAbsorbRecord record) throws CircuitException {
        ByteBuf bb = Unpooled.buffer();
        bb.writeBytes(new Gson().toJson(record).getBytes());
        JPushFrame frame = new JPushFrame("pushMessage /chat/room/message gbera/1.0", bb);

        frame.head("to-person", record.getPerson());
        frame.parameter("room", Encript.md5(_KEY_ABSORB_CHATROOM));
        frame.parameter("contentType", "/pay/absorbs");
        frame.parameter("msgid", Encript.md5(UUID.randomUUID().toString()));
        frame.parameter("ctime", System.currentTimeMillis() + "");
//        String servicer = "superadmin@system.netos";//虚拟一个平台客服账号
        frame.parameter("roomCreator", record.getPerson());//谁接收的谁就是洇取聊天室创建者
        frame.head("sender-person", record.getPerson());//必为必须字段，页record中隐含的发金人在此查的话性能太差，因此留给客户端解释，在此全当是自已发给自己的
        pusher.push(frame.copy());
    }

    @Override
    public void pushTrial(DepositTrialRecord record)throws CircuitException  {
        ByteBuf bb = Unpooled.buffer();
        bb.writeBytes(new Gson().toJson(record).getBytes());
        JPushFrame frame = new JPushFrame("pushMessage /chat/room/message gbera/1.0", bb);

        frame.head("to-person", record.getPayee());
        frame.parameter("room", Encript.md5(_KEY_TRIAL_CHATROOM));
        frame.parameter("contentType", "/pay/trials");
        frame.parameter("msgid", Encript.md5(UUID.randomUUID().toString()));
        frame.parameter("ctime", System.currentTimeMillis() + "");
//        String servicer = "superadmin@system.netos";//虚拟一个平台客服账号
        frame.parameter("roomCreator", record.getPayee());//付款人是聊天室创建者
        frame.head("sender-person", record.getPayer());//必为必须字段
        pusher.push(frame.copy());
    }
}
