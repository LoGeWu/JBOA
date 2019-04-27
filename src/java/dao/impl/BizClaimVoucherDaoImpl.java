package dao.impl;

import dao.BizClaimVoucherDao;
import entity.BizCheckResult;
import entity.BizClaimVoucher;
import entity.BizClaimVoucherDetail;
import entity.SysEmployee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import util.ClaimUtil;

import java.util.List;

public class BizClaimVoucherDaoImpl extends HibernateDaoSupport implements BizClaimVoucherDao {
    @Override
    public List<BizClaimVoucher> query(ClaimUtil claimUtil, SysEmployee sysEmployee) {
       StringBuilder hql=new StringBuilder("from BizClaimVoucher where 1=1");
       if (sysEmployee.getSysPositionByPositionId().getId()==1){
           hql.append(" and sysEmployeeByCreateSn.sn = :create_sn");
       }else if (sysEmployee.getSysPositionByPositionId().getId()!=1){
           hql.append(" and sysEmployeeByNextDealSn.sn = :create_sn");
       }
       if (claimUtil.getStatus()!=null&&!"".equals(claimUtil.getStatus())){
           hql.append(" and status = :status");
       }
        if (claimUtil.getStartDate()!=null&&!"".equals(claimUtil.getStartDate())){
            hql.append(" and createTime > :startDate");
        }
        if (claimUtil.getEndDate()!=null&&!"".equals(claimUtil.getEndDate())){
            hql.append(" and createTime < :endDate");
        }
        hql.append(" order by id desc");
        Session session = null;
         session = this.getHibernateTemplate().getSessionFactory().openSession();
         Query hqlQuery = session.createQuery(hql.toString());
         hqlQuery.setProperties(claimUtil);

        if (claimUtil.getPageNo()!=0&&claimUtil.getPageSize()!=0){
            hqlQuery.setFirstResult((claimUtil.getPageNo()-1)*claimUtil.getPageSize());
            hqlQuery.setMaxResults(claimUtil.getPageSize());
        }


        return hqlQuery.list();
    }

    /**
     * 得到查询记录数
     * @param claimUtil
     * @param sysEmployee
     * @return
     */
    @Override
    public int getCount(ClaimUtil claimUtil, SysEmployee sysEmployee) {
        StringBuilder hql=new StringBuilder("from BizClaimVoucher where 1=1");
        if (sysEmployee.getSysPositionByPositionId().getId()==1){
            hql.append(" and sysEmployeeByCreateSn.sn = :create_sn");
        }else if (sysEmployee.getSysPositionByPositionId().getId()!=1){
            hql.append(" and sysEmployeeByNextDealSn.sn = :create_sn");
        }
        if (claimUtil.getStatus()!=null&&!"".equals(claimUtil.getStatus())){
            hql.append(" and status = :status");
        }
        if (claimUtil.getStartDate()!=null&&!"".equals(claimUtil.getStartDate())){
            hql.append(" and createTime > :startDate");
        }
        if (claimUtil.getEndDate()!=null&&!"".equals(claimUtil.getEndDate())){
            hql.append(" and createTime < :endDate");
        }

        Session session = null;
        session = this.getHibernateTemplate().getSessionFactory().openSession();
        Query hqlQuery = session.createQuery(hql.toString());
        hqlQuery.setProperties(claimUtil);
        return hqlQuery.list().size();
    }

    /**
     * 添加明细
     * @param bizClaimVoucherDetail
     */
    @Override
    public void addClaimDetail(BizClaimVoucherDetail bizClaimVoucherDetail) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(bizClaimVoucherDetail);
        tx.commit();
    }

    /**
     * 添加报销单
     * @param bizClaimVoucher
     */
    @Override
    public void addClaim(BizClaimVoucher bizClaimVoucher) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(bizClaimVoucher);
        tx.commit();
        /*Serializable i=this.getHibernateTemplate().save(bizClaimVoucher);
        System.out.println(i);*/
    }

    @Override
    public BizClaimVoucher getClaim(long id) {
        return (BizClaimVoucher)this.getHibernateTemplate().get(BizClaimVoucher.class,id);
    }

    @Override
    public void updateClaim(BizClaimVoucher bizClaimVoucher) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.update(bizClaimVoucher);
        tx.commit();
    }

    @Override
    public void delClaimDetail(BizClaimVoucherDetail bizClaimVoucherDetail) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.delete(bizClaimVoucherDetail);
        tx.commit();
    }

    @Override
    public SysEmployee getEmpByPid(long pid) {
        return (SysEmployee)this.getHibernateTemplate().find("from SysEmployee where sysPositionByPositionId.id="+pid).get(0);
    }

    @Override
    public void delClaim(BizClaimVoucher bizClaimVoucher) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.delete(bizClaimVoucher);
        tx.commit();
    }

    @Override
    public List<BizClaimVoucherDetail> getClaimDetailByMainID(BizClaimVoucher bizClaimVoucher) {
        return this.getHibernateTemplate().find("from BizClaimVoucherDetail where bizClaimVoucherByMainId.id="+bizClaimVoucher.getId());
    }

    @Override
    public void addCheckResult(BizCheckResult bizCheckResult) {
        Session session=this.getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(bizCheckResult);
        tx.commit();
    }

}
