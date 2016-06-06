package com.exist.service.impl;

import com.exist.dao.RoleDao;
import com.exist.dao.impl.RoleDaoImpl;
import com.exist.dao.impl.GenericDaoImpl;
import com.exist.dto.RoleDto;
import com.exist.model.Role;
import com.exist.service.RoleService;
import com.exist.service.impl.GenericServiceImpl;

public class RoleServiceImpl extends GenericServiceImpl<RoleDto, Role, Long> implements RoleService{
    private static final RoleDao roleDao = new RoleDaoImpl();
    
    public RoleServiceImpl(){
        super(roleDao);
    }
}
