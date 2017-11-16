import com.itheima.utils.PinYin4jUtils;
import org.junit.Test;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 10:08 <br/>
 * Author zzff
 */
public class PinYin4JTest {

    @Test
    public void tests(){
        String city="北京市";
        String citycode=  PinYin4jUtils.hanziToPinyin(city.substring(0,city.length()-1),"");
        System.out.println(citycode.toUpperCase());
        String[] string = PinYin4jUtils.getHeadByString("北京北京天安门", false);
        for (String s: string
             ) { System.out.print(s.toUpperCase());

        }

    }
}
