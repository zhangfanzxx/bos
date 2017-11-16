package com.itheima;

import com.itheima.utils.MailUtils;
import com.itheima.utils.SmsUtils;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 22:20 <br/>
 * Author zzff
 */
@Component
public class Consumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            // 如果是文本消息
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;

            }
            // 如果是Map消息
            if (message instanceof MapMessage) {
                MapMessage mm = (MapMessage) message;
                String head = mm.getString("head");
                if("mail".equals(head)){
                    String mail = mm.getString("mail");
                    String msg = mm.getString("msg");
                    MailUtils.sendMail(mail,msg);
                }else if("sms".equals(head)){
                    String telephone = mm.getString("telephone");
                    String msg = mm.getString("msg");
                    SmsUtils.sendSmsByHTTP(telephone,msg);
                }
            }
            // 如果是Object消息
            if (message instanceof ObjectMessage) {
                ObjectMessage om = (ObjectMessage) message;
            }
            // 如果是bytes消息
            if (message instanceof BytesMessage) {
                byte[] b = new byte[1024];
                int len = -1;
                BytesMessage bm = (BytesMessage) message;
                    while ((len = bm.readBytes(b)) != -1) {
                    }
            }

            // 如果是Stream消息
            if (message instanceof StreamMessage) {
                StreamMessage sm = (StreamMessage) message;
                System.out.println(sm.readString());
                System.out.println(sm.readInt());
            }
        } catch (Exception e) {

        }

    }
}
