package com.example.demo.login.controller;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    // 結婚ステータスのラジオボタン用変数
    private Map<String, String> radioMarriage;

    // ラジオボタンの初期化メソッド
    private Map<String, String> initRadioMarriage() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 既婚、未婚をMapに格納
        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    // ユーザー一覧画面のGET用メソッド
    @GetMapping("/home")
    public String getHome(Model model) {

        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "login/home::home_contents");
        return "login/homeLayout";
    }

    // ユーザー一覧画面のGet用メソッド
    @GetMapping("/userList")
    public String getUserList(Model model) {

        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        // "login/userList"はhtmlファイル、"userList_contents"はfragment名
        model.addAttribute("contents", "login/userList :: userList_contents");
        // ユーザー一覧の生成
        List<User> userList = userService.selectMany();
        model.addAttribute("userList", userList);

        // データ研修を取得
        int count = userService.count();
        model.addAttribute("userListCount", count);

        return "login/homeLayout";
    }

    // ユーザー詳細画面のGET用メソッド "PathValue"でクエリパラメータを引数に引当てる
    @GetMapping("userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form,
                                Model model,
                                @PathVariable("id") String userId) {
        System.out.println("userId = " + userId);

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "login/userDetail :: userDetail_contents");
        // 結婚ステータス用ラジオボタンの初期化
        radioMarriage = initRadioMarriage();
        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioMarriage", radioMarriage);
        // ユーザーIDのチェック
        if (userId != null && userId.length() > 0) {
            // ユーザー情報を取得
            User user = userService.selectOne(userId);

            // Userクラスをフォームクラスに変換
            form.setUserId(user.getUserId()); //ユーザーID
            form.setUserName(user.getUserName()); //ユーザー名
            form.setBirthday(user.getBirthday()); //誕生日
            form.setAge(user.getAge()); //年齢
            form.setMarriage(user.isMarriage()); //結婚ステータス

            // Modelに登録
            model.addAttribute("signupForm", form);
        }
        return "login/homeLayout";
    }

    // ユーザー更新用処理
    // params属性を使ってHTML側のname属性を取得しルーティングを判別する
    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {

        System.out.println("更新ボタンの更新");
        // Userインスタンスの生成
        User user = new User();

        // フォームクラスのUserクラスの変換
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());

        // 更新実行
        boolean result = userService.updateOne(user);
        if (result == true) {
            model.addAttribute("result", "更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
        }
        return getUserList(model);
    }

    // ユーザー削除用処理
    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form,
                                       Model model) {
        System.out.println("削除ボタンの処理");

        // 削除実行
        boolean result = userService.deleteOne(form.getUserId());
        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }
        return getUserList(model);
    }

    // ログアウト用メソッド
    @PostMapping("/logout")
    public String postLogout() {

        // ログイン画面にリダイレクト
        return "redirect:/login";
    }

    // ユーザー一覧のCSV出力用メソッド
    @GetMapping("/userList/csv")
    public String getUserListCsv(Model model) {
        return getUserList(model);
    }
}
