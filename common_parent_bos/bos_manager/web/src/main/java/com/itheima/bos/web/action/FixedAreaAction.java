package com.itheima.bos.web.action;

import com.itheima.base.domain.FixedArea;
import com.itheima.bos.service.FixedAreaService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 20:43 <br/>
 * Author zzff
 */
public class FixedAreaAction  extends  BaseAction<FixedArea>{
    @Autowired
    private FixedAreaService service;
    private final String nameAction="fixedAreaAction_";

    private Long courierId;
    private Long takeTimeId;

    /**
     * 分页条件查询
     * 条件需要自己去封装
     * */
    @Action(value=nameAction+"pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员
        Specification<FixedArea> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<FixedArea> page =service.pageQuery(specification,pageable);
        page2json(page,new String[]{"subareas","couriers"});
    }
    /***
     * 查询所有能用的数据
     */
    @Action(nameAction+"findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<FixedArea> list =service.findAllIsCanUse();
        list2json(list,new String[]{""});
    }
    /***
     * 查询所有数据
     */
    @Action(nameAction+"findAll")
    @Override
    public void findAll() {
        List<FixedArea> list = service.findAll();
        list2json(list,new String[]{""});
    }


    @Action(value = "fixedAreaAction_associationCourierToFixedArea", results = {
            @Result(name = "success", location = "pages/base/fixed_area.html", type = "redirect") })
    public void associationCourierToFixedArea() throws IOException {
        service.associationCourierToFixedArea(getModel().getId(), courierId, takeTimeId);
    }
    /**
     * 保存对象
     * */
    @Action(value=nameAction+"save",
            results = {@Result(name="success",location ="pages/base/fixed_area.html",type="redirect" )})
    @Override
    public String save() {
        service.save(getModel());
        return SUCCESS;
    }



        /**********
         * get/set 区域
         * */
     public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public void setTakeTimeId(Long takeTimeId) {
        this.takeTimeId = takeTimeId;
    }

    /*  私有方法    */
    private Specification<FixedArea> specification() {
        return  null;
    }


}
