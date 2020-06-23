<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<script src="../js/index.js"></script>
<link href="../css/reset.css" type="text/css" rel="stylesheet">
<link href="../css/style.css" type="text/css" rel="stylesheet">
<title>메모보드</title>
</head>

<body>
	<header class="header">
		<h1 class="logo">
			<a href="/index">
				<img src="../images/logo.png" alt="메모보드">
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
					<li>알림</li>
					<li>프로필</li>
				</ul>
			</nav>
		</section>
	</header>
	
    <section class="profile-header">
            <h1 class="d-none">팀 디테일 메인</h1>
        <section class="team-profile-section">
            <h1 class="d-none">팀명</h1>
            <div class="team-profile">
                <img src="../images/team-image.png" alt="">
                <div class="profile-info">                
                    <div class="profile-name">
						<b>${team.name}</b> (팀장:  ${leader.uid})
                    </div>
                </div>
            </div>
        </section>

        <section class="team-menus-wrap">
            <h1 class="d-none">디테일 메뉴</h1>
            <ul class="team-menus">
                <li class="menu"><a href="boards">보드</a></li>
				<li class="menu"><a href="members">멤버</a></li>
				<li class="menu"><a href="teams">팀</a></li>
            </ul>
        </section>
    </section>


    <main class="team-detail-main">
        <section class="member-wrap team-main-wrap">
            <h1 class="d-none">멤버메뉴</h1>
            <aside class="temp-invitation">
                <h1 class="d-none">멤버초대</h1>
                <form method="POST" class="invite-form">
                    
                    <select class="option d-none">
                        <option value="uid" selected="selected">uid</option>
                    </select>
                    
                    <input type="text" class="invite-input" name="invitee" placeholder="초대할 멤버의 ID를 입력해주세요">
                    <input type="submit" class="invite-Btn" value="초대">
                </form>
                <div class="auto-list">
                </div>
            </aside>

            <section class="member">
                <h1 class="d-none">멤버</h1>
                <div class="invite-list">
                    <button class="team-invited team-invite active">초대된 회원</button>
                    <button class="team-inviting team-invite">초대중인 회원</button>
                    <button class="team-invitation team-invite">받은 초대</button>
                    <input class="teamId d-none" value="${tempTeamId}" name="teamId">
                </div>
                <div class="invite-item">
                    <c:forEach var="MembersList" items="${teamList}">
                        <div class="invited-user invited">
	                        <span class="invited-image">
	                            <img src="../images/profile-icon.png">
                            </span>
                            <span class="invited-member">
	                            <span><b>${MembersList.uid}</b>(${MembersList.nickname})</span>
	                            
	                            <span>${MembersList.email}</span>
                            
                                <c:if test="${User.id eq team.createrId}">
                                    <button class="remove-member" data-value=${MembersList.id}>해고하기</button>
                                </c:if>
                            </span>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </section>
    </main>
</body>

<script src="../js/invite.js"></script>
<script src="../js/inviting.js"></script>
<script src="../js/acceptTeam.js"></script>
<script src="../js/cancelinvite.js"></script>
<script src="../js/invited.js"></script>
<script src="../js/invitation.js"></script>

</html>