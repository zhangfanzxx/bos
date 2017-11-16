package com.itheima.bos.web.action;

import com.itheima.base.domain.FixedArea;
import com.itheima.base.domain.GoodType;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.service.GoodTypeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 19:02 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class GoodTypeAction extends BaseAction<GoodType> {

    @Autowired
    private GoodTypeService service;
    private final String nameAction="goodTypeAction_";

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
        List<GoodType> list = service.findAll();
        list2json(list,new String[]{""});
    }
    /**
     * 保存对象
     * */
    @Action(value=nameAction+"save",
            results = {@Result(name="success",location ="pages/base/area.html",type="redirect" )})
    @Override
    public String save() {
        service.save(getModel());
        return SUCCESS;
    }
    /***
     * 私有方法
     */
    private Specification<GoodType> specification() {
        return  null;
    }
}
