package dao;

import entity.SysEmployee;

import java.util.List;

public interface SysEmployeeDao {
    public List<SysEmployee> login(SysEmployee sysEmployee);
}
