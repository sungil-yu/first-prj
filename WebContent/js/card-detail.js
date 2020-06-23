window.addEventListener("load", function(){
    var view = document.querySelector(".view");
    var nameContainer = view.querySelector(".name-container");
    var name = view.querySelector(".name");
    var nameContainerHidden = view.querySelector(".name-container-hidden");
    var nameHidden = nameContainerHidden.querySelector(".name-hidden");

 // --------------name인풋창 보여주기-----------------------
    nameContainer.onclick = function(e){
        nameContainer.classList.add("d-none");
        nameContainerHidden.classList.remove("d-none");
        nameHidden.focus();
    }

 // -----------nameHidden 다시 감추기------------------------
    
    nameHidden.onkeydown = function(e){
        
    //  엔터키 눌렀을 때 수정하고 감추기
        if(e.keyCode==13){
            e.preventDefault();
            var nameContent = nameHidden.value;
            
            var xhr = new XMLHttpRequest();
            
            xhr.open('GET', `detail/name-reg?content=${nameContent}`, true);
            
            var card;

            xhr.onload = function(){
                card = JSON.parse(xhr.responseText);
                name.innerHTML = `${card.content}`;
                
            }
            xhr.send();

            nameContainerHidden.classList.add("d-none");
            nameContainer.classList.remove("d-none");
            
        }

    //  esc키 눌러을 때 감추기
        if(e.keyCode==27){
            nameHidden.value= "";
            nameContainerHidden.classList.add("d-none");
            nameContainer.classList.remove("d-none");

        }
    }

    var nameConfirm = nameContainerHidden.querySelector(".confirm");
    
    // 확인 눌렀을 때 수정하기
    nameConfirm.onclick = function(e){
        var nameContent = nameHidden.value;
            
        var xhr = new XMLHttpRequest();
        
        xhr.open('GET', `detail/name-reg?content=${nameContent}`, true);
        
        var card;

        xhr.onload = function(){
            card = JSON.parse(xhr.responseText);
            name.innerHTML = `${card.content}`;
            
        }
        xhr.send();

        nameContainerHidden.classList.add("d-none");
        nameContainer.classList.remove("d-none");
    }
    var nameCancel = nameContainerHidden.querySelector(".cancel");
    
    // 취소 눌렀을 때 텍스트 지우고 바꿔주기
    nameCancel.onclick = function(e){
        nameHidden.value= "";
        nameContainerHidden.classList.add("d-none");
        nameContainer.classList.remove("d-none");
    }

 // ------------descripion창 보여주기 및 수정-----------  
    
    var desContainer = view.querySelector(".des-container");
    var desContainerHidden = view.querySelector(".des-container-hidden");
    var desHidden = view.querySelector(".des-hidden");
    var des = view.querySelector(".des");
   
    // --------------des인풋창 보여주기-----------------------
    desContainer.onclick = function(e){
        desContainer.classList.add("d-none");
        desContainerHidden.classList.remove("d-none");
        desHidden.focus();
    }

    // -----------desHidden 수정 or 취소 후 다시 감추기------------------------
        
    // des를 등록할지 수정할지 구분해주는 상태변수
    var desReg = 0;

    desHidden.onkeydown = function(e){
    //  엔터키 눌렀을 때 수정하고 감추기
        if(e.keyCode==13){
            e.preventDefault();
            // 상태 0일때는 수정
            if (desReg==0){
                var desContent = desHidden.value;
                console.log("desContet:",desContent);
                    
                var xhr = new XMLHttpRequest();
                
                xhr.open('GET', `detail/des-edit?des=${desContent}`, true);
                
                var description;
        
                xhr.onload = function(){
                    description = JSON.parse(xhr.responseText);
                    des.innerHTML = `${description.content}`;
                }
                xhr.send();
        
                desContainerHidden.classList.add("d-none");
                desContainer.classList.remove("d-none");
            }

            // 상태 1일때는 등록
            if (desReg==1){
                
    
                var desContent = desHidden.value;
                console.log("desContet:",desContent);
                    
                var xhr = new XMLHttpRequest();
                
                xhr.open('GET', `detail/des-reg?des=${desContent}`, true);
                
                var description;
    
                xhr.onload = function(){
                    description = JSON.parse(xhr.responseText);
                    des.innerHTML = `${description.content}`;
                }
                xhr.send();
    
                desHidden.value= "";
                desContainerHidden.classList.add("d-none");
                desContainer.classList.remove("d-none");
                desReg = 0;
            }
   
            
        }
    //  esc키 눌렀을 때 감추기
        if(e.keyCode==27){
            desHidden.value= "";
            desContainerHidden.classList.add("d-none");
            desContainer.classList.remove("d-none");
          
        }

    }

    var desConfirm = desContainerHidden.querySelector(".confirm");

    // 확인 눌렀을 때 수정하기
    desConfirm.onclick = function(e){
        // 상태 0일때는 수정
        if (desReg==0){
            var desContent = desHidden.value;
            console.log("desContet:",desContent);
                
            var xhr = new XMLHttpRequest();
            
            xhr.open('GET', `detail/des-edit?des=${desContent}`, true);
            
            var description;
    
            xhr.onload = function(){
                description = JSON.parse(xhr.responseText);
                des.innerHTML = `${description.content}`;
            }
            xhr.send();
    
            desContainerHidden.classList.add("d-none");
            desContainer.classList.remove("d-none");
        }

        // 상태 1일때는 등록
        if (desReg==1){
            
            var desContent = desHidden.value;
            console.log("desContet:",desContent);
                
            var xhr = new XMLHttpRequest();
            
            xhr.open('GET', `detail/des-reg?des=${desContent}`, true);
            
            var description;

            xhr.onload = function(){
                description = JSON.parse(xhr.responseText);
                des.innerHTML = `${description.content}`;
            }
            xhr.send();

            desHidden.value= "";
            desContainerHidden.classList.add("d-none");
            desContainer.classList.remove("d-none");
            desReg = 0;
        }

    }

    var desCancel = desContainerHidden.querySelector(".cancel");
    
    // 취소 눌렀을 때 텍스트 지우고 바꿔주기
    desCancel.onclick = function(e){
        desHidden.value= "";
        desContainerHidden.classList.add("d-none");
        desContainer.classList.remove("d-none");
    }


// ----------------menu-button----------
    var menuButton = document.querySelector(".menu-button");
    var menu = document.querySelector(".detail-menu");
    var closebutton = document.querySelector(".detail-menu-container .close-button");
    var menuonOff = 0;

    // 버튼눌렀을때 d-none풀기
    menuButton.addEventListener("click", function(e){
        if(menuonOff == 0){
            menu.classList.remove("d-none");
            menuonOff = 1;
        }
        else{
            menu.classList.add("d-none");
            menuonOff = 0;
        }
        
    })
    
    closebutton.onclick = function(){

        menu.classList.add("d-none");

    };
   
   window.addEventListener("load", function(){
    var view = document.querySelector(".view");
    var nameContainer = view.querySelector(".name-container");
    var name = view.querySelector(".name");
    var nameContainerHidden = view.querySelector(".name-container-hidden");
    var nameHidden = nameContainerHidden.querySelector(".name-hidden");

 // --------------name인풋창 보여주기-----------------------
    nameContainer.onclick = function(e){
        nameContainer.classList.add("d-none");
        nameContainerHidden.classList.remove("d-none");
        nameHidden.focus();
    }

 // -----------nameHidden 다시 감추기------------------------
    
    nameHidden.onkeydown = function(e){
        
    //  엔터키 눌렀을 때 수정하고 감추기
        if(e.keyCode==13){
            e.preventDefault();
            var nameContent = nameHidden.value;
            
            var xhr = new XMLHttpRequest();
            
            xhr.open('GET', `detail/name-reg?content=${nameContent}`, true);
            
            var card;

            xhr.onload = function(){
                card = JSON.parse(xhr.responseText);
                name.innerHTML = `${card.content}`;
                
            }
            xhr.send();

            nameContainerHidden.classList.add("d-none");
            nameContainer.classList.remove("d-none");
            
        }

    //  esc키 눌러을 때 감추기
        if(e.keyCode==27){
            nameHidden.value= "";
            nameContainerHidden.classList.add("d-none");
            nameContainer.classList.remove("d-none");

        }
    }

    var nameConfirm = nameContainerHidden.querySelector(".confirm");
    
    // 확인 눌렀을 때 수정하기
    nameConfirm.onclick = function(e){
        var nameContent = nameHidden.value;
            
        var xhr = new XMLHttpRequest();
        
        xhr.open('GET', `detail/name-reg?content=${nameContent}`, true);
        
        var card;

        xhr.onload = function(){
            card = JSON.parse(xhr.responseText);
            name.innerHTML = `${card.content}`;
            
        }
        xhr.send();

        nameContainerHidden.classList.add("d-none");
        nameContainer.classList.remove("d-none");
    }
    var nameCancel = nameContainerHidden.querySelector(".cancel");
    
    // 취소 눌렀을 때 텍스트 지우고 바꿔주기
    nameCancel.onclick = function(e){
        nameHidden.value= "";
        nameContainerHidden.classList.add("d-none");
        nameContainer.classList.remove("d-none");
    }

 // ------------descripion창 보여주기 및 수정-----------  
    
    var desContainer = view.querySelector(".des-container");
    var desContainerHidden = view.querySelector(".des-container-hidden");
    var desHidden = view.querySelector(".des-hidden");
    var des = view.querySelector(".des");
   
    // --------------des인풋창 보여주기-----------------------
    desContainer.onclick = function(e){
        desContainer.classList.add("d-none");
        desContainerHidden.classList.remove("d-none");
        desHidden.focus();
    }

    // -----------desHidden 수정 or 취소 후 다시 감추기------------------------
        
    // des를 등록할지 수정할지 구분해주는 상태변수
    var desReg = 0;

    desHidden.onkeydown = function(e){
    //  엔터키 눌렀을 때 수정하고 감추기
        if(e.keyCode==13){
            e.preventDefault();
            // 상태 0일때는 수정
            if (desReg==0){
                var desContent = desHidden.value;
                console.log("desContet:",desContent);
                    
                var xhr = new XMLHttpRequest();
                
                xhr.open('GET', `detail/des-edit?des=${desContent}`, true);
                
                var description;
        
                xhr.onload = function(){
                    description = JSON.parse(xhr.responseText);
                    des.innerHTML = `${description.content}`;
                }
                xhr.send();
        
                desContainerHidden.classList.add("d-none");
                desContainer.classList.remove("d-none");
            }


            // 상태 1일때는 등록
            if (desReg==1){
                
    
                var desContent = desHidden.value;
                console.log("desContet:",desContent);
                    
                var xhr = new XMLHttpRequest();
                
                xhr.open('GET', `detail/des-reg?des=${desContent}`, true);
                
                var description;
    
                xhr.onload = function(){
                    description = JSON.parse(xhr.responseText);
                    des.innerHTML = `${description.content}`;
                }
                xhr.send();
    
                desHidden.value= "";
                desContainerHidden.classList.add("d-none");
                desContainer.classList.remove("d-none");
                desReg = 0;
            }
   
            
        }
    //  esc키 눌렀을 때 감추기
        if(e.keyCode==27){
            desHidden.value= "";
            desContainerHidden.classList.add("d-none");
            desContainer.classList.remove("d-none");
          
        }

    }

    var desConfirm = desContainerHidden.querySelector(".confirm");

    // 확인 눌렀을 때 수정하기
    desConfirm.onclick = function(e){
        // 상태 0일때는 수정
        if (desReg==0){
            var desContent = desHidden.value;
            console.log("desContet:",desContent);
                
            var xhr = new XMLHttpRequest();
            
            xhr.open('GET', `detail/des-edit?des=${desContent}`, true);
            
            var description;
    
            xhr.onload = function(){
                description = JSON.parse(xhr.responseText);
                des.innerHTML = `${description.content}`;
            }
            xhr.send();
    
            desContainerHidden.classList.add("d-none");
            desContainer.classList.remove("d-none");
        }

        // 상태 1일때는 등록
        if (desReg==1){
            
            var desContent = desHidden.value;
            console.log("desContet:",desContent);
                
            var xhr = new XMLHttpRequest();
            
            xhr.open('GET', `detail/des-reg?des=${desContent}`, true);
            
            var description;

            xhr.onload = function(){
                description = JSON.parse(xhr.responseText);
                des.innerHTML = `${description.content}`;
            }
            xhr.send();

            desHidden.value= "";
            desContainerHidden.classList.add("d-none");
            desContainer.classList.remove("d-none");
            desReg = 0;
        }

    }

    var desCancel = desContainerHidden.querySelector(".cancel");
    
    // 취소 눌렀을 때 텍스트 지우고 바꿔주기
    desCancel.onclick = function(e){
        desHidden.value= "";
        desContainerHidden.classList.add("d-none");
        desContainer.classList.remove("d-none");
    }


// ----------------menu-button----------
    var menuButton = document.querySelector(".menu-button");
    var menu = document.querySelector(".detail-menu");
    var closebutton = document.querySelector(".detail-menu-container .close-button");
    var menuonOff = 0;

    // 버튼눌렀을때 d-none풀기
    menuButton.addEventListener("click", function(e){
        if(menuonOff == 0){
            menu.classList.remove("d-none");
            menuonOff = 1;
        }
        else{
            menu.classList.add("d-none");
            menuonOff = 0;
        }
        
    })
   
   closebutton.onclick = function(){

        menu.classList.add("d-none");

    };
   
   

    var addDes = menu.querySelector(".add-des");
    var addComment = menu.querySelector(".add-comment");

// -------버튼으로 추가하는 폼 보여주기------
    
    //-----------des 새로 등록-----------
    addDes.addEventListener("click", function(e){
        desReg = 1;

        desContainer.classList.add("d-none");
        desContainerHidden.classList.remove("d-none");
        desHidden.focus();

    })

    
    // -------------comment-------------
    var commentContainer = view.querySelector(".comment-container");
    var commentContent = view.querySelector(".comment");
    var commentContainerHidden = view.querySelector(".comment-container-hidden");
    var commentHidden = view.querySelector(".comment-hidden");

    
    // 버튼 선택시 d-none 바꿔치기
    addComment.addEventListener("click", function(e){
        commentContainerHidden.classList.remove("d-none");
        commentHidden.focus();
    })
   
    // -----------commentHidden 등록 or 취소 배열------------------------
    
   

    commentHidden.onkeydown = function(e){
    //  엔터키 눌렀을 때 등록하고 배열넣고 감추기
        if(e.keyCode==13){
            e.preventDefault();
            var comment = e.target.value;
            // stringfy는 키가 없고 값만 있을 때, 문자열을 json으로 바꿔줌
            var json = JSON.stringify(comment);
            var xhr = new XMLHttpRequest();
            
            xhr.open('post', `detail/comment-reg`, true);
            xhr.setRequestHeader("Content-type","application/json");
        
            xhr.onload = function(){
                comment = JSON.parse(xhr.responseText);
                var commentWrapper = view.querySelector(".comment-wrapper");
                commentWrapper = "";
            
                var m;
                var xhr2 = new XMLHttpRequest();
                xhr2.open('get', `detail/comment-reg?m=${m}`, true);
                
                xhr2.onload = function(){
                    m = JSON.parse(xhr2.responseText);
                    commentWrapper =`<div class="comment-wrapper">
						                   <div class="comment-body">
						                        <span class="comment-writter-list">
						                            <div class="comment-writer">
						                                ${m.nickname}(${m.email})
						                            </div>
						                            <div class="comment-regdate">
						                                ${comment.regDate}
						                            </div>
						                        </span>
						                        <div class="comment-content">
						                            ${comment.content}
						                        </div>
						                    </div>
						
						                    <div class="btn-comment-container">
						                        <div class="btn-edit-comment">
                    								수정
						                        </div>
						                        <div class="btn-del-comment">
                    								삭제
						                        </div>
						                    </div>
						                </div>`;

                    commentContainer.insertAdjacentHTML("afterbegin",commentWrapper);
                }
                xhr2.send();
            }
            xhr.send(json);
            
            commentHidden.value= "";
            commentContainerHidden.classList.add("d-none");
            commentContainer.classList.remove("d-none");
        }
    

        
     //  esc키 눌렀을 때 감추기
        if(e.keyCode==27){
            commentHidden.value= "";
            commentContainerHidden.classList.add("d-none");
            commentContainer.classList.remove("d-none");
        }

    }
    
    var commentConfirm = commentContainerHidden.querySelector(".confirm");

    // 확인 눌렀을 때 등록하기
    
    commentConfirm.onclick = function(e){
        var comment = e.target.previousElementSibling.value;
        // stringfy는 키가 없고 값만 있을 때, 문자열을 json으로 바꿔줌
        var json = JSON.stringify(comment);
        var xhr = new XMLHttpRequest();
        
        xhr.open('post', `detail/comment-reg`, true);
        xhr.setRequestHeader("Content-type","application/json");
        
        xhr.onload = function(){
            comment = JSON.parse(xhr.responseText);
            var commentWrapper = view.querySelector(".comment-wrapper");
            commentWrapper = "";
        
            var m;
            var xhr2 = new XMLHttpRequest();
            xhr2.open('get', `detail/comment-reg?m=${m}`, true);
            
            xhr2.onload = function(){
                m = JSON.parse(xhr2.responseText);
                commentWrapper =`<div class="comment-wrapper">
				                    	<div class="comment-body">
						                    <span class="comment-writter-list">
						                        <div class="comment-writer">
						                            ${m.nickname}(${m.email})
						                        </div>
						                        <div class="comment-regdate">
						                            ${comment.regDate}
						                        </div>
						                    </span>
						                    <div class="comment-content">
						                        ${comment.content}
							                    </div>
							                </div>
							
								                <div class="btn-comment-container">
								                    <div class="btn-edit-comment">
								                        수정
								                    </div>
								            <div class="btn-del-comment">
		                								삭제
						                    </div>
						                </div>
						            </div>`;

                commentContainer.insertAdjacentHTML("afterbegin",commentWrapper);
            }
            xhr2.send();
        }
        xhr.send(json);

        commentHidden.value= "";
        commentContainerHidden.classList.add("d-none");
        commentContainer.classList.remove("d-none");
        
    }

    var commentCancel = commentContainerHidden.querySelector(".cancel");
    
    // 취소 눌렀을 때 텍스트 지우고 바꿔주기
    commentCancel.onclick = function(e){
        commentHidden.value= "";
        commentContainerHidden.classList.add("d-none");
        commentContainer.classList.remove("d-none");
    }

 // -------comment 수정하기--------
    
    // var editBtn = view.querySelector(".btn-edit-comment");
    // var delBtn = view.querySelector(".btn-del-comment");
    var commentContainerHidden2 = view.querySelector(".comment-container-hidden2");
    var commentHidden2 = view.querySelector(".comment-hidden2");
    var commentConfirm2 = view.querySelector(".confirm2");
    var commentCancel2 = view.querySelector(".cancel2");
    var btnContainer = view.querySelector(".btn-comment-container");
    var targetContent;
    var targetWrapper;

    // 수정버튼 눌렀을 때 띄워주기
   
    var editBtn;
    var delBtn;
    // delBtn = e.target.lastElementChild;
    commentContainer.addEventListener("click", function(e){
        if(!e.target.classList.contains("btn-edit-comment")) return;
        editBtn = e.target;
        e.target.parentElement.previousElementSibling.querySelector(".comment-content");
        targetContent = e.target.parentElement
                                .previousElementSibling
                                .querySelector(".comment-content")
                                .innerText;
        targetWrapper = e.target.parentElement.parentElement;
        targetWrapper.classList.add("d-none");
        commentHidden2.value = targetContent;
        commentContainerHidden2.classList.remove("d-none");
        commentHidden2.focus();
    })
    

    // 수정 눌렀을때 댓글 update후 보여주기
    commentConfirm2.onclick = function(e){
        var comment = e.target.previousElementSibling.value;
        var comments = [targetContent, comment];
        var json = JSON.stringify(comments);
        var xhr = new XMLHttpRequest();
        
        xhr.open('post', `detail/comment-edit`, true);
        xhr.setRequestHeader("Content-type","application/json");
        
        xhr.onload = function(){
            comment = JSON.parse(xhr.responseText);

            var m;
            var xhr2 = new XMLHttpRequest();
            xhr2.open('get', `detail/comment-edit?m=${m}`, true);
            
            xhr2.onload = function(){
                m = JSON.parse(xhr2.responseText);
                targetWrapper =`<div class="comment-wrapper">
                                    <div class="comment-body">
                                        <span class="comment-writter-list">
                                            <div class="comment-writer">
                                                ${m.nickname}(${m.email})
                                            </div>
                                            <div class="comment-regdate">
                                                ${comment.regDate}
                                            </div>
                                        </span>
                                        <div class="comment-content">
                                            ${comment.content}
                                        </div>
                                    </div>

                                    <div class="btn-comment-container">
                                        <div class="btn-edit-comment">
                							수정
                                        </div>
                                        <div class="btn-del-comment">
                							삭제
                                        </div>
                                    </div>
                                </div>`;

                commentContainer.insertAdjacentHTML("afterbegin", targetWrapper);
            }
            xhr2.send();
        }
        xhr.send(json);

        commentHidden2.value= "";
        commentContainerHidden2.classList.add("d-none");
        commentContainer.classList.remove("d-none");
        
    }

    // 취소 버튼 눌렀을 때
    commentCancel2.onclick = function(e){
        commentHidden2.value= "";
        commentContainerHidden2.classList.add("d-none");
        targetWrapper.classList.remove("d-none");
    }

    //  -----키눌렀을 때 수정 및 취소
    commentHidden2.onkeydown = function(e){
        //  엔터키 눌렀을 때 등록하고 배열넣고 감추기
            if(e.keyCode==13){
                e.preventDefault();
                var comment = e.target.value;
                var comments = [targetContent, comment];
                var json = JSON.stringify(comments);
                var xhr = new XMLHttpRequest();
                
                xhr.open('post', `detail/comment-edit`, true);
                xhr.setRequestHeader("Content-type","application/json");
                
                xhr.onload = function(){
                    comment = JSON.parse(xhr.responseText);

                    var m;
                    var xhr2 = new XMLHttpRequest();
                    xhr2.open('get', `detail/comment-edit?m=${m}`, true);
                    
                    xhr2.onload = function(){
                        m = JSON.parse(xhr2.responseText);
                        targetWrapper =`<div class="comment-wrapper">
                                             <div class="comment-body">
	                                                <span class="comment-writter-list">
	                                                    <div class="comment-writer">
	                                                        ${m.nickname}(${m.email})
	                                                    </div>
	                                                    <div class="comment-regdate">
	                                                        ${comment.regDate}
	                                                    </div>
	                                                 </span>
	                                                 <div class="comment-content">
	                                                    ${comment.content}
	                                                 </div>
	                                            </div>
	
	                                            <div class="btn-comment-container">
	                                                <div class="btn-edit-comment">
	                                                    	수정
	                                                </div>
	                                                <div class="btn-del-comment">
	                        								삭제
	                                                </div>
	                                            </div>
                                        	</div>`;

                        commentContainer.insertAdjacentHTML("afterbegin", targetWrapper);
                    }
                    xhr2.send();
                }
                xhr.send(json);

            commentHidden2.value= "";
            commentContainerHidden2.classList.add("d-none");
            commentContainer.classList.remove("d-none");
        
        }
            
        //  esc키 눌렀을 때 감추기
        if(e.keyCode==27){
            commentHidden2.value= "";
            commentContainerHidden2.classList.add("d-none");
            targetWrapper.classList.remove("d-none");
        }
    
    }



//  ---------comment 삭제하기---------
    commentContainer.onclick = function(e){
        if(!e.target.classList.contains("btn-del-comment")) return;
        
        targetContent = e.target.parentElement
                                .previousElementSibling
                                .querySelector(".comment-content")
                                .innerText;
        targetWrapper = e.target.parentElement.parentElement;
        var result = 2;
        var xhr = new XMLHttpRequest();
        xhr.open('get', `detail/comment-del?m=${targetContent}`, true);
        xhr.onload = function(){
            result = xhr.responseText;
            console.log(result);
            console.log(typeof result);

            if(result==1){
                console.log("삭제 성공");
                targetWrapper.remove();
            }
            else if(result==0){
                console.log("삭제 실패");
            }
        }
        xhr.send();
        

        
    }

});
   
   

    var addDes = menu.querySelector(".add-des");
    var addComment = menu.querySelector(".add-comment");

// -------버튼으로 추가하는 폼 보여주기------
    
    //-----------des 새로 등록-----------
    addDes.addEventListener("click", function(e){
        desReg = 1;

        desContainer.classList.add("d-none");
        desContainerHidden.classList.remove("d-none");
        desHidden.focus();

    })

    
    // -------------comment-------------
    var commentContainer = view.querySelector(".comment-container");
    var commentContent = view.querySelector(".comment");
    var commentContainerHidden = view.querySelector(".comment-container-hidden");
    var commentHidden = view.querySelector(".comment-hidden");

    
    // 버튼 선택시 d-none 바꿔치기
    addComment.addEventListener("click", function(e){
        commentContainerHidden.classList.remove("d-none");
        commentHidden.focus();
    })
   
    // -----------commentHidden 등록 or 취소 배열------------------------
    
   

    commentHidden.onkeydown = function(e){
    //  엔터키 눌렀을 때 등록하고 배열넣고 감추기
        if(e.keyCode==13){
            e.preventDefault();
            var comment = e.target.value;
            // stringfy는 키가 없고 값만 있을 때, 문자열을 json으로 바꿔줌
            var json = JSON.stringify(comment);
            var xhr = new XMLHttpRequest();
            
            xhr.open('post', `detail/comment-reg`, true);
            xhr.setRequestHeader("Content-type","application/json");
        
            xhr.onload = function(){
                comment = JSON.parse(xhr.responseText);
                var commentWrapper = view.querySelector(".comment-wrapper");
                commentWrapper = "";
            
                var m;
                var xhr2 = new XMLHttpRequest();
                xhr2.open('get', `detail/comment-reg?m=${m}`, true);
                
                xhr2.onload = function(){
                    m = JSON.parse(xhr2.responseText);
                    commentWrapper =`<div class="comment-wrapper">
                                        <div class="comment-body">
                                            <span class="comment-writter-list">
                                                <div class="comment-writer">
                                                    ${m.nickname}(${m.email})
                                                </div>
                                                <div class="comment-regdate">
                                                    ${comment.regDate}
                                                </div>
                                            </span>
                                            <div class="comment-content">
                                                ${comment.content}
                                            </div>
                                        </div>

                                        <div class="btn-comment-container">
                                            <div class="btn-edit-comment">
                                                수정
                                            </div>
                                            <div class="btn-del-comment">
                                                삭제
                                            </div>
                                        </div>
                                    </div>`;

                    commentContainer.insertAdjacentHTML("afterbegin",commentWrapper);
                }
                xhr2.send();
            }
            xhr.send(json);
            
            commentHidden.value= "";
            commentContainerHidden.classList.add("d-none");
            commentContainer.classList.remove("d-none");
        }
    

        
     //  esc키 눌렀을 때 감추기
        if(e.keyCode==27){
            commentHidden.value= "";
            commentContainerHidden.classList.add("d-none");
            commentContainer.classList.remove("d-none");
        }

    }
    
    var commentConfirm = commentContainerHidden.querySelector(".confirm");

    // 확인 눌렀을 때 등록하기
    
    commentConfirm.onclick = function(e){
        var comment = e.target.previousElementSibling.value;
        // stringfy는 키가 없고 값만 있을 때, 문자열을 json으로 바꿔줌
        var json = JSON.stringify(comment);
        var xhr = new XMLHttpRequest();
        
        xhr.open('post', `detail/comment-reg`, true);
        xhr.setRequestHeader("Content-type","application/json");
        
        xhr.onload = function(){
            comment = JSON.parse(xhr.responseText);
            var commentWrapper = view.querySelector(".comment-wrapper");
            commentWrapper = "";
        
            var m;
            var xhr2 = new XMLHttpRequest();
            xhr2.open('get', `detail/comment-reg?m=${m}`, true);
            
            xhr2.onload = function(){
                m = JSON.parse(xhr2.responseText);
                commentWrapper =`<div class="comment-wrapper">
                                    <div class="comment-body">
                                        <span class="comment-writter-list">
                                            <div class="comment-writer">
                                                ${m.nickname}(${m.email})
                                            </div>
                                            <div class="comment-regdate">
                                                ${comment.regDate}
                                            </div>
                                        </span>
                                        <div class="comment-content">
                                            ${comment.content}
                                        </div>
                                    </div>

                                    <div class="btn-comment-container">
                                        <div class="btn-edit-comment">
                                            수정
                                        </div>
                                        <div class="btn-del-comment">
                                            삭제
                                        </div>
                                    </div>
                                </div>`;

                commentContainer.insertAdjacentHTML("afterbegin",commentWrapper);
            }
            xhr2.send();
        }
        xhr.send(json);

        commentHidden.value= "";
        commentContainerHidden.classList.add("d-none");
        commentContainer.classList.remove("d-none");
        
    }

    var commentCancel = commentContainerHidden.querySelector(".cancel");
    
    // 취소 눌렀을 때 텍스트 지우고 바꿔주기
    commentCancel.onclick = function(e){
        commentHidden.value= "";
        commentContainerHidden.classList.add("d-none");
        commentContainer.classList.remove("d-none");
    }

 // -------comment 수정하기--------
    
    // var editBtn = view.querySelector(".btn-edit-comment");
    // var delBtn = view.querySelector(".btn-del-comment");
    var commentContainerHidden2 = view.querySelector(".comment-container-hidden2");
    var commentHidden2 = view.querySelector(".comment-hidden2");
    var commentConfirm2 = view.querySelector(".confirm2");
    var commentCancel2 = view.querySelector(".cancel2");
    var btnContainer = view.querySelector(".btn-comment-container");
    var targetContent;
    var targetWrapper;

    // 수정버튼 눌렀을 때 띄워주기
   
    var editBtn;
    var delBtn;
    // delBtn = e.target.lastElementChild;
    commentContainer.addEventListener("click", function(e){
        if(!e.target.classList.contains("btn-edit-comment")) return;
        editBtn = e.target;
        e.target.parentElement.previousElementSibling.querySelector(".comment-content");
        targetContent = e.target.parentElement
                                .previousElementSibling
                                .querySelector(".comment-content")
                                .innerText;
        targetWrapper = e.target.parentElement.parentElement;
        targetWrapper.classList.add("d-none");
        commentHidden2.value = targetContent;
        commentContainerHidden2.classList.remove("d-none");
        commentHidden2.focus();
    })
    

    // 수정 눌렀을때 댓글 update후 보여주기
    commentConfirm2.onclick = function(e){
        var comment = e.target.previousElementSibling.value;
        var comments = [targetContent, comment];
        var json = JSON.stringify(comments);
        var xhr = new XMLHttpRequest();
        
        xhr.open('post', `detail/comment-edit`, true);
        xhr.setRequestHeader("Content-type","application/json");
        
        xhr.onload = function(){
            comment = JSON.parse(xhr.responseText);

            var m;
            var xhr2 = new XMLHttpRequest();
            xhr2.open('get', `detail/comment-edit?m=${m}`, true);
            
            xhr2.onload = function(){
                m = JSON.parse(xhr2.responseText);
                targetWrapper =`<div class="comment-wrapper">
                                    <div class="comment-body">
                                        <span class="comment-writter-list">
                                            <div class="comment-writer">
                                                ${m.nickname}(${m.email})
                                            </div>
                                            <div class="comment-regdate">
                                                ${comment.regDate}
                                            </div>
                                        </span>
                                        <div class="comment-content">
                                            ${comment.content}
                                        </div>
                                    </div>

                                    <div class="btn-comment-container">
                                        <div class="btn-edit-comment">
                                            수정
                                        </div>
                                        <div class="btn-del-comment">
                                            삭제
                                        </div>
                                    </div>
                                </div>`;

                commentContainer.insertAdjacentHTML("afterbegin", targetWrapper);
            }
            xhr2.send();
        }
        xhr.send(json);

        commentHidden2.value= "";
        commentContainerHidden2.classList.add("d-none");
        commentContainer.classList.remove("d-none");
        
    }

    // 취소 버튼 눌렀을 때
    commentCancel2.onclick = function(e){
        commentHidden2.value= "";
        commentContainerHidden2.classList.add("d-none");
        targetWrapper.classList.remove("d-none");
    }

    //  -----키눌렀을 때 수정 및 취소
    commentHidden2.onkeydown = function(e){
        //  엔터키 눌렀을 때 등록하고 배열넣고 감추기
            if(e.keyCode==13){
                e.preventDefault();
                var comment = e.target.value;
                var comments = [targetContent, comment];
                var json = JSON.stringify(comments);
                var xhr = new XMLHttpRequest();
                
                xhr.open('post', `detail/comment-edit`, true);
                xhr.setRequestHeader("Content-type","application/json");
                
                xhr.onload = function(){
                    comment = JSON.parse(xhr.responseText);

                    var m;
                    var xhr2 = new XMLHttpRequest();
                    xhr2.open('get', `detail/comment-edit?m=${m}`, true);
                    
                    xhr2.onload = function(){
                        m = JSON.parse(xhr2.responseText);
                        targetWrapper =`<div class="comment-wrapper">
                                            <div class="comment-body">
                                                <div class="comment-writer">
                                                    작성자:${m.nickname}(${m.email})
                                                </div>
                                                <div class="comment-content">
                                                    ${comment.content}
                                                </div>
                                                <div class="comment-regdate">
                                                    작성일:${comment.regDate}
                                                </div>
                                            </div>

                                            <div class="btn-comment-container">
                                                <div class="btn-edit-comment">
                                                    수정
                                                </div>
                                                <div class="btn-del-comment">
                                                    삭제
                                                </div>
                                            </div>
                                        </div>`;

                        commentContainer.insertAdjacentHTML("afterbegin", targetWrapper);
                    }
                    xhr2.send();
                }
                xhr.send(json);

            commentHidden2.value= "";
            commentContainerHidden2.classList.add("d-none");
            commentContainer.classList.remove("d-none");
        
        }
            
        //  esc키 눌렀을 때 감추기
        if(e.keyCode==27){
            commentHidden2.value= "";
            commentContainerHidden2.classList.add("d-none");
            targetWrapper.classList.remove("d-none");
        }
    
    }



//  ---------comment 삭제하기---------
    commentContainer.onclick = function(e){
        if(!e.target.classList.contains("btn-del-comment")) return;
        
        targetContent = e.target.parentElement
                                .previousElementSibling
                                .querySelector(".comment-content")
                                .innerText;
        targetWrapper = e.target.parentElement.parentElement;
        var result = 2;
        var xhr = new XMLHttpRequest();
        xhr.open('get', `detail/comment-del?m=${targetContent}`, true);
        xhr.onload = function(){
            result = xhr.responseText;
            console.log(result);
            console.log(typeof result);

            if(result==1){
                console.log("삭제 성공");
                targetWrapper.remove();
            }
            else if(result==0){
                console.log("삭제 실패");
            }
        }
        xhr.send();
        

        
    }

});