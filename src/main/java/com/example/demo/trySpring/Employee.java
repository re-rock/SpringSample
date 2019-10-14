package com.example.demo.trySpring;

import lombok.Data;

// リポジトリークラスやサービスクラス間で値を渡すクラスをSpringではドメインクラスと呼ぶ
@Data // getterやsetterの自動作成（Lombok:ロンボック）
public class Employee {

    private int employeeId;
    private String employeeName;
    private int age;
}
