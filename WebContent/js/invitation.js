window.addEventListener("load", function () {

    const userItem = document.querySelector(".invite-item");
    const invitationBtn = document.querySelector(".team-invitation");


    invitationBtn.addEventListener("click", function () {

        let xhr = new XMLHttpRequest();
        xhr.open("GET", "member-invitation", true);
        xhr.addEventListener("load", function () {

            let maps = JSON.parse(xhr.responseText);
            let team = maps.team;
            let iv = maps.invitation;
            let inviter = maps.member;


            invitationMember(inviter, team, iv);



        })
        xhr.send(null);

    })

    function invitationMember(inviter, team, iv) {

        let temp = "";
        userItem.innerHTML = "";
        let listBody = "";

        for (let i = 0; i < inviter.length; i++) {

            temp = `
                         <div class="invited-user invitation">
                            <span>
                                <span><b>${team[i].name}</b></span>                            
                                <span>${inviter[i].uid}(${inviter[i].nickname})님이 팀으로 초대하였습니다.</span>
                                <span class="invited-date">초대한 날짜 : ${iv[i].invDate}</span>
                                <span class="invitation-btn">
                                    <button class="accept-team" data-value=${team[i].id}>수락하기</button>
                                    <button class="refuse-team" data-value=${iv[i].inviterId}>거절하기</button>
                                </span>
                            </span>
                        </div>
                        
                <z/div>`

            listBody += temp;
        }
        userItem.innerHTML = listBody;
    }

})