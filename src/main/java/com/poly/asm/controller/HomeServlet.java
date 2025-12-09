package com.poly.asm.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.poly.asm.dao.VideoDAO;
import com.poly.asm.entity.Video;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideoDAO dao = new VideoDAO();
        List<Video> list = dao.findAll(); // Lấy tất cả video
        
        req.setAttribute("videos", list);
        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }
}