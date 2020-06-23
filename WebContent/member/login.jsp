<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="../css/reset.css" type="text/css" rel="stylesheet">
    <link href="../css/style.css" type="text/css" rel="stylesheet">
    <title>메모보드</title>
</head>
<style>

</style>

<body>
    <main>
        <section>
            <h1 class="d-none">로그인</h1>
            <section class="login">
                <h1 class="login-logo">
                    <a href="#">
                        <img src="../images/logo-color.png" alt="트렐로 로그인">
                    </a>
                </h1>
                <span><c:if test="${errors.idAndPwdError}">아이디, 비밀번호 오류입니다.</c:if></span>
                <form class="login-form" method="POST" action="/member/login">
                    <input type="text" placeholder="아이디" name="id">
                  	<span><c:if test="${errors.id}">아이디를 입력하세요</c:if></span>
                    <input type="password" placeholder="패스워드" name="pwd">
                    <span><c:if test="${errors.pwd}">패스워드를 입력하세요</c:if></span>
                    <input type="submit" value="로그인">
                    <a href="/member/join">회원가입</a>
                </form>
            </section>
        </section>
    </main>
</body>

</html>