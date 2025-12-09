package com.poly.asm.dao;

import com.poly.asm.entity.User;

public class UserDAO extends AbstractDAO<User> {
    
    public UserDAO() {
        super(User.class);
    }
    
    // Có thể viết thêm hàm login check ở đây nếu cần, hoặc dùng findById ở controller
}