package com.poly.asm.controller;

import com.poly.asm.dao.FavoriteDAO;
import com.poly.asm.entity.Favorite;
import com.poly.asm.entity.User;
import com.poly.asm.entity.Video;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String videoId = req.getParameter("id");
        
        if (user == null) {
            resp.sendRedirect("login"); // Yêu cầu đăng nhập [cite: 86]
            return;
        }

        FavoriteDAO dao = new FavoriteDAO();
        Favorite favorite = dao.findByUserIdAndVideoId(user.getId(), videoId);

        if (favorite == null) {
            // Chưa like thì like
            favorite = new Favorite();
            Video video = new Video();
            video.setId(videoId);
            favorite.setVideo(video);
            favorite.setUser(user);
            favorite.setLikeDate(new Date());
            dao.create(favorite);
        } else {
            // Đã like thì unlike (xóa) [cite: 79]
            dao.remove(favorite.getId());
        }
        
        resp.sendRedirect(req.getHeader("referer")); // Quay lại trang cũ
    }
}