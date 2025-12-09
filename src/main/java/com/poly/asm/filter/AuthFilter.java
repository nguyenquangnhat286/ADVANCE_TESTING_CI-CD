package com.poly.asm.filter;

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
import com.poly.asm.entity.User;

@WebFilter({"/admin/*", "/like", "/share", "/account/*"})
public class AuthFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            // Chưa đăng nhập -> chuyển về trang login
            resp.sendRedirect(req.getContextPath() + "/login?message=Vui long dang nhap");
        } else if (req.getRequestURI().contains("/admin") && !user.getAdmin()) {
            // Đã đăng nhập nhưng không phải admin -> đá về trang chủ
            resp.sendRedirect(req.getContextPath() + "/home?message=Khong co quyen truy cap");
        } else {
            // Cho phép đi tiếp
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}
}