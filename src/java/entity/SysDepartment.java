package entity;

import java.util.Collection;

public class SysDepartment {
    private long id;
    private String name;
    private Collection<SysEmployee> sysEmployeesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDepartment that = (SysDepartment) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<SysEmployee> getSysEmployeesById() {
        return sysEmployeesById;
    }

    public void setSysEmployeesById(Collection<SysEmployee> sysEmployeesById) {
        this.sysEmployeesById = sysEmployeesById;
    }
}
