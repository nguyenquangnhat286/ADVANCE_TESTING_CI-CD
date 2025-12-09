package com.poly.asm.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            // "PolyOE" là tên định danh trong file persistence.xml
            // Nếu bạn đặt tên khác thì sửa lại chỗ này cho khớp
            factory = Persistence.createEntityManagerFactory("PolyOE");
        }
        return factory.createEntityManager();
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}