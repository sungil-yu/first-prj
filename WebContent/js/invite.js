window.addEventListener("load", function () {
    const inviteBox = document.querySelector(".temp-invitation");
    const inviteForm = inviteBox.querySelector(".invite-form");
    const inviteinput = inviteForm.querySelector(".invite-input");
    const inviteBtn = inviteForm.querySelector(".invite-Btn");
    const option = inviteForm.querySelector(".option")
    const autoList = inviteBox.querySelector(".auto-list");
    const inviteUsers = document.querySelector(".invite-item");
    
    //클릭한것 active
    var teamInvited = document.querySelector(".team-invited");
    var teamInviting = document.querySelector(".team-inviting");
    var teamInvitation = document.querySelector(".team-invitation");
    
    teamInvited.addEventListener("click", function () {
    	teamInvited.classList.add("active");
    	teamInviting.classList.remove("active");
    	teamInvitation.classList.remove("active");
    });
    
    teamInviting.addEventListener("click", function () {
    	teamInvited.classList.remove("active");
    	teamInviting.classList.add("active");
    	teamInvitation.classList.remove("active");
    });
    
    teamInvitation.addEventListener("click", function () {
    	teamInvited.classList.remove("active");
    	teamInviting.classList.remove("active");
    	teamInvitation.classList.add("active");
    });


    inviteinput.addEventListener("input", function () {

        let inputValue = inviteinput.value;
        let optionValue = option.value;

        let xhr = new XMLHttpRequest();
        xhr.open("GET", `members-auto?input=${inputValue}&option=${optionValue}`, true);
        xhr.addEventListener("load", function () {
            let map = JSON.parse(xhr.responseText);
            let user = map.members;
            let md = map.Md;
            autoSearch(user , md);
            
        })
        xhr.send(null);



        function autoSearch(user,md) {
        	console.log(user);
        	console.log(md);
            if (inputValue === "") return;

            autoList.innerHTML = "";

            var content = "";

            for (var i = 0; i < user.length; i++) {
                temp =
                    `<div class="team-detail-list">
                        <img src="/profile-img/${md[i].profilePicture}" alt="프로필이미지">
                        <span>
                            <span>${user[i].uid}&nbsp;&#47;&nbsp;</span>
                            <span class="pointer-none">${user[i].nickname}</span>
                        </span>
                    </div>`;

                content += temp;
            }
            autoList.innerHTML = content;
        }

    })

    autoList.addEventListener("click", function (e) {

        inviteinput.value = e.target.innerText;

    })

    inviteForm.addEventListener("submit", function (e) {
        e.preventDefault();

        let inputValue = inviteinput.value;

        function Member(inputValue) {
            this.uid = inputValue;
        }

        let member = new Member(inputValue);

        let xhr = new XMLHttpRequest();

        xhr.open("POST", "members", true);

        xhr.addEventListener("load", function () {
            let errors = JSON.parse(xhr.responseText);
            console.log(xhr.responseText);

            if (errors.NotMember) {
                alert("회원이 존재하지않습니다.");
            }

            if (!errors.isMember) {
                alert("이미 초대된 회원입니다.");
            } else if (errors.isMember) {
                alert(" 초대하였습니다..");
            }

        })
        xhr.setRequestHeader("Content-Type", "application/json");


        xhr.send(JSON.stringify(member));
    })

    inviteUsers.addEventListener("click", function (e) {
        e.preventDefault();

        if (!e.target.classList.contains("remove-member")) return;

        let id = e.target.dataset.value;
        let user = e.target.parentNode;

        function Member(id) {
            this.id = id;
        }

        let member = new Member(id);
        let xhr = new XMLHttpRequest();
        console.log(member);
        xhr.open("POST", "members-remove", true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.addEventListener("load", function () {
            let error = JSON.parse(xhr.responseText);
            if (error.checkDel) {
                alert("Member is delete");
            } else {
                alert("Member is not delete");
            }

            user.remove();

        })

        xhr.send(JSON.stringify(member));
    })



});
