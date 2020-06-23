<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head profile="http://www.w3.org/2005/10/profile">
	<meta charset="UTF-8">
	<script src="js/index.js"></script>
	<script src="js/profile.js"></script>
	<link href="css/reset.css" type="text/css" rel="stylesheet">
	<link href="css/style.css" type="text/css" rel="stylesheet">
	<link rel="icon" type="image/png" href="http://example.com/myicon.png">
	<title>메모보드</title>
</head>

<body>
	<header class="header">
		<h1 class="logo">
			<a href="index">
				<img src="images/logo.png" alt="메모보드">
			</a>
		</h1>
		<section class="header-container">
			<h1 class="d-none">헤더</h1>
			<nav>
				<h1 class="d-none">메뉴</h1>
				<ul class="left-menu">
					<li><a href="index">홈</a></li>
					<li class="board-btn">
						<span></span>
						<span>보드</span>
					</li>
				</ul>
			</nav>
			<nav>
				<h1 class="d-none">메뉴</h1>
				<ul class="right-menu">
					<c:if test="${empty noti }">
						<li>알림</li>
					</c:if>
					<c:if test="${not empty noti }">
						<li style="background-color: #F2581F;">알림</li>
					</c:if>
					
					<li><img src="profile-img/${empty detail.profilePicture?'defalut.png':detail.profilePicture}"></li>
				</ul>
			</nav>
		</section>
	</header>


	<section class="header-board-popup d-none">
		<h1>RECENT BOARDS</h1>
		<ul>
			<c:forEach var="b" items="${board}">
                 <li><a href="/board/board?bid=${b.id }">${b.name}</a></li>
	         </c:forEach>
		</ul>
	</section>


	<div class="body">
		<div class="content-container">

			<section class="profile">
				<h1 class="section-name">프로필</h1>

				<section class="profile-list">
					<h1 class="d-none">프로필 영역</h1>
					<dl>
						<dt><img src="/profile-img/${detail.profilePicture}"></dt>
						<dd>
							<span>${member.nickname }</span>
							<span>${member.email }</span>
						</dd>
					</dl>
					<span class="profile-click">프로필 이미지 수정</span>

					<section class="profile-setting d-none">
					<span>
						<h1 class="section-name">프로필 이미지 수정</h1>
						<span>하단 이미지를 클릭하여<br />원하는 프로필 이미지를 업데이트해주세요.</span>

						<form action="/index" method="POST" enctype="multipart/form-data">
							<input class="profile-fileBtn d-none" type="file" name="file">
							<div class="profile-img-input">
								<img class="profile-img" src="" alt="">
							</div>
							<input class="profile-submit" type="submit" value="확인">
						</form>
					</section>
				</section>
			</section>
			<section class="team-list">
				<span class="team-button-span">
					<h1 class="section-name">팀 리스트</h1>
					<button class="add-team-btn">팀 추가</button>
				</span>
				<c:forEach var="t" items="${team}">
					<section class="team">
						<span>
							<h1>${t.name}</h1>
							<ul class="team-btn">
								<li>
									<span></span>
									<span class="add-board-btn">보드 등록</span>
									<div class="d-none">${t.id}</div>
								</li>
								<li>
									<span></span>
									<span class="team-detail-button" data-value="${t.id}">팀 관리</span>
								</li>
							</ul>
						</span>

						<ul class="board-list">
							<c:forEach var="b" items="${board}">
								<c:if test="${t.id eq b.teamId}">
									<li><a href="/board/board?bid=${b.id }">${b.name}</a></li>
								</c:if>
							</c:forEach>
						</ul>
					</section>
				</c:forEach>
			</section>
		</div>

		<section>
			<h1 class="d-none">팀/보드 추가 팝업</h1>
			<section class="add-popup add-team-popup d-none">
				<span>
					<h1>팀 등록</h1>
					<span class="close-button"></span>
				</span>
				<form action="" class="add-team-form">
					<input class="popup-name-input" type="text" placeholder="팀명을 입력해주세요."> 
					<input class="add-button add-team-button" type="submit" value="팀 추가">
				</form>
			</section>

			<section class="add-popup add-board-popup d-none">
				<span>
					<h1>보드 등록</h1> <span class="close-button"></span>
				</span>
				<form action="" class="add-board-form">
					<input class="popup-name-input" type="text" placeholder="사용할 보드명을 입력해주세요."> <input
						class="add-button add-board-button" type="submit" value="보드 추가">
				</form>
			</section>
		</section>

		<section class="profile-popup d-none">
			<header class="popup-header">
				<h1 class="d-none">프로필 팝업</h1>
				<span>PROFILE</span>
			</header>
			<div class="button-container">
				<div class="logout-button">로그아웃</div>
			</div>
		</section>

		<section class="notice-popup d-none">
			<header class="popup-header">
				<h1 class="d-none">알림 팝업</h1>
				<span>NOTICE</span>
			</header>
			<div class="button-container">
			<c:if test="${empty noti }">
				<span style="margin-left : 10px; color: #888888">알림이 없습니다.</span>
			</c:if>
			<c:if test="${not empty noti }">
				<c:forEach var="n" items="${noti }">
					<div class="notice-box">
						<div class="notice-content">(${n.teamName }) 팀에서 초대가 왔습니다.</div>
						<button class="accept-button" data-value="${n.id}">수락</button>
						<button class="reject-button" data-value="${n.id}">거절</button>
					</div>
				</c:forEach>
			</c:if>
			</div>
		</section>

	</div>
</body>

</html>