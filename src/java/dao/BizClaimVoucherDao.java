package dao;

import entity.BizCheckResult;
import entity.BizClaimVoucher;
import entity.BizClaimVoucherDetail;
import entity.SysEmployee;
import util.ClaimUtil;

import java.util.List;

public interface BizClaimVoucherDao {
    public List<BizClaimVoucher> query(ClaimUtil claimUtil, SysEmployee sysEmployee);
    public int getCount(ClaimUtil claimUtil, SysEmployee sysEmployee);
    public void addClaimDetail(BizClaimVoucherDetail bizClaimVoucherDetail);
    public void addClaim(BizClaimVoucher bizClaimVoucher);
    public BizClaimVoucher getClaim(long id);
    public void updateClaim(BizClaimVoucher bizClaimVoucher);
    public void delClaimDetail(BizClaimVoucherDetail bizClaimVoucherDetail);
    public SysEmployee getEmpByPid(long pid);
    public void delClaim(BizClaimVoucher bizClaimVoucher);
    public List<BizClaimVoucherDetail> getClaimDetailByMainID(BizClaimVoucher bizClaimVoucher);
    public void addCheckResult(BizCheckResult bizCheckResult);
}
