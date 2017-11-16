package com.itheima.bos.web.action.take_delivery;

import com.itheima.base.domain.Area;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.service.take_delivery.WayBillService;
import com.itheima.bos.web.action.BaseAction;
import com.itheima.take_delivery.domain.Order;
import com.itheima.take_delivery.domain.WayBill;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

import java.io.IOException;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 9:46 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class WayBillAction extends BaseAction<WayBill> {
    @Autowired
    private WayBillService service;

    private final String nameAction = "wayBillAction_";

    /**
     * 分页条件查询
     * 条件需要自己去封装
     */
    @Action(value = nameAction + "pageQuery")
    @Override
    public void pageQuery() { // 分页查询所有的快递员
        Specification<WayBill> specification = specification();
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<WayBill> page = service.pageQuery(specification, pageable);
        page2json(page, new String[]{""});
    }


    /***
     * 查询所有能用的数据
     */
    @Action(nameAction + "findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<WayBill> list = service.findAllIsCanUse();
        list2json(list, new String[]{""});
    }

    /***
     * 查询所有数据
     */
    @Action(nameAction + "findAll")
    @Override
    public void findAll() {
        List<WayBill> list = service.findAll();
        list2json(list, new String[]{""});
    }

    /**
     * 保存对象
     */
    @Action(value = nameAction + "save")
    @Override
    public String save() throws IOException {
        String returnStr="NO";
        try {
            service.save(getModel());
            returnStr="OK";
        } catch (Exception e) {

        }
        getResponse().getWriter().write(returnStr);
        return NONE;
    }
    @Action(nameAction+"findByWayBillNum")
    public void findByWayBillNum() {
        WayBill wayBill = service.findByWayBillNum(getModel().getWayBillNum());
        object2Json(wayBill,new String[]{"subareas"});
    }

    /***
     * 私有方法
     */
    private Specification<WayBill> specification() {
        return null;
    }
}
