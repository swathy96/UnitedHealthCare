package servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.UserBO;

public class ValidateFilter implements Filter {

    public ValidateFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			System.out.println("in validate filter");
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res = (HttpServletResponse)response;
			HttpSession session = req.getSession(false);
			if(session!=null){
				session.removeAttribute("message");
				session.removeAttribute("username");
			}
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			UserBO userBo = new UserBO();
			
			if(userBo.checkUserPresentByUsername(username)) {
				if(userBo.validateUser(username, password)) {
			        chain.doFilter(request, response);
				}
				else {
					req.getSession().setAttribute("message","Incorrect Password!!!");
					res.sendRedirect("login.jsp");
				}
			}
			else {
				req.getSession().setAttribute("message","No such User!!!");
				res.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
