package com.itheima.bos.web.action;

import com.itheima.base.domain.TakeTime;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.service.TakeTimeService;
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
 * Date:2017/11/11 10:24 <br/>
 * Author zzff
 */

@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class TakeTimeAction extends BaseAction<TakeTime> {
    @Autowired
    private TakeTimeService service;
    private final String nameAction="takeTimeAction_";



    /**
     * 分页条件查询
     * 条件需要自己去封装
     * */
    @Action(value=nameAction+"pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员
        Specification<TakeTime> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<TakeTime> page =service.pageQuery(specification,pageable);
        page2json(page,new String[]{""});
    }


    /***
     * 查询所有能用的数据
     */
    @Action(nameAction+"findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<TakeTime> list =service.findAllIsCanUse();
        list2json(list,new String[]{""});
    }
    /***
     * 查询所有数据
     */
    @Action(nameAction+"findAll")
    @Override
    public void findAll() {
        List<TakeTime> list = service.findAll();
        list2json(list,new String[]{""});
    }
    /**
     * 保存对象
     * */
    @Action(value=nameAction+"save",
            results = {@Result(name="success",location ="pages/base/take_time.html",type="redirect" )})
    @Override
    public String save() {
        service.save(getModel());
        return SUCCESS;
    }

    String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Action(value=nameAction+"delTakeTime",
            results = {@Result(name="success",location ="pages/base/take_time.html",type="redirect" )})
    public String delTakeTime() {
        service.delTakeTime(ids);
        return SUCCESS;
    }


    /***
     * 私有方法
     */
    private Specification<TakeTime> specification() {
        return  null;
    }
}
