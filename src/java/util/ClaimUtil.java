package util;

import java.util.Date;

public class ClaimUtil {
    private String status;
    private Date startDate;
    private Date endDate;
    private int pageNo;
    private int pageSize;
    private int count;
    private String create_sn;
    private String next_sn;

    public String getCreate_sn() {
        return create_sn;
    }

    public void setCreate_sn(String create_sn) {
        this.create_sn = create_sn;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNext_sn() {
        return next_sn;
    }

    public void setNext_sn(String next_sn) {
        this.next_sn = next_sn;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
