package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// @Aspect : AOPクラスに付ける。DIコンテナへBean定義をするため@Componentアノテーションも付ける
@Aspect
@Component
public class LogAspect {

    // Bean名でAOPの対象を指定する
    // @Around("bean(*Controller)") コンテナに登録されているBean名を指定
    // public Object startLog(ProceedingJoinPoint jp) throws Throwable {}

    // 任意のアノテーションが付与されているメソッドを指定 アノテーションをパッケージ名まで含めて指定する
    // @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    // public Object startLog(ProceedingJoinPoint jp) throws Throwable {}

    // @within 指定したアノテーションがクラスの全てのメソッドが対象
    // @Around("@within(org.springframework.stereotype.Controller)")
    // public Object startLog(ProceedingJoinPoint jp) throws Throwable {}

    // // AOPの実装 execution(戻り値 メソッドまでのパス(引数))
    // // [*]は任意の文字列。[..]は任意のパッケージ、引数箇所では0以上の引数になる
    // @Before("execution(* *..*.*Controller.*(..))")
    // public void stratLog(JoinPoint jp) {
    //     System.out.println("メソッド開始： " + jp.getSignature());
    // }
    //
    // @After("execution(* *..*.*Controller.*(..))")
    // public void endLog(JoinPoint jp) {
    //     System.out.println("メソッド終了： " + jp.getSignature());
    // }

    @Around("execution(* *..*.*Controller.*(..))")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("コントローラメソッドの開始： " + jp.getSignature());

        try {
            // 渡されてきたメソッドを実行
            Object result = jp.proceed();
            System.out.println("コントローラメソッドの終了： " + jp.getSignature());
            return result;

        } catch (Exception e) {
            System.out.println("コントローラメソッドの異常終了： " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    // UserDaoクラスのログ出力
    @Around("execution(* *..*.*UserDao*.*(..))")
    public Object daoLog(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("Daoメソッドの開始：" + jp.getSignature());

        try {
            Object result = jp.proceed();
            System.out.println("Daoメソッドの終了：" + jp.getSignature());

            return result;

        } catch (Exception e) {
            System.out.println("Daoメソッドの異常終了 " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}




























