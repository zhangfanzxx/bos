package com.itheima.bos.web.action.take_delivery;

import com.itheima.bos.service.take_delivery.OrderService;
import com.itheima.bos.web.action.BaseAction;
import com.itheima.take_delivery.domain.Order;
import com.itheima.take_delivery.domain.WayBill;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 15:31 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class OrderAction extends BaseAction<Order> {
    @Autowired
    private OrderService service;
    private final String nameAction="orderAction_";

    /**
     * 分页条件查询
     * 条件需要自己去封装
     * */
    @Action(value=nameAction+"pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员
        Specification<Order> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<Order> page =service.pageQuery(specification,pageable);
        page2json(page,new String[]{""});
    }

    /***
     * 查询所有能用的数据
     */
    @Action(nameAction+"findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<Order> list =service.findAllIsCanUse();
        list2json(list,new String[]{""});
    }
    /***
     * 查询所有数据
     */
    @Action(nameAction+"findAll")
    @Override
    public void findAll() {
        List<Order> list = service.findAll();
        list2json(list,new String[]{""});
    }
    @Action(nameAction+"findByOrderNum")
    public void findByOrderNum() {
        Order order = service.findByOrderNum(getModel().getOrderNum());
        object2Json(order,new String[]{"fixedAreas","workBills","subareas"});
    }
    /**
     * 保存对象
     * */
    @Override
    public String save() {
        //service.addOrder(getModel());
        return SUCCESS;
    }
    /***
     * 私有方法
     */
    private Specification<Order> specification() {
        return  null;
    }
}
