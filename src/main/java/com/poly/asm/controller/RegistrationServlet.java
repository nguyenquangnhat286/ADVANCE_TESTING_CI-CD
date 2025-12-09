package com.poly.asm.controller;

import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());
            user.setAdmin(false); // Mặc định là user thường

            UserDAO dao = new UserDAO();
            if(dao.findById(user.getId()) != null) {
                req.setAttribute("message", "Tên đăng nhập đã tồn tại!");
            } else {
                dao.create(user);
                req.setAttribute("message", "Đăng ký thành công! Hãy đăng nhập.");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi đăng ký: " + e.getMessage());
        }
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }
}