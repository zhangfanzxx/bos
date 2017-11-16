package com.itheima.web.action;

import com.itheima.domain.Customer;
import com.itheima.utils.MailUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static groovy.xml.Entity.reg;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 11:16 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class CustomerAction extends BaseAction<Customer>{
    static{
        InputStream asStream = CustomerAction.class.getClassLoader().getResourceAsStream("remoteAddress.properties");
        Properties properties=new Properties();
        try {
            properties.load(asStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        remoteAddress=properties.getProperty("customerAddress");
    }
    @Autowired
    private RedisTemplate redisTemplate;
    private static String remoteAddress;
    private static Map<String,Customer> customerMap;

    private String checkCode;

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
    public CustomerAction(){
        customerActionInit();
    }

    private void customerActionInit() {
        if(customerMap==null){
            Collection<? extends Customer> customers = WebClient.create(remoteAddress + "customers")
                    .encoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.APPLICATION_JSON)
                    .getCollection(Customer.class);
            customerMap=new HashMap<>(customers.size()+20);
            for (Customer c: customers) {
                customerMap.put(c.getTelephone(),c);
            }
        }
    }

    /***
     * 注册
     */
    @Action(value="customerAction_register",results = {@Result(location = "index.html",name = "success",type = "redirect"),
                @Result(location = "login.html",name = "error",type = "redirect") })
    public String register() throws MessagingException {
        String serverCode = (String) getSession().getAttribute("serverCode");
        if (serverCode==null||checkCode==null||!serverCode.equals(checkCode)){
            return ERROR;
        }
        String telephone = getModel().getTelephone();
        String reg="^[1][3,4,5,7,8][0-9]{9}$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(telephone);
        boolean b = m.matches();
        if(!b){
            return ERROR;
        }
        Customer customer = customerMap.get(telephone);
        if (customer!=null){
            return ERROR;
        }
        String code= RandomStringUtils.randomNumeric(32);
        redisTemplate.opsForValue().set(telephone,code,24, TimeUnit.DAYS);
        MailUtils.sendMail(getModel().getEmail(), "欢迎您成为我们的会员请在二十四小时内激活账号，" +
                "请<a href='"+getRequest().getContextPath()+"/customerAction_active.action?telephone="+telephone+"&?checkCode="+code+"'>点击</a>激活");
        WebClient.create(remoteAddress+"customer")
                .encoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(getModel());
        customerMap.put(telephone,getModel());
        return  SUCCESS;
    }

    @Action(value="customerAction_active",results = {@Result(location = "index.html",name = "success",type = "redirect"),
            @Result(location = "login.html",name = "error",type = "redirect") })
    public String active(){
        String telephone = getModel().getTelephone();
        if(checkCode==null||telephone==null){
            return  ERROR;
        }
        String serverCode = (String) redisTemplate.opsForValue().get(telephone);
        if(serverCode!=null&&serverCode.equals(checkCode)){
            Customer customer = customerMap.get(telephone);
            if(customer!=null&&customer.getType()!=1){
                customer.setType(1);
                WebClient.create(remoteAddress+"customer")
                        .encoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .type(MediaType.APPLICATION_JSON)
                        .post(customer);
            }
        }
        return  ERROR;
    }
    @Action(value="customerAction_login",results = {@Result(location = "index.html",name = "success",type = "redirect"),
            @Result(location = "login.html",name = "error",type = "redirect") })
    public String login(){
       //验证码
        String serverCode = (String) getSession().getAttribute("validateCode");
        if (serverCode==null){
            System.out.println("本地没有开启Cookie");
        }
        if (checkCode!=null&&checkCode.equals(serverCode)){
            // 身份验证
            Customer customer = customerMap.get(getModel().getTelephone());
            if (customer!=null&&customer.getPassword()!=null&&customer.getPassword().equals(getModel().getPassword()))
            {
                return SUCCESS;
            }
        }
        return  ERROR;
    }

    @Action(value="customerAction_getCode")
    public String getCode(){ // 生成短信验证码
        Random r =new Random();
        int i = r.nextInt(8999) + 1000;
        getSession().setAttribute("serverCode",""+i);
        System.out.println(i);
        return NONE;
    }
}
