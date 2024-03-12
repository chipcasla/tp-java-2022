package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Persona;

/**
 * Servlet implementation class ValidarAdmin
 */
@WebFilter({ "/AdminFilter", "/Admin/*" })
public class ValidarAdmin implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public ValidarAdmin() {
        super();
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		
        HttpSession session = httpRequest.getSession();
        
        if (session!=null && session.getAttribute("user")!=null) {
        	String userClass = ((Persona) session.getAttribute("user")).getTipo();
        	if ("admin".equals(userClass)) {
        		chain.doFilter(request, response);
        	} else {
        		httpResponse.sendRedirect(httpRequest.getContextPath()+"/unauthorized.jsp");
        	}
        } else {
        	httpResponse.sendRedirect(httpRequest.getContextPath()+"/index.jsp");
        }
        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
