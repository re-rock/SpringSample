package com.example.demo.login.domain.Repository.jdbc;

import com.example.demo.login.domain.Repository.UserDao;
import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("UserDaoJdbcImpl") // DIにBean名をセットする
public class UserDaoJdbcImpl implements UserDao {

  @Autowired // JdbcTemplateはデフォルトでBean定義がされている
  JdbcTemplate jdbc;

  @Autowired PasswordEncoder passwordEncoder;

  @Override // Userテーブルの件数を取得
  public int count() throws DataAccessException {
    // 全件取得してカウント
    int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
    return count;
  }

  @Override // Userテーブルにテータを1件Insert
  public int insertOne(User user) throws DataAccessException {

    // パスワードの暗号化
    String password = passwordEncoder.encode(user.getPassword());

    int rowNumber =
        jdbc.update(
            "INSERT  INTO m_user(user_id,"
                + " password,"
                + " user_name,"
                + " birthday,"
                + " age,"
                + " marriage,"
                + " role)"
                + " VALUES(?,?,?,?,?,?,?)",
            user.getUserId(),
            password,
            user.getUserName(),
            user.getBirthday(),
            user.getAge(),
            user.isMarriage(),
            user.getRole());

    return rowNumber;
  }

  @Override // Userテーブルのデータを1件取得
  public User selectOne(String userId) throws DataAccessException {

    Map<String, Object> map =
        jdbc.queryForMap("SELECT * FROM m_user" + " WHERE user_id = ?", userId);

    // 結果返却用の変数
    User user = new User();
    // 取得したデータを結果返却用の変数にセット
    user.setUserId((String) map.get("user_id"));
    user.setPassword((String) map.get("password"));
    user.setUserName((String) map.get("user_name"));
    user.setBirthday((Date) map.get("birthday"));
    user.setAge((Integer) map.get("age"));
    user.setMarriage((Boolean) map.get("marriage"));
    user.setRole((String) map.get("role"));

    return user;
  }

  @Override // Userテーブルの全データを取得
  public List<User> selectMany() throws DataAccessException {

    // M_USERテーブルのデータを全件取得
    List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");

    // 結果返却用の変数
    List<User> userList = new ArrayList<>();
    // 取得したデータを結果返却用のListに格納していく
    for (Map<String, Object> map : getList) {
      // Userインスタンスの生成
      User user = new User();

      // Userインスタンスに取得したデータをセットする
      user.setUserId((String) map.get("user_id"));
      user.setPassword((String) map.get("password"));
      user.setUserName((String) map.get("user_name"));
      user.setBirthday((Date) map.get("birthday"));
      user.setAge((Integer) map.get("age"));
      user.setMarriage((Boolean) map.get("marriage"));
      user.setRole((String) map.get("role"));

      // 結果返却用のListに追加
      userList.add(user);
    }
    return userList;
  }

  @Override // Userテーブルを1件更新
  public int updateOne(User user) throws DataAccessException {

    // パスワードの暗号化
    String password = passwordEncoder.encode(user.getPassword());

    // 1件更新
    int rowNumber =
        jdbc.update(
            "UPDATE M_USER SET"
                + " password = ?,"
                + " user_name = ?,"
                + " birthday = ?,"
                + " age = ?,"
                + " marriage = ?"
                + " WHERE user_id = ?",
            password,
            user.getUserName(),
            user.getBirthday(),
            user.getAge(),
            user.isMarriage(),
            user.getUserId());

    // トランザクション確認。意図的に例外を発生させる
    //    if (rowNumber > 0) {
    //      throw new DataAccessException("トランザクションテスト") {};
    //    }

    return rowNumber;
  }

  // Userテーブルを１件削除.
  @Override
  public int deleteOne(String userId) throws DataAccessException {

    // １件削除
    int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);

    return rowNumber;
  }

  // SQL取得結果をサーバーにCSVで保存する
  @Override
  public void userCsvOut() throws DataAccessException {

    // M_USERテーブルのデータを全件取得するSQL
    String sql = "SELECT * FROM m_user";

    // ResultSetExtractorの生成\
    UserRowCallbackHandler handler = new UserRowCallbackHandler();

    // SQL実行＆CSV出力
    jdbc.query(sql, handler);
  }
}
