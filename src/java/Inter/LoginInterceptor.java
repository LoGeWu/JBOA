package Inter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import entity.SysEmployee;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        SysEmployee user = (SysEmployee)request.getSession().getAttribute("user");
        if (user != null){//user不为空，为已经登录
            return actionInvocation.invoke();
        } else {
            System.out.println("未登录");
            //ctx.put("tip" , "您还没有登陆，请输入用户名和密码登陆系统");
            return Action.LOGIN;
        }
    }
}
