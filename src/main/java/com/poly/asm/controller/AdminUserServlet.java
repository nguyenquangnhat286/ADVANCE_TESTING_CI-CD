package com.poly.asm.controller;

import com.poly.asm.dao.UserDAO;
import com.poly.asm.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet({"/admin/users", "/admin/user/update", "/admin/user/delete", "/admin/user/edit"})
public class AdminUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        String uri = req.getRequestURI();
        
        if (uri.contains("edit")) {
            String id = req.getParameter("id");
            req.setAttribute("userEdit", dao.findById(id));
        } else if (uri.contains("delete")) {
            String id = req.getParameter("id");
            try { dao.remove(id); } catch (Exception e) { req.setAttribute("message", "Không thể xóa user này!"); }
        }

        req.setAttribute("users", dao.findAll());
        req.getRequestDispatcher("/views/admin/user-manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());
            UserDAO dao = new UserDAO();
            dao.update(user); // Chỉ cho update, không cho create user ở trang admin (thường là vậy)
            req.setAttribute("message", "Cập nhật thành công!");
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi: " + e.getMessage());
        }
        doGet(req, resp);
    }
}