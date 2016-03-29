package com.github.alexwolfgoncharov.termdata.filters;


import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SystemAccessFilter implements Filter {

	private FilterConfig filterConfig;
	private static final Logger log = Logger.getLogger(SystemAccessFilter.class.getName());
	public void setFilterConfig(FilterConfig fc) {
		filterConfig = fc;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		  this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		log.info("Вход в фильтр");
		log.info(request.toString());
		String url;
		 if (filterConfig == null){
	            return;
	        }
	       ServletContext ctx = filterConfig.getServletContext();
	       HttpSession session  = ((HttpServletRequest) request).getSession();
	       String referrer = ((HttpServletRequest) request).getHeader("referer");
	       String contentPath = ((HttpServletRequest)request).getContextPath();
	       if (referrer != null) {
	    	   log.info("reffer не пустой, reffer: " + referrer);
	    	    url = ((HttpServletRequest)request).getRequestURL().toString();
	    	    String  ur = ((HttpServletRequest)request).getPathInfo();
	    	    log.info("ur: " + ur);
		        log.info("url: " + url);
		        url = referrer.replace("http://localhost:5555/term","");
		      
		        log.info("Новый URL 1: " + url);
		        
		        log.info("Новый URL 2: " + url);
	       } else {
	    	   
	         url = ((HttpServletRequest)request).getRequestURI();
	        
	        log.info("url: " + url);
	        log.info("contentPath: " + contentPath);
	        url = url.replace(contentPath,"");
	        log.info("Новый URL: " + url);
	       }
//	        		getRequestURL().toString();
	       
	        String logged = (String)((HttpServletRequest)request).getSession().getAttribute("isLogged");
	        log.info(logged);
	        
	        if (!"true".equals(logged)){
	        	 log.info("переходим на авторизацию");
		        session.setAttribute("requestAddr", url);
	        RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
	        dispatcher.forward(request, response);
	    } else {
	    	log.info("уже авторизованы, переходим на запрашиваемую страницу");
	        RequestDispatcher dispatcher = ctx.getRequestDispatcher(url);
	        
	        dispatcher.forward(request, response);
	    }
		
		
		
		
//
//		String value = "Simple Filter";
//
//		request.setAttribute("info", value);

		chain.doFilter(request, response);

//		log.severe("info = " + value);

		log.info("Окончание фильтрa");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
