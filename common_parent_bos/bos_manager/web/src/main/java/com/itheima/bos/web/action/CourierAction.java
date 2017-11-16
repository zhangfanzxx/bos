package com.itheima.bos.web.action;

import com.itheima.base.domain.Courier;
import com.itheima.base.domain.Standard;
import com.itheima.bos.service.CourierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 11:26 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class CourierAction extends BaseAction<Courier> {
    @Autowired
    private CourierService courierService;


    @Action(value="courierAction_pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员
        Specification<Courier> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<Courier> page =courierService.pageQuery(specification,pageable);
        page2json(page,new String[]{"fixedAreas"});
    }

    @Action("courierAction_findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<Courier> list = courierService.findAllIsCanUse();
        list2json(list,new String[]{"fixedAreas"});
    }
    @Action("courierAction_findAll")
    @Override
    public void findAll() {
        List<Courier> list = courierService.findAll();
        list2json(list,new String[]{"fixedAreas"});
    }


    @Action(value="courierAction_save",
            results = {@Result(name="success",location ="pages/base/courier.html",type="redirect" )})
    @Override
    public String save() {
        courierService.save(getModel());
        return SUCCESS;
    }

    @Action(value="courierAction_delCouriers",
            results = {@Result(name="success",location ="pages/base/courier.html",type="redirect" )})
    public String delCouriers() {
        Character deltag = getModel().getDeltag();

        if(deltag==null||deltag==1){
            deltag=null;
        }
        courierService.delCouriers(ids,deltag);
        return SUCCESS;
    }
    String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    private Specification<Courier> specification() {
        return new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list =new ArrayList<>();
                String courierNum=getModel().getCourierNum();
                String company=getModel().getCompany();
                String type=getModel().getType();
                Character deltag=getModel().getDeltag();
                if(deltag==null||deltag=='0'){
                    deltag=null;
                }
                Standard standard = getModel().getStandard();
                if (standard != null && !StringUtils.isEmpty(standard.getId())) {
                    // 根据收派标准的名字进行级联查询
                    Join<Object, Object> join = root.join("standard");
                    list.add( cb.equal(join.get("id").as(Long.class), +standard.getId()));
                }
                if(!StringUtils.isEmpty(company)){

                    list.add( cb.like(root.get("company").as(String.class), "%"+company+"%"));
                }
                if(!StringUtils.isEmpty(type)){
                    list.add(cb.equal(root.get("type").as(String.class), type));
                }
                if(!StringUtils.isEmpty(deltag)){
                    list.add(cb.equal(root.get("deltag").as(Character.class), deltag));
                }
                if(!StringUtils.isEmpty(courierNum)){
                    list.add(cb.equal(root.get("courierNum").as(String.class), courierNum));
                }
                if(list.size()==0) {
                    return  null;
                }
                Predicate[] array = new Predicate[list.size()];
                list.toArray(array);
                // 构造查询条件
                return cb.and(array);
            }
        };
    }




}
