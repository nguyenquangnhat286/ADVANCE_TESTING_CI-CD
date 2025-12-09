package com.poly.asm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("username");
        String password = req.getParameter("password");
        
        UserDAO dao = new UserDAO();
        User user = dao.findById(id);

        if (user != null && user.getPassword().equals(password)) {
            // Đăng nhập thành công, lưu vào session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            
            // Phân quyền: Admin -> trang quản trị, User -> trang chủ
            if(user.getAdmin()) {
                resp.sendRedirect("admin/videos");
            } else {
                resp.sendRedirect("home");
            }
        } else {
            // Đăng nhập thất bại
            req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}