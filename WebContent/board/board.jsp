<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메모보드</title>
    <script src="../js/board.js"></script>
    <link href="../css/reset.css" type="text/css" rel="stylesheet">
	<link href="../css/style.css" type="text/css" rel="stylesheet">
</head>

<body id="body">
	<header class="header">
		<h1 class="logo">
			<a href="../index">
				<img src="../images/logo.png" alt="메모보드">
			</a>
		</h1>
		<section class="header-container">
			<h1 class="d-none">헤더</h1>
			<nav>
				<h1 class="d-none">메뉴</h1>
				<ul class="left-menu">
					<li><a href="../index">홈</a></li>
					<li class="board-btn">
						<span></span> 
						<span>보드</span>
					</li>
				</ul>
			</nav>
			<nav>
				<h1 class="d-none">메뉴</h1>
				<ul class="right-menu">
					<li>알림</li>
					<li>프로필</li>
				</ul>
			</nav>
		</section>
	</header>

    <main class="board-main">
        <section class="board-info">
            <h1 class="d-none">board-info</h1>
            <span data-value="${board.id }">${board.name }</span>
        </section>

        <section class="content-box">
			<h1 class="d-none">To do List</h1>
			
			<c:forEach var="li" items="${lists }">
			<div>
				<section class="todo-list-box">
					<header class="list-box-header">
                        <h1 class="list-name ">
	                        ${li.name} 
                        	<c:if test="${li.check == 100 }">
	                        	<span class="list-check-span" style="color:red;">(&nbsp;${li.check }%&nbsp;)</span>
                        	</c:if>
                        	<c:if test="${li.check != 100 }">
	                        	<span class="list-check-span">(&nbsp;${li.check }%&nbsp;)</span>
                        	</c:if>
                        </h1>
                        <input type="text" class="list-update-input d-none" value = "${li.name}">
                        <button class="hidden-menu-button">...</button>
                        <input type="text" hidden value="${li.id }"> 
                        <!-- ... 버튼 -->
                    </header>
                    <c:forEach var="c" items="${cards }">
	                    <c:if test="${li.id eq c.listId }">
		                    <div class="card-box" data-value="${c.id }">
								<c:if test="${c.check == 1}">
									${c.content }
									<input type="checkbox" class="card-todo-check" checked>
								</c:if>
								<c:if test="${c.check == 0}">
									${c.content }
									<input type="checkbox" class="card-todo-check">
								</c:if>
		                    </div>
    	                </c:if>
                    </c:forEach>
					<div class="add-card-button">
                        + 카드 추가
					</div>
					<div class= "d-none">
                        <input class="card-content-input" type="text" placeholder="내용 입력.."> 
                        <input type="text" hidden value="${li.id }"> 
						<input class="reg-card-button" type="submit" value="카드 추가">
						<span class="cancle-card-button">X</span>
					</div>
				</section>
            </div>
            </c:forEach>
	
			<div>
				<form action="" class="list-add-form" enctype="application/x-www-form-urlencoded">
					<span class="add-list-button">
						 + 리스트 추가 
					</span>
					<span class="d-none">
						<input class="list-title-input" type="text" placeholder="리스트 제목..">
						<input class="reg-list-button" type="submit" value="리스트 추가">
						<span class="cancle-add-button">X</span>
					</span>
				</form>
            </div>
            
            <div class="hidden-popup d-none">
                <section>
                    <header class="hidden-header">
                        <h1>리스트 Memu</h1>
                        <span class="hidden-popup-close">X</span>
                        <input type="text" hidden class="list-id">
                    </header>
                    <div class="popup-content">
                        <div class="list-del-button">리스트 삭제하기(List Delete)</div>
                    </div>
                </section>
            </div>

        </section>
        
        <div class="trash-space d-none">
            REMOVE
        </div>
    </main>
</body>
</html>