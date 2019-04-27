package dao.impl;

import dao.SysEmployeeDao;
import entity.SysEmployee;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class SysEmployeeDaoImpl extends HibernateDaoSupport implements SysEmployeeDao {
    @Override
    public List<SysEmployee> login(SysEmployee sysEmployee) {
        List<SysEmployee> list=this.getHibernateTemplate().find("from SysEmployee where status='在职' and sn='"+sysEmployee.getSn()+"' and password='"+sysEmployee.getPassword()+"'");
        return list;
    }
}
