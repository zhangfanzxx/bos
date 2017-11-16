package com.itheima.bos.web.action;

import com.itheima.base.domain.Area;
import com.itheima.bos.service.AreaService;
import com.itheima.utils.PinYin4jUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.poi.POIDocument;
import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import javax.persistence.Column;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 19:52 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class AreaAction extends  BaseAction<Area>{
    @Autowired
    private AreaService service;
    private final String nameAction="areaAction_";

    /**
     * 分页条件查询
     * 条件需要自己去封装
     * */
    @Action(value=nameAction+"pageQuery")
    @Override
    public void pageQuery(){ // 分页查询所有的快递员
        Specification<Area> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<Area> page =service.pageQuery(specification,pageable);
        page2json(page,new String[]{"subareas"});
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
        List<Area> list;
        if(StringUtils.isEmpty(q)){
            list   = service.findAll();
        }else{
            list   = service.findAllByQ(q);
        }
        list2json(list,new String[]{"subareas"});
    }

    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public String save(){
        return null;
    }

    /**
     * 保存对象
     * */
    @Action(value=nameAction+"upload",
            results = {@Result(name="success",location ="pages/base/area.html",type="redirect" )})
    public String upload() throws Exception {
        HSSFWorkbook work=new HSSFWorkbook(new FileInputStream(upload));
        HSSFSheet sheet = work.getSheetAt(0);
        List<Area> list =new ArrayList<>(100);
        for (Row row:sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            String province = row.getCell(1).getStringCellValue(); // 省
            String city = row.getCell(2).getStringCellValue();     // 城市
            String district = row.getCell(3).getStringCellValue();  // 区域
            String postcode = row.getCell(4).getStringCellValue(); //邮编

            String citycode=  PinYin4jUtils.hanziToPinyin(city.substring(0,city.length()-1),"").toUpperCase(); // 城市编码
            String[] head = PinYin4jUtils.getHeadByString(province.substring(0, province.length() - 1) +
                    city.substring(0, city.length() - 1) + district.substring(0, district.length() - 1));// 简码
            StringBuilder sb=new StringBuilder();
            for (String str:head) {
                sb.append(str);
            }
            String shortcode=sb.toString().toUpperCase();
            Area area =new Area(province, city, district, postcode, citycode, shortcode);
            list.add(area);
        }
        service.save(list);
        return SUCCESS;
    }
    /***
     * 私有方法
     */
    private Specification<Area> specification() {
        return  null;
    }
}
