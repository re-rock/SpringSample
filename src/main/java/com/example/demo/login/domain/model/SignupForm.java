package com.example.demo.login.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SignupForm {

    private String userId;
    private String password;
    private String userName;

    // 画面から渡された文字列を日付に変換する。pattern属性で渡されてくるフォーマットを指定
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    private int age;
    private boolean marriage;

}
