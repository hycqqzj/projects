package com.hyc.service;

import com.hyc.dao.DepartmentMapper;
import com.hyc.entity.Department;
import com.hyc.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public Department findById(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public Department add(Department department) {
        departmentMapper.insertSelective(department);
        return department;
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void setDeptManager(String empCode, String deptCode) {
        Department department = departmentMapper.selectByCode(deptCode);
        if(department == null) {
            throw new BusinessException("部门不存在");
        }
        Department updateDetp = new Department();
        updateDetp.setId(department.getId());
        updateDetp.setManagerCode(empCode);
        departmentMapper.updateByPrimaryKeySelective(updateDetp);
    }
}
