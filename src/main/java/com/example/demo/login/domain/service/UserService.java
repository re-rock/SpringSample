package com.example.demo.login.domain.service;

import com.example.demo.login.domain.Repository.UserDao;
import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    // for insert
    public boolean insert(User user) {

        // execute insert
        int rowNumber = dao.insertOne(user);

        // check
        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    // カウントメソッド
    public int count() {
        return dao.count();
    }

    // 全件取得用メソッド
    public List<User> selectMany() {
        return dao.selectMany();
    }

    // 1件取得用メソッド
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    // 1件更新メソッド
    public boolean updateOne(User user) {
        // 1件更新
        int rowNumber = dao.updateOne(user);
        // 判定用変数
        boolean result = false;
        if (rowNumber > 0) {
            // Update Success
            result = true;
        }
        return result;
    }

    // 1件削除
    public boolean deleteOne(String userId) {

        // 1件削除
        int rowNumber = dao.deleteOne(userId);
        // 判定用変数
        boolean result = false;
        if (rowNumber > 0) {
            // success to delete
            return true;
        }
        return result;
    }
}
