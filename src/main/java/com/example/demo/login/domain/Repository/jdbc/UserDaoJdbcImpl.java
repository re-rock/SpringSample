package com.example.demo.login.domain.Repository.jdbc;

import com.example.demo.login.domain.Repository.UserDao;
import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    @Autowired // JdbcTemplateはデフォルトでBean定義がされている
    JdbcTemplate jdbc;

    @Override // Userテーブルの件数を取得
    public int count() throws DataAccessException {
        return 0;
    }

    @Override // Userテーブルにテータを1件Insert
    public int insertOne(User user) throws DataAccessException {

        int rowNumber = jdbc.update("INSERT  INTO m_user(user_id,"
                + " password,"
                + " user_name,"
                + " birthday,"
                + " age,"
                + " marriage,"
                + " role)"
                + " VALUES(?,?,?,?,?,?,?)"
                , user.getUserId()
                , user.getPassword()
                , user.getUserName()
                , user.getBirthday()
                , user.getAge()
                , user.isMarriage()
                , user.getRole());

        return rowNumber;
    }

    @Override // Userテーブルのデータを1件取得
    public User selectOne(String userId) throws DataAccessException {
        return null;
    }

    @Override // Userテーブルの全データを取得
    public List<User> selectMany() throws DataAccessException {
        return null;
    }

    @Override // Userテーブルを1件更新
    public int updateOne(User user) throws DataAccessException {
        return 0;
    }

    @Override // Userテーブルw1件削除
    public int deleteOne(String userId) throws DataAccessException {
        return 0;
    }

    @Override // SQL取得結果をサーバーにCSVで保存する
    public void userCsvOut() throws DataAccessException {

    }
}
