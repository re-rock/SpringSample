package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    // Getリクエストの受付
    @GetMapping("/hello")
    public String getHello() {

        // 拡張子なしのhtmlファイルの指定しhello.htmlを表示
        return "hello";
    }

    // Postリクエストの受付
    // RequestParamで画面からの入力内容を取得、引数はhtml要素のname属性の値を指定
    @PostMapping("/hello")
    public String postRequest(@RequestParam("text1")String str, Model model) {

        // キーと値を設定することで画面でキーの値を受け取る
        model.addAttribute("sample", str);

        return "helloResponse";
    }

    @PostMapping("hello/db")
    public String postDbRequest(@RequestParam("text2")String str, Model model) {

        // String to int
        int id = Integer.parseInt(str);

        // 1件検索
        Employee employee = helloService.findOne(id);

        // 検索結果をModelに登録
        model.addAttribute("id", employee.getEmployeeId());
        model.addAttribute("name", employee.getEmployeeName());
        model.addAttribute("age", employee.getAge());

        // helloResponseDB.htmlに画面遷移
        return "helloResponseDB";
    }
}
