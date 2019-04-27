package entity;

import java.util.Collection;

public class SysPosition {
    private long id;
    private String nameCn;
    private String nameEn;
    private Collection<SysEmployee> sysEmployeesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPosition that = (SysPosition) o;

        if (id != that.id) return false;
        if (nameCn != null ? !nameCn.equals(that.nameCn) : that.nameCn != null) return false;
        if (nameEn != null ? !nameEn.equals(that.nameEn) : that.nameEn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nameCn != null ? nameCn.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        return result;
    }

    public Collection<SysEmployee> getSysEmployeesById() {
        return sysEmployeesById;
    }

    public void setSysEmployeesById(Collection<SysEmployee> sysEmployeesById) {
        this.sysEmployeesById = sysEmployeesById;
    }
}
