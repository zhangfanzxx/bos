package com.itheima.bos.web.action;

import com.itheima.base.domain.Area;
import com.itheima.base.domain.FixedArea;
import com.itheima.base.domain.SubArea;
import com.itheima.bos.service.SubAreaService;
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

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 11:37 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class SubAreaAction extends BaseAction<SubArea> {
    @Autowired
    private SubAreaService service;
    private final String nameAction="subAreaAction_";

    /**
     * 分页条件查询
     * 条件需要自己去封装
     * */
    @Action(value=nameAction+"pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员
        Specification<SubArea> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<SubArea> page =service.pageQuery(specification,pageable);

        page2json(page,new String[]{"fixedArea","subareas"});
    }


    /***
     * 查询所有能用的数据
     */
    @Action(nameAction+"findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<SubArea> list =service.findAllIsCanUse();
        list2json(list,new String[]{"fixedArea","subareas"});
    }
    /***
     * 查询所有数据
     */
    @Action(nameAction+"findAll")
    @Override
    public void findAll() {
        List<SubArea> list = service.findAll();
        list2json(list,new String[]{"fixedArea","subareas"});
    }
    /**
     * 保存对象
     * */
    @Action(value=nameAction+"save",
            results = {@Result(name="success",location ="pages/base/sub_area.html",type="redirect" )})
    @Override
    public String save() {
        service.save(getModel());
        return SUCCESS;
    }

    @Action(nameAction+"find_subAreaByFixedAreadIdIsNull")
    public void find_subAreaByFixedAreadIdIsNull() {
        List<SubArea> list = service.find_subAreaByFixedAreaIdIsNull();
        list2json(list,new String[]{"fixedArea","subareas"});
    }
    Long fixedAreaId=0L;

    public void setFixedAreaId(Long fixedAreaId) {
        this.fixedAreaId = fixedAreaId;
    }

    @Action(nameAction+"find_subAreaByFixedAreaId")
    public void find_subAreaByFixedAreaId() {
        List<SubArea> list = service.find_subAreaByFixedAreaId(fixedAreaId);
        list2json(list,new String[]{"fixedArea","subareas"});
    }
    public void setFixedAreaId(long fixedAreaId) {
        this.fixedAreaId = fixedAreaId;
    }

    List<Long>subAreaIds=new ArrayList<>(0);
    public void setSubAreaIds(List<Long> subAreaIds) { // 保存分区
        this.subAreaIds = subAreaIds;
    }

    @Action(value=nameAction+"associationSubAreaToFixedArea",
            results = {@Result(name="success",location ="pages/base/fixed_area.html",type="redirect" )})
    public String associationSubAreaToFixedArea() {
        service.associationSubAreaToFixedArea(fixedAreaId,subAreaIds);


        return SUCCESS;
    }

    /***
     * 私有方法
     */

    private Specification<SubArea> specification() {
        return new Specification<SubArea>() {
            @Override
            public Predicate toPredicate(Root<SubArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<>();
                Area area = getModel().getArea();
                String keyWords = getModel().getKeyWords();
                FixedArea fixedArea = getModel().getFixedArea();
                if(!StringUtils.isEmpty(keyWords)){
                    list.add(cb.like(root.get("keyWords").as(String.class),"%"+keyWords+"%"));
                }
                if (!StringUtils.isEmpty(fixedArea)&&!StringUtils.isEmpty(fixedArea.getId())){
                    Join<Object,Object> join =root.join("fixedArea");
                    list.add(cb.equal(join.get("id").as(Long.class),fixedArea.getId()));
                }
                if(!StringUtils.isEmpty(area)){
                    if(!StringUtils.isEmpty(area.getCity())){
                        Join<Object,Object> join =root.join("area");
                        list.add(cb.like(join.get("city").as(String.class),"%"+area.getCity()+"%"));
                    }
                    if(!StringUtils.isEmpty(area.getProvince())){
                        Join<Object,Object> join =root.join("area");
                        list.add(cb.like(join.get("province").as(String.class),"%"+area.getProvince()+"%"));
                    }
                    if(!StringUtils.isEmpty(area.getDistrict())){
                        Join<Object,Object> join =root.join("area");
                        list.add(cb.like(join.get("district").as(String.class),"%"+area.getDistrict()+"%"));
                    }
                }
                if(list.size()==0){
                    return null;
                }
                Predicate[] array = new Predicate[list.size()];
                list.toArray(array);
                // 构造查询条件
                return cb.and(array);
            }
        };
    }
}
