package com.matome.accounts.utils;


import org.apache.myfaces.shared.webapp.webxml.DelegatedFacesServlet;
import org.apache.myfaces.webapp.MyFacesServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PLMFacesServlet extends HttpServlet implements DelegatedFacesServlet {

    private MyFacesServlet delegate;

    public final void init(ServletConfig servletConfig) throws ServletException {
        delegate = new MyFacesServlet();
        delegate.init(servletConfig);
    }

    public final void destroy() {
        delegate.destroy();
    }

    public final ServletConfig getServletConfig() {
        return delegate.getServletConfig();
    }

    public final String getServletInfo() {
        return delegate.getServletInfo();
    }

    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            delegate.service(request, response);
        } catch(Exception e) {}
    }
    // some other code...
}