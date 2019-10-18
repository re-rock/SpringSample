package com.example.demo.login.domain.Repository;

import com.example.demo.login.domain.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {

    // Userテーブルの件数取得
    int count() throws DataAccessException;

    // Userテーブルにデータを1件Insert
    int insertOne(User user) throws DataAccessException;

    // Userテーブルからデータを1件取得
    User selectOne(String userId) throws DataAccessException;

    // Userテーブルの全データを取得
    List<User> selectMany() throws DataAccessException;

    // Userテーブルを1件更新
    int updateOne(User user) throws DataAccessException;

    // Userテーブルを1件削除
    int deleteOne(String userId) throws DataAccessException;

    // SQL取得結果をサーバーにCSVで保存する
    void userCsvOut() throws DataAccessException;
}
