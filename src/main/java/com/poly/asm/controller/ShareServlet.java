package com.poly.asm.controller;

import com.poly.asm.dao.ShareDAO;
import com.poly.asm.entity.Share;
import com.poly.asm.entity.User;
import com.poly.asm.entity.Video;
import com.poly.asm.util.EmailUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) {
                resp.getWriter().write("LoginRequired"); // Phản hồi cho AJAX hoặc redirect
                return;
            }

            String videoId = req.getParameter("videoId");
            String emailTo = req.getParameter("email");

            Video video = new Video();
            video.setId(videoId);

            // Lưu vào CSDL
            Share share = new Share();
            share.setUser(user);
            share.setVideo(video);
            share.setEmails(emailTo);
            share.setShareDate(new Date());

            ShareDAO dao = new ShareDAO();
            dao.create(share);

            // Gửi mail
            String subject = "Chia sẻ video hay từ Online Entertainment";
            String body = "Xin chào, bạn " + user.getFullname() + " đã chia sẻ video này: https://www.youtube.com/watch?v=" + videoId;
            EmailUtil.send(emailTo, subject, body);

            resp.getWriter().write("Success");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error");
        }
    }
}