package com.poly.asm.dao;

import com.poly.asm.entity.Favorite;
import com.poly.asm.util.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class FavoriteDAO extends AbstractDAO<Favorite> {
    
    // XÓA cái Constructor public FavoriteDAO() { super... } đi vì AbstractDAO ở trên không hỗ trợ.

    // Hàm kiểm tra like
    public Favorite findByUserIdAndVideoId(String userId, String videoId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :uid AND f.video.id = :vid";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("uid", userId);
            query.setParameter("vid", videoId);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    // Thêm hàm xóa để LikeServlet gọi được
    public void remove(Long id) {
        super.remove(id, Favorite.class);
    }
    
    // Hàm create đã có sẵn bên AbstractDAO
}