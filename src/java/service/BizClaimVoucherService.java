package service;

import entity.BizCheckResult;
import entity.BizClaimVoucher;
import entity.BizClaimVoucherDetail;
import entity.SysEmployee;
import util.ClaimUtil;

import java.util.List;

public interface BizClaimVoucherService {
    public List<BizClaimVoucher> query(ClaimUtil claimUtil, SysEmployee sysEmployee);
    public int getCount(ClaimUtil claimUtil, SysEmployee sysEmployee);
    public void addClaim(BizClaimVoucher bizClaimVoucher);
    public void addClaimDetails(BizClaimVoucher bizClaimVoucher);
    public BizClaimVoucher getClaim(long id);
    public void updateClaim(BizClaimVoucher bizClaimVoucher);
    public SysEmployee getEmpByPid(long pid);
    public void updateClaimDetail(BizClaimVoucher bizClaimVoucher,List<BizClaimVoucherDetail> bizClaimVoucherDetails);
    public void delClaim(BizClaimVoucher bizClaimVoucher);
    public void check(BizCheckResult bizCheckResult,BizClaimVoucher bizClaimVoucher);
}
