package com.poly.asm.controller;

import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Video;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/video/detail")
public class VideoDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        VideoDAO dao = new VideoDAO();
        Video video = dao.findById(id);

        if (video != null) {
            video.setViews(video.getViews() + 1);
            dao.update(video);
            req.setAttribute("video", video);
            // Lấy list video ngẫu nhiên để gợi ý bên cạnh (nếu cần)
            req.setAttribute("randomVideos", dao.findAll()); 
            req.getRequestDispatcher("/views/user/detail.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}