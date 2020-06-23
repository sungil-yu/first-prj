<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="../css/reset.css" type="text/css" rel="stylesheet">
    <link href="../css/style.css" type="text/css" rel="stylesheet">
    <script src="../js/join.js"></script>
    <title>메모보드</title>
</head>

<body>
    <main>
        <section>
            <h1 class="d-none">회원가입</h1>
            <section class="join">
                <h1 class="login-logo">
                    <a href="#">
                        <img src="../images/logo-color.png" alt="트렐로 로그인">
                    </a>
                </h1>
                <form class="join-form" method="POST" action="join">
                
                <fieldset>
               <legend class="d-none">required</legend>
                    <span class="necessary">
                        <label for="id">아이디 (아이디는 6글자 이상 14글자 이하로 입력해주세요.)</label>
                        <span class="id-err err-text d-none">중복되는 아이디입니다.</span>
                        <input type="text" id="id" name="id" class="id-input" required="required" size="14" minlength="6" maxlength="14">

                        <label for="pwd">패스워드</label>
                        <span class="pwd-err err-text d-none">패스워드를 입력하세요.</span>
                        <input type="password" id="pwd" name="pwd" class="pwd-input" required="required" size="14" minlength="6" maxlength="14">
   
                        <label for="confirmPwd">패스워드 확인</label>
                        <span class="repwd-err err-text d-none">비밀번호와 다릅니다.</span>
                        <input type="password" id="confirmPwd" name="repwd" class="repwd-input" required="required" size="14" minlength="6" maxlength="14">

                        <label for="name">이름</label>
                        <input type="text" id="name" name="name" class="name-input" required="required" size="10" minlength="3" maxlength="20">

                        <label for="nickname">닉네임</label>
                        <span class="nick-err err-text d-none">중복되는 닉네임입니다.</span>
                        <input type="text" id="nickname" name="nickname" class="nick-input" required="required" size="20" minlength="1" maxlength="20">

                        <label for="email">이메일</label>
                        <span class="email-err err-text d-none">올바른 이메일을 입력해주세요.</span>
                        <span class="email-over-err err-text d-none">중복되는 이메일 입니다.</span>
                        <input type="email" id="email" name="email" class="email-input" required="required">
                    </span>
            </fieldset>

            <fieldset>
                <legend class="d-none">option</legend>
                <span class="option">
                    <label for="gender">성별</label>
                    <span class="gender">
                        <input type="radio" id="gender" name="gender" value="male"> 남성
                        <input type="radio" id="gender"  name="gender" value="female"> 여성
                    </span>
                    <label for="birthday">생년월일</label>
                    <input type="date" id="birthday" name="birthday" class="birthday-input">
                </span>
            </fieldset>         


                    <input type="submit" value="가입하기">
                    
                    <a href="login">로그인</a>
                </form>
            </section>
        </section>
    </main>
</body>

</html>