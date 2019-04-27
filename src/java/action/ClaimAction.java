package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.BizCheckResult;
import entity.BizClaimVoucher;
import entity.BizClaimVoucherDetail;
import entity.SysEmployee;
import net.sf.json.JSONArray;
import org.apache.struts2.StrutsStatics;
import service.BizClaimVoucherService;
import util.ClaimUtil;
import util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClaimAction extends ActionSupport {
    private BizClaimVoucherService bizClaimVoucherService;
    private List<BizClaimVoucher> claims;
    private BizClaimVoucher claim;
    private ClaimUtil claimParam;
    private Page page;
    private String json;
    private int hidd;
    private long id;
    private String comm;

    /**
     * 审核
     * @return
     */
    public String check1(){
        claim=bizClaimVoucherService.getClaim(id);
        BizCheckResult bizCheckResult=new BizCheckResult();
        if(hidd==1){
            if(claim.getTotalAccount()>5000){
                claim.setStatus("待审批");
                claim.setSysEmployeeByNextDealSn(bizClaimVoucherService.getEmpByPid(3));
            }else{
                claim.setStatus("待付款");
                claim.setSysEmployeeByNextDealSn(bizClaimVoucherService.getEmpByPid(4));
            }
            bizCheckResult.setResult("通过");
        }else if (hidd==2){
            claim.setStatus("已拒绝");
            claim.setSysEmployeeByNextDealSn(null);
            bizCheckResult.setResult("拒绝");
        }else if (hidd==3){
            claim.setStatus("已打回");
            claim.setSysEmployeeByNextDealSn(claim.getSysEmployeeByCreateSn());
            bizCheckResult.setResult("打回");
        }
        Map session=ActionContext.getContext().getSession();
        SysEmployee user=((SysEmployee)session.get("user"));
        bizCheckResult.setBizClaimVoucherByClaimId(claim);
        bizCheckResult.setCheckTime(new Date());
        bizCheckResult.setComm(comm);
        bizCheckResult.setSysEmployeeByCheckerSn(user);
        bizClaimVoucherService.check(bizCheckResult,claim);
        return "add";
    }

    /***
     *财务审核
     * @return
     */
    public String check2(){
        claim=bizClaimVoucherService.getClaim(id);
        BizCheckResult bizCheckResult=new BizCheckResult();
        if(hidd==1){
            claim.setStatus("已付款");
            claim.setSysEmployeeByNextDealSn(null);
            bizCheckResult.setResult("已付");
        }
        Map session=ActionContext.getContext().getSession();
        SysEmployee user=((SysEmployee)session.get("user"));
        bizCheckResult.setBizClaimVoucherByClaimId(claim);
        bizCheckResult.setCheckTime(new Date());
        bizCheckResult.setComm(comm);
        bizCheckResult.setSysEmployeeByCheckerSn(user);
        bizClaimVoucherService.check(bizCheckResult,claim);

        return "pay";
    }

    public String del(){
        claim=new BizClaimVoucher();
        claim=bizClaimVoucherService.getClaim(id);
        bizClaimVoucherService.delClaim(claim);
        return "add";
    }

    /**
     * 提交
     * @return
     */
    public String sub(){
        claim=new BizClaimVoucher();
        claim=bizClaimVoucherService.getClaim(id);
        claim.setStatus("已提交");

        claim.setSysEmployeeByNextDealSn(bizClaimVoucherService.getEmpByPid(2));

        bizClaimVoucherService.updateClaim(claim);
        return "add";
    }

    //执行修改操作
    public String upd(){
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        System.out.println(hidd);
        String event=claim.getEvent();
        Long totalAccount=claim.getTotalAccount();
        claim=bizClaimVoucherService.getClaim(id);
        claim.setEvent(event);
        claim.setTotalAccount(totalAccount);
        claim.setModifyTime(new Date());
        HttpSession session=request.getSession();
        SysEmployee user=((SysEmployee)session.getAttribute("user"));
        if (hidd==1){
            //claim.setSysEmployeeByNextDealSn(user);
        }else if (hidd==2){
            claim.setStatus("已提交");
            claim.setSysEmployeeByNextDealSn(bizClaimVoucherService.getEmpByPid(2));
        }
        List<BizClaimVoucherDetail> list2=(List<BizClaimVoucherDetail>)JSONArray.toList(JSONArray.fromObject(json), BizClaimVoucherDetail.class);
        bizClaimVoucherService.updateClaim(claim);
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).setBizClaimVoucherByMainId(claim);
        }
        bizClaimVoucherService.updateClaimDetail(claim,list2);
        return "add";
    }

    //查看claim
    public String info(){
        claim=new BizClaimVoucher();
        claim=bizClaimVoucherService.getClaim(id);
        return "infoOrUpd";
    }
    //跳转update.jsp
    public String update(){
        claim=new BizClaimVoucher();
        claim=bizClaimVoucherService.getClaim(id);
        return "infoOrUpd";
    }
    public String detail(){
        claim=new BizClaimVoucher();
        claim=bizClaimVoucherService.getClaim(id);
        return "detail";
    }
    //执行添加操作
    public String add(){
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        System.out.println(json);
        List<BizClaimVoucherDetail> list2=(List<BizClaimVoucherDetail>)JSONArray.toList(JSONArray.fromObject(json), BizClaimVoucherDetail.class);
        claim.setStatus("新创建");
        System.out.println(claim.getCreateTime());
        HttpSession session=request.getSession();
        SysEmployee user=((SysEmployee)session.getAttribute("user"));
        claim.setSysEmployeeByCreateSn(user);

        if(hidd==1){
            claim.setSysEmployeeByNextDealSn(user);
        }else if(hidd==2){
            claim.setStatus("已提交");
            claim.setSysEmployeeByNextDealSn(bizClaimVoucherService.getEmpByPid(2));
        }
        bizClaimVoucherService.addClaim(claim);
        claim.setBizClaimVoucherDetailsById(list2);
        bizClaimVoucherService.addClaimDetails(claim);
        return "add";
    }
    //查询
    public String query(){
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(claimParam==null){
            claimParam=new ClaimUtil();
        }
        claimParam.setPageNo(1);
        claimParam.setPageSize(8);
        if (page==null){
            page=new Page();
        }else if (page.getPageIndex()!=0){
            System.out.println(page.getPageIndex()+" "+page.getPageAll());
            if (page.getPageIndex()<1){
                claimParam.setPageNo(1);
            }else if (page.getPageAll()!=0&&page.getPageIndex()>=page.getPageAll()){
                claimParam.setPageNo(page.getPageAll());
            } else{
                claimParam.setPageNo(page.getPageIndex());
            }
        }
        page.setPageIndex(claimParam.getPageNo());
        HttpSession session=request.getSession();
        SysEmployee user=((SysEmployee)session.getAttribute("user"));
        if(user==null){
            return "login";
        }
        claimParam.setCreate_sn(user.getSn());
        claims=bizClaimVoucherService.query(claimParam,user);
        int count=bizClaimVoucherService.getCount(claimParam,user);
        int pageAll=count%claimParam.getPageSize()==0?count/claimParam.getPageSize():count/claimParam.getPageSize()+1;
        page.setCount(count);
        page.setPageAll(pageAll);
        session.setAttribute("user",user);
        return Action.SUCCESS;
    }


    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ClaimUtil getClaimParam() {
        return claimParam;
    }

    public void setClaimParam(ClaimUtil claimParam) {
        this.claimParam = claimParam;
    }

    public List<BizClaimVoucher> getClaims() {
        return claims;
    }

    public void setClaims(List<BizClaimVoucher> claims) {
        this.claims = claims;
    }

    public BizClaimVoucherService getBizClaimVoucherService() {
        return bizClaimVoucherService;
    }

    public void setBizClaimVoucherService(BizClaimVoucherService bizClaimVoucherService) {
        this.bizClaimVoucherService = bizClaimVoucherService;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getHidd() {
        return hidd;
    }

    public void setHidd(int hidd) {
        this.hidd = hidd;
    }

    public BizClaimVoucher getClaim() {
        return claim;
    }

    public void setClaim(BizClaimVoucher claim) {
        this.claim = claim;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }
}
