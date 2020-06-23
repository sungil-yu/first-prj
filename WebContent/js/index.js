window.addEventListener("load",function(){
	
    /*팀추가*/
	var teamList = document.querySelector(".team-list");
    var addTeamBtn = document.querySelector(".add-team-btn");
    var addTeamPop = document.querySelector(".add-team-popup");
    var teamCloseBtn = addTeamPop.querySelector(".close-button");
    var addTeamForm = addTeamPop.querySelector(".add-team-form");
    var teamTitleInput = addTeamForm.querySelector(".popup-name-input");

    /*보드추가*/
    var addBoardBtn = document.querySelector(".add-board-btn");
    var addBoardPop = document.querySelector(".add-board-popup");
    var boardCloseBtn = addBoardPop.querySelector(".close-button");
    var addBoardForm = addBoardPop.querySelector(".add-board-form");
    var boardTitleInput = addBoardForm.querySelector(".popup-name-input");

    /*상단 보드버튼 클릭시, 팝업*/
    var boardBtn = document.querySelector(".board-btn");
    var boardPop = document.querySelector(".header-board-popup");


    // notice button
    var noticeButton = document.querySelector(".right-menu>li");
    var noticePopup = document.querySelector(".notice-popup");

    noticePopup.addEventListener("click",function(e){
        if(!e.target.classList.contains("accept-button")) return;
        var iid = e.target.dataset.value;
        window.location.href = `accept?iid=${iid}`;
    });

    noticePopup.addEventListener("click",function(e){
        if(!e.target.classList.contains("reject-button")) return;
        //reject controller
        var iid = e.target.dataset.value;
        var xhr = new XMLHttpRequest();
        xhr.open('get',`reject?iid=${iid}`,true);
        xhr.onload = function(){
            if(xhr.responseText==1)
                e.target.parentElement.remove();    
        };
        xhr.send();
    });

    noticeButton.addEventListener("click",function(e){
        noticePopup.classList.toggle("d-none");
        noticeButton.style.backgroundColor = "#00bb96";
    });

    // profile button
    var profileButton = document.querySelector(".right-menu>li+li");
    var profilePopup = document.querySelector(".profile-popup");
    var logoutButton = document.querySelector(".logout-button");

    logoutButton.addEventListener("click",function(e){
        window.location.href = "logout";
    });

    profileButton.addEventListener("click",function(e){
        profilePopup.classList.toggle("d-none");
    });

    //team-detail
    teamList.addEventListener("click",function(e){
        if(!e.target.classList.contains("team-detail-button")) return;
        var teamId = e.target.dataset.value;
        //성일이형 팀 디테일 컨트롤에 붙힘
        window.location.href=`team/members?teamId=${teamId}`; 
    });

    boardBtn.onclick = function(){
    	if (!boardPop.classList.contains("d-none")) {
    		boardPop.classList.add("d-none");
    	} else {
    		boardPop.classList.remove("d-none");
    	}
    };

    teamList.onclick = function(e){

        if(!e.target.classList.contains("add-board-btn")) return;
        addBoardPop.classList.remove("d-none");

        teamId = e.target.nextElementSibling.innerText;

    };
    
    //보드reg
    addBoardForm.onsubmit = function(e){
        e.preventDefault();

        var name = boardTitleInput.value;
        
        if(name == "") {
            alert("보드명을 입력하지 않았습니다.");
            return;
        }
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'boardreg', true);
        xhr.setRequestHeader("Content-type","application/json");
        xhr.onload = function(e){

            var result = JSON.parse(xhr.responseText);
            console.log(result);
            bindBoard(result);

            boardTitleInput.value = "";
            
            addBoardPop.classList.add("d-none");
            window.location.reload(true);

        };

        var json = {"teamId":teamId, "name":name};
        var data = JSON.stringify(json);

        xhr.send(data);
        
    };

    //team reg
    addTeamForm.onsubmit = function(e){
        e.preventDefault();

        var name = teamTitleInput.value;
        
        if(name == "") {
            alert("팀명을 입력하지 않았습니다.");
            return;
        }
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'teamreg', true);
        xhr.setRequestHeader("Content-type","application/json");
        xhr.onload = function(){
            var result = JSON.parse(xhr.responseText);

            teamBind(result);
            
            teamTitleInput.value = "";
            addTeamPop.classList.add("d-none");

        };

        var json = {"name":name};
        var data = JSON.stringify(json);
        xhr.send(data);
        
    };
    
    addTeamBtn.onclick = function(){
        addTeamPop.classList.remove("d-none");
    };
    
    teamCloseBtn.onclick = function(){
    	addTeamPop.classList.add("d-none");
    };

    addBoardBtn.addEventListener("click",function(){
    	addBoardPop.classList.remove("d-none");
    });
    
    boardCloseBtn.onclick = function(){
    	addBoardPop.classList.add("d-none");
    };
    
    function teamBind(result){
        var template = `
                    <section class="team">
						<span>
							<h1>${result.name}</h1>
							<ul class="team-btn">
								<li>
									<span></span>
									<span class="add-board-btn">보드 등록</span>
									<div class="d-none">${result.id}</div>
								</li>
								<li>
								    <span></span>
									<span class="team-detail-button" data-value="${result.id}">팀 관리</span>
								</li>
							</ul>
						</span>
						
						<ul class="board-list">
								                    		
                        </ul>
                        
                    </section>`
        var teamButtonSpan = document.querySelector(".team-button-span");
        teamButtonSpan.insertAdjacentHTML("afterend",template);
    }

    function bindBoard(result){
        var template = `<li><a href="/board/board?bid=${result.id }">${result.name}</a></li>`;
    }
});