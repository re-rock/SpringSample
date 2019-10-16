package com.example.demo.login.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    // 必須入力、メールアドレス形式
    @NotBlank(message = "{require_check}")
    @Email(message = "{email_check}")
    private String userId;

    // 必須、長さ4~100桁まで、半角英数字
    @NotBlank(message = "{require_check}")
    @Length(min = 4, max = 100, message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pattern_check}")
    private String password;

    @NotBlank(message = "{require_check}")
    private String userName;

    @NotNull(message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;
    // 画面から渡された文字列を日付に変換する。pattern属性で渡されてくるフォーマットを指定

    // 値が20から100まで
    @Min(value = 20, message = "{min_check}")
    @Max(value = 100, message = "{max_check}")
    private int age;

    // falseのみ可能
    @AssertFalse(message = "{false_check}")
    private boolean marriage;
}
