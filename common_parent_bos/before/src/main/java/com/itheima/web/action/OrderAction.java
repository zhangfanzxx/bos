package com.itheima.web.action;

import com.itheima.domain.Order;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.MediaType;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 18:15 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class OrderAction  extends ActionSupport implements ModelDriven<Order>{
    private Order order;
    @Override
    public Order getModel() {
        if(order==null){
            order=new Order();
        }
        return order;
    }
   @Action(value="orderAction_save",results ={@Result(name = "success",location = "index.html",type = "redirect")})
    public String save(){
        order.getId();
       WebClient.create("http://localhost:8010/bos_manager/webservice/orderservice/order")
               .encoding("utf-8")
               .accept(MediaType.APPLICATION_JSON)
               .type(MediaType.APPLICATION_JSON)
               .post(order);
       return SUCCESS;
    }
}
