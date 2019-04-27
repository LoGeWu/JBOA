package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.SysEmployee;
import org.apache.struts2.StrutsStatics;
import service.SysEmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysEmployeeAction extends ActionSupport {
    private SysEmployee sysEmployee;
    private SysEmployeeService sysEmployeeService;

    public String login(){
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        SysEmployee s= sysEmployeeService.login(sysEmployee);
        HttpSession session=request.getSession();
        if (s!=null){

            session.setAttribute("user",s);
            session.removeAttribute("error");
            return Action.SUCCESS;
        }else {
            if("A5".equals(sysEmployee.getSn())){
                session.setAttribute("error","该员工已离职！");
                return Action.LOGIN;
            }
            session.setAttribute("error","密码或账户错误");
            return Action.LOGIN;
        }
    }
   public String loginOut(){
       HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
       HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
       HttpSession session=request.getSession();
       session.removeAttribute("user");
       return Action.LOGIN;
   }
    public SysEmployeeService getSysEmployeeService() {
        return sysEmployeeService;
    }

    public void setSysEmployeeService(SysEmployeeService sysEmployeeService) {
        this.sysEmployeeService = sysEmployeeService;
    }

    public SysEmployee getSysEmployee() {
        return sysEmployee;
    }

    public void setSysEmployee(SysEmployee sysEmployee) {
        this.sysEmployee = sysEmployee;
    }
}
