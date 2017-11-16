import com.itheima.utils.MailUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import javax.mail.MessagingException;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/12 9:13 <br/>
 * Author zzff
 */
public class MailTest {

    @Test
    public void test1() throws MessagingException {
        MailUtils.sendMail("475083976@qq.com","屠龙宝刀点击就送是一封激活邮件，请<a href='http://www.baidu.com'>点击</a>");
    }
    @Test
    public void test2() throws MessagingException {
        //MailUtils.sendMail("475083976@qq.com","屠龙宝刀点击就送是一封激活邮件，请<a href='http://www.baidu.com'>点击</a>");
        System.out.println(RandomStringUtils.randomNumeric(32));
    }
}
