package service.impl;

import dao.BizClaimVoucherDao;
import entity.BizCheckResult;
import entity.BizClaimVoucher;
import entity.BizClaimVoucherDetail;
import entity.SysEmployee;
import service.BizClaimVoucherService;
import util.ClaimUtil;

import java.util.Iterator;
import java.util.List;
public class BizClaimVoucherServiceImpl implements BizClaimVoucherService {
    private BizClaimVoucherDao bizClaimVoucherDao;


    public BizClaimVoucherDao getBizClaimVoucherDao() {
        return bizClaimVoucherDao;
    }

    public void setBizClaimVoucherDao(BizClaimVoucherDao bizClaimVoucherDao) {
        this.bizClaimVoucherDao = bizClaimVoucherDao;
    }
    @Override
    public List<BizClaimVoucher> query(ClaimUtil claimUtil, SysEmployee sysEmployee) {
        return bizClaimVoucherDao.query(claimUtil,sysEmployee);
    }

    @Override
    public int getCount(ClaimUtil claimUtil, SysEmployee sysEmployee) {
        return bizClaimVoucherDao.getCount(claimUtil,sysEmployee);
    }

    /**
     * 增加报销单
     * @param bizClaimVoucher
     */
    @Override
    public void addClaim(BizClaimVoucher bizClaimVoucher) {
        bizClaimVoucherDao.addClaim(bizClaimVoucher);
    }

    /**
     * 增加报销单明细
     * @param bizClaimVoucher
     */
    @Override
    public void addClaimDetails(BizClaimVoucher bizClaimVoucher) {
        if (bizClaimVoucher!=null){
            Iterator<BizClaimVoucherDetail> it=bizClaimVoucher.getBizClaimVoucherDetailsById().iterator();
            while(it.hasNext()) {
                BizClaimVoucherDetail bizClaimVoucherDetail = it.next();
                bizClaimVoucherDetail.setBizClaimVoucherByMainId(bizClaimVoucher);
                bizClaimVoucherDao.addClaimDetail(bizClaimVoucherDetail);
            }
        }
    }

    @Override
    public BizClaimVoucher getClaim(long id) {
        return bizClaimVoucherDao.getClaim(id);
    }

    @Override
    public void updateClaim(BizClaimVoucher bizClaimVoucher) {
        bizClaimVoucherDao.updateClaim(bizClaimVoucher);
    }

    @Override
    public SysEmployee getEmpByPid(long pid) {
        return bizClaimVoucherDao.getEmpByPid(pid);
    }

    @Override
    public void updateClaimDetail(BizClaimVoucher bizClaimVoucher,List<BizClaimVoucherDetail> bizClaimVoucherDetails) {

        if (bizClaimVoucher!=null){
            Iterator<BizClaimVoucherDetail> it=bizClaimVoucher.getBizClaimVoucherDetailsById().iterator();
            while(it.hasNext()) {
                BizClaimVoucherDetail bizClaimVoucherDetail = it.next();
                bizClaimVoucherDao.delClaimDetail(bizClaimVoucherDetail);
            }
        }
        for (int i = 0; i <bizClaimVoucherDetails.size() ; i++) {
            bizClaimVoucherDao.addClaimDetail(bizClaimVoucherDetails.get(i));
        }
    }

    @Override
    public void delClaim(BizClaimVoucher bizClaimVoucher) {
        if (bizClaimVoucher!=null){
            Iterator<BizClaimVoucherDetail> it=bizClaimVoucher.getBizClaimVoucherDetailsById().iterator();
            List<BizClaimVoucherDetail> list=null;
            list=bizClaimVoucherDao.getClaimDetailByMainID(bizClaimVoucher);
            for (int i = 0; i < list.size(); i++) {
                bizClaimVoucherDao.delClaimDetail(list.get(i));
            }
            bizClaimVoucherDao.delClaim(bizClaimVoucher);
        }

    }

    @Override
    public void check(BizCheckResult bizCheckResult, BizClaimVoucher bizClaimVoucher) {
        bizClaimVoucherDao.updateClaim(bizClaimVoucher);
        bizClaimVoucherDao.addCheckResult(bizCheckResult);
    }


}
