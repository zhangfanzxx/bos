package com.itheima.bos.web.action;

import com.itheima.base.domain.Standard;
import com.itheima.bos.service.StandardService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.prefs.NodeChangeEvent;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 10:50 <br/>
 * Author zzff
 */
@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class StandardAction extends  BaseAction<Standard> {
    @Autowired
    private StandardService standardService;

    @Action(value="standardAction_pageQuery")
    @Override
    public void pageQuery(){
        Pageable pageable =new PageRequest(page-1,rows);
        Page<Standard> pages= standardService.pageQuery(pageable);
        page2json(pages,new String[]{""});
    }

    @Override
    public void findAllIsCanUse() {

    }

    @Action(value="standardAction_findAll")
    @Override
    public void findAll(){
        List<Standard>list = standardService.findAll();
        list2json(list,new String[]{""});
    }


    @Action(value="standardAction_save",
            results = {@Result(name="success",location ="pages/base/standard.html",type="redirect" )})
    @Override
    public String save(){
        standardService.save(getModel());
       return SUCCESS;
    }

    // 作废!!
/*    @Action(value="standardAction_changeDeltag",
            results = {@Result(name="success",location ="pages/base/standard.html",type="rederect" )})
    public String changeDeltag(){
        standardService.changeDeltag(getModel())
        return SUCCESS;
    }*/
}
