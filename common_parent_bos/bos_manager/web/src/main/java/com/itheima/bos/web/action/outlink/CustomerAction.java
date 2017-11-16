package com.itheima.bos.web.action.outlink;

import com.itheima.base.domain.outlink.Customer;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.web.action.BaseAction;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 20:58 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class CustomerAction extends BaseAction<Customer> {
    static {
        customerList   = (ArrayList<Customer>) WebClient.create("http://localhost:8020/crm/webservice/customerService/customers")
                .encoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
    }
    private static final String  remoteLocation="http://localhost:8020/crm/webservice/customerService";
    private static List<Customer> customerList;
    private final String nameAction="customerAction_";

    /**
     * 分页条件查询
     * 条件需要自己去封装
     * */
    @Action(value=nameAction+"pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员

    }

    /***
     * 查询所有能用的数据
     */
    @Action(nameAction+"findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {

    }
    /***
     * 查询所有数据
     */
    @Action(nameAction+"findAll")
    @Override
    public void findAll() {

    }
    @Action(nameAction+"find_customerByFixedAreaId")
    public void findCustomerByFixedAreaId() {
        String fixedAreaId = getModel().getFixedAreaId();
        if(StringUtils.isEmpty(fixedAreaId)){
           return;
        }
        List<Customer> list =new ArrayList<>(customerList.size()/5);
        if (customerList!=null){
            for (Customer c: customerList) {
                if(fixedAreaId.equals(c.getFixedAreaId())){
                    list.add(c);
                }
            }
        }
        list2json(list,new String[]{});
    }
    @Action(nameAction+"find_customerByFixedAreadIdIsNull")
    public void find_customerByFixedAreadIdIsNull() {
        List<Customer> list =new ArrayList<>(customerList.size()/5);
        if (customerList!=null){
            for (Customer c: customerList) {
                if(c.getFixedAreaId()==null){
                    list.add(c);
                }
            }
        }
        list2json(list,new String[]{});
    }

    /**
     * 保存对象
     * */
    @Action(value=nameAction+"save",
            results = {@Result(name="success",location ="pages/base/area.html",type="redirect" )})
    @Override
    public String save() {

        return SUCCESS;
    }

    List<Long> customerIds= new ArrayList<>(0);

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }
    @Action(value=nameAction+"assignCustomers2FixedArea",
            results = {@Result(name="success",location ="pages/base/fixed_area.html",type="redirect" ),
                    @Result(name="error",location ="pages/base/fixed_area.html",type="redirect" )})
    public String assignCustomers2FixedArea() {
        String fixedAreaId = getModel().getFixedAreaId();
        if(StringUtils.isEmpty(fixedAreaId)){
            return ERROR;
        }
        for (Customer c: customerList) {
            boolean flag=true;
            if(fixedAreaId.equals(c.getFixedAreaId())){
                for (Long cid: customerIds) {
                    if(cid.equals(c.getId())){
                        customerIds.remove(cid);
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    c.setFixedAreaId(null);
                    updateCustomer(c);
                }
            }
        }
        List<Customer> list2AddFixedId =new ArrayList<>();
        for (Customer c: customerList) {
            for (Long cid: customerIds) {
                if(c.getId().equals(cid)){
                    c.setFixedAreaId(fixedAreaId);
                    updateCustomer(c);
                }
            }
        }
        return SUCCESS;
    }

    /***
     * 私有方法
     */

    private void updateCustomer(Customer customer){
        WebClient.create(remoteLocation+"/customer")
                .encoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(customer);
    }
    private Specification<Customer> specification() {
        return  null;
    }
}
