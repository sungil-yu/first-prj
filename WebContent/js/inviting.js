window.addEventListener("load", function () {
    const inviteList = document.querySelector(".invite-list");
    const userItem = document.querySelector(".invite-item");
    const teamId = document.querySelector(".teamId");


    inviteList.addEventListener("click", function (e) {


        if (!e.target.classList.contains("team-inviting")) return;
        let teamIdValue = teamId.value;

        let xhr = new XMLHttpRequest();
        xhr.open("GET", `members-inviting?teamId=${teamIdValue}`, true);

        xhr.addEventListener("load", function () {
            let maps = JSON.parse(xhr.responseText);

            let member = maps.member;
            let team = maps.team;
            let invitation = maps.invitation;
            inviting(member, team, invitation);

        })
        xhr.send(null);
    })

    function inviting(member, team, invitation) {


        let temp = "";
        userItem.innerHTML = "";
        let listBody = "";

        for (let i = 0; i < member.length; i++) {
            temp = `
                     <div class="invited-user inviting">
                        <span class="invited-image">
	                    	<img src="../images/profile-icon.png" alt="">
	                     </span>
	                     <span class="invited-member">
	                    	<span><b>${team[i].name}</b></span>
	                     	<span>${member[i].uid} (${member[i].nickname})</span>
	                        <span class="invited-date">초대날짜 : ${invitation[i].invDate}</span>
	                        <button class="cancel-btn">취소하기</button>
	                        <input class="invitee-id d-none" data-value=${invitation[i].inviteeId}>
                        </span>
                    </div>
                    `
            listBody += temp;
        }
        userItem.innerHTML = listBody;
    }

})