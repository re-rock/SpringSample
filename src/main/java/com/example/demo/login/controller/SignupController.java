package com.example.demo.login.controller;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.GroupSequence;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SignupController {

    // ラジオボタンの実装
    private Map<String, String> radioMarriage;

    // ラジオボタンの初期化メソッド
    private Map<String, String> initRadioMarrige() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 既婚、未婚をMapに格納
        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    // ユーザー登録画面のGET用コントローラー
    @GetMapping("/signup")
    // ModelAttribute -> 自動でModelクラスに登録する(自動でaddAttributeするイメージ)
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

        // ラジオボタンの初期化メソッドの呼び出し
        radioMarriage = initRadioMarrige();

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioMarriage", radioMarriage);

        return "/login/signup";
    }

    // ユーザー登録画面のPOST用コントローラー
    // BindingResult -> データバインド結果を受け取る。バインド失敗や入力チェックエラー時はhasErrors()で検知
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
                             BindingResult bindingResult,
                             Model model) {

        // 入力チェックに引っかかった場合
        if (bindingResult.hasErrors()) {
            // Getリクエスト用のメソッドを呼び出して、ユーザ画面に戻る
            return getSignUp(form, model);
        }

        System.out.println(form);
        // login.htmlにリダイレクト
        return "redirect:/login";
    }
}
