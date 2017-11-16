package com.itheima.bos.service.impl;

import com.itheima.base.domain.Area;
import com.itheima.bos.dao.AreaRepository;
import com.itheima.bos.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 19:54 <br/>
 * Author zzff
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Page<Area> pageQuery(Specification<Area> specification, Pageable pageable) {
        return areaRepository.findAll(specification,pageable);
    }

    @Override
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public void save(List<Area> list) {
        areaRepository.save(list);
    }

    @Override
    public List<Area> findAllByQ(String q) {
        return areaRepository.findByQ("%"+q+"%");
    }


}
