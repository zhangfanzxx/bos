package com.itheima.bos.service.system.impl;

import com.itheima.bos.dao.system.MenuRepostiry;
import com.itheima.bos.service.system.MenuService;
import com.itheima.system.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:com.itheima.bos.service.system.impl <br/>
 * Function: <br/>
 * Date:2017/11/15 18:15 <br/>
 * Author zzff
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    private static List<Menu> list;
    private static List<Menu> allMenu;
    @Autowired
    private MenuRepostiry menuRepostiry;
    @Override
    public void save(Menu model) {
        menuRepostiry.save(model);
        allMenu.add(model);
        Menu parentMenu = model.getParentMenu();
        if(parentMenu==null){
            list.add(model);
        }else if(list.contains(parentMenu)){
            int index = list.indexOf(parentMenu);
            list.get(index).getChildrenMenus().add(model);
        }else{
            list=menuRepostiry.findByPageIsNull();
        }
    }

    @Override
    public List<Menu> findAll() {
        if(allMenu==null){
            allMenu=menuRepostiry.findAll();
        }
        return allMenu;
    }

    @Override
    public Page<Menu> pageQuery(Specification<Menu> specification, Pageable pageable) {
        return menuRepostiry.findAll(specification,pageable);
    }

    @Override
    public List<Menu> findAllIsCanUse() {
        return null;
    }

    @Override
    public List<Menu> findMenuParent() {
        if(list==null){
            list = menuRepostiry.findByPageIsNull();
        }
        return list;
    }
}
