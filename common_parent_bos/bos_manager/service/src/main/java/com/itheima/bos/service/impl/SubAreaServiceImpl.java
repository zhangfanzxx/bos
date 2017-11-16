package com.itheima.bos.service.impl;

import com.itheima.base.domain.FixedArea;
import com.itheima.base.domain.SubArea;
import com.itheima.bos.dao.SubAreaRepository;
import com.itheima.bos.service.SubAreaService;
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
 * Date:2017/11/10 11:39 <br/>
 * Author zzff
 */
@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
    @Autowired
    private SubAreaRepository subAreaRepository;

    @Override
    public List<SubArea> findAll() {
        return subAreaRepository.findAll();
    }

    @Override
    public List<SubArea> findAllIsCanUse() {
        return null;
    }



    @Override
    public Page<SubArea> pageQuery(Specification<SubArea> specification, Pageable pageable) {
        return subAreaRepository.findAll(specification,pageable);
    }

    @Override
    public void save(SubArea model) {
        subAreaRepository.save(model);
    }

    @Override
    public List<SubArea> find_subAreaByFixedAreaIdIsNull() {
        return subAreaRepository.findByFixedAreaIsNull();
    }

    @Override
    public List<SubArea> find_subAreaByFixedAreaId(Long fixedAreaId) {
        FixedArea fixedArea =new FixedArea();
        fixedArea.setId(fixedAreaId);
        return subAreaRepository.findByFixedArea(fixedArea);
    }

    @Override
    public void associationSubAreaToFixedArea(Long fixedAreaId, List<Long> subAreaIds) {
        FixedArea fixedArea =new FixedArea();
        fixedArea.setId(fixedAreaId);
        subAreaRepository.setSubAreaFixedAreaBeNullByFixedArea(fixedArea);

        for (Long sid: subAreaIds) {
            subAreaRepository.updateSubAreaByFixedAreaAndSubAreaId(fixedArea,sid);
        }
    }
}
