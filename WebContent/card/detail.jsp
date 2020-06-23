<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Card-Detail</title>
<link href="../css/reset.css" type="text/css" rel="stylesheet">
<link href="../css/style.css" type="text/css" rel="stylesheet">
<script src="/js/card-detail.js"></script>
</head>
<body>
    <section class="card">
        <h1 class="d-none">전체 영역</h1>
        <section class="card-detail">
            <h1 class="d-none">카드영역</h1>
            
            <section class="detail-menu-container">
                <h1 class="menu-button">메뉴버튼</h1>                
                <section class="detail-menu d-none">
                    <section>
                        <h1>메뉴</h1>
                        <span class="close-button"></span>
                    </section>
                    <div class="add-des">카드 설명 추가</div>
                    <div class="add-comment">댓글 추가</div>
                </section>
            </section>
            
            <div class="view">
                <section class="name-container">
                    <h1 class="d-none">카드이름</h1>
                    <span class="name">${card.content }</span>
                </section>

                <section class="name-container-hidden d-none">
                    <h1>카드이름 수정</h1>
                    <form>
                        <input class="name-hidden" type="text">
                        <div class="confirm">등록</div>
                        <div class="cancel">취소</div>
                    </form>
                </section>
                
                <c:choose>

                <c:when test="${empty description.content}">
                    <section class="des-container d-none">
                        <h1 class="detail-h1 des-h1">설명</h1>
                        <div class="des"></div>
                    </section>
                </c:when>

                <c:otherwise>
                    <section class="des-container">
                        <h1 class="detail-h1 des-h1">설명</h1>
                        <div class="des">${description.content }</div>
                    </section>
                </c:otherwise>
                
                </c:choose>
                
                <section class="des-container-hidden d-none">
                    <h1>설명 추가 / 수정</h1>
                    <form>
                        <input class="des-hidden" type="text">
                        <div class="confirm">등록</div>
                        <div class="cancel">취소</div>
                    </form>
                </section>
                
                
                <section class="comment-container">
                    <h1 class="detail-h1 comment-h1">댓글</h1>
                    
	                <section class="comment-container-hidden comment-add-container d-none">
	                    <h1>댓글 작성</h1>
	                    <form>
	                        <input class="comment-hidden" type="text">
	                        <div class="confirm">등록</div>
	                        <div class="cancel">취소</div>
	                    </form>
	                </section>
	
	                <section class="comment-container-hidden2 comment-add-container d-none">
	                    <h1>댓글 수정</h1>
	                    <form>
	                        <input class="comment-hidden2" type="text">
	                        <div class="confirm2">수정</div>
	                        <div class="cancel2">취소</div>
	                    </form>
	                </section>
                    
                    <c:forEach var="ml" items="${commentMemberList }" varStatus="st">
                    <div class="comment-wrapper">
                        <div class="comment-body">
                            <span class="comment-writter-list">
                                <div class="comment-writer">
                                    ${ml.nickname}(${ml.email})
                                </div>
                                <div class="comment-regdate">
                                    ${commentList[st.index].regDate }
                                </div>
                            </span>

                            <div class="comment-content">
                                ${commentList[st.index].content }
                            </div>
                        </div>

                        <c:if test="${commentList[st.index].writerId eq member.id}">
                         <div class="btn-comment-container">
                             <div class="btn-edit-comment">수정</div>
                             <div class="btn-del-comment">삭제</div>
                         </div>
                        </c:if>
                   </div>  
                    </c:forEach>
                    
                    
                </section>
            </div>
            
            <div class="exit">
                <a href="board?bid=${board.id }">닫기</a>   
             </div>

        </section>

    </section>
</body>
</html>