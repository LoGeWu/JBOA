package service.impl;

import dao.SysEmployeeDao;
import entity.SysEmployee;
import service.SysEmployeeService;

import java.util.List;

public class SysEmployeeServiceImpl implements SysEmployeeService {
    private SysEmployeeDao sysEmployeeDao;
    @Override
    public SysEmployee login(SysEmployee sysEmployee) {
        List<SysEmployee> list=sysEmployeeDao.login(sysEmployee);
        if (list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;
    }

    public SysEmployeeDao getSysEmployeeDao() {
        return sysEmployeeDao;
    }

    public void setSysEmployeeDao(SysEmployeeDao sysEmployeeDao) {
        this.sysEmployeeDao = sysEmployeeDao;
    }
}
