package com.poly.asm.controller;

import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Video;
import org.apache.commons.beanutils.BeanUtils; // Nhớ add thư viện commons-beanutils
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/videos", "/admin/video/create", "/admin/video/update", "/admin/video/delete", "/admin/video/edit"})
public class AdminVideoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        VideoDAO dao = new VideoDAO();
        Video video = new Video();
        String message = "";

        if (uri.contains("edit")) {
            String id = req.getParameter("id");
            video = dao.findById(id);
        } else if (uri.contains("delete")) {
            String id = req.getParameter("id");
            try {
                dao.remove(id);
                message = "Xóa thành công!";
            } catch (Exception e) {
                message = "Không thể xóa video này (đang được sử dụng)!";
            }
        }

        req.setAttribute("video", video);
        req.setAttribute("message", message);
        req.setAttribute("items", dao.findAll()); // Load danh sách video lên bảng
        req.getRequestDispatcher("/views/admin/video-manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideoDAO dao = new VideoDAO();
        Video video = new Video();
        String uri = req.getRequestURI();
        
        try {
            BeanUtils.populate(video, req.getParameterMap()); // Map form vào entity
            
            if (uri.contains("create")) {
                dao.create(video);
                req.setAttribute("message", "Thêm mới thành công!");
            } else if (uri.contains("update")) {
                dao.update(video);
                req.setAttribute("message", "Cập nhật thành công!");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi thao tác: " + e.getMessage());
        }

        req.setAttribute("video", video);
        req.setAttribute("items", dao.findAll());
        req.getRequestDispatcher("/views/admin/video-manager.jsp").forward(req, resp);
    }
}