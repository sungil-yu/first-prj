window.addEventListener("load", function () {
    const team = document.querySelector(".teamId");
    const invitedMember = document.querySelector(".team-invited");
    const userItem = document.querySelector(".invite-item");


    invitedMember.addEventListener("click", function () {

        let teamId = team.value;
        let xhr = new XMLHttpRequest();
        xhr.open("GET", `members-invited?teamId=${teamId}`, true);

        xhr.addEventListener("load", function () {
            let maps = JSON.parse(xhr.responseText);
            let teamMember = maps.teamMember;
            let user = maps.member;
            let leader = maps.leader;

            if (leader.id == user.id) {
                teamLeader(teamMember);
            } else {
                invitedMemberList(teamMember);
            }
        })

        xhr.send(null);
    })

    function teamLeader(teamMember) {

        let temp = "";
        userItem.innerHTML = "";
        let listBody = "";

        for (let i = 0; i < teamMember.length; i++) {
            temp = `
                <div class="invited-user invited">
                    <span class="invited-image">
                        <img src="../images/profile-icon.png">
                    </span>
                    <span class="invited-member">
                        <span><b>${teamMember[i].uid}</b> (${teamMember[i].nickname})</span>
                        <span>${teamMember[i].email}</span>
                        <button class="remove-member" data-value=${teamMember.id}>해고하기</button>
                    <span>
                </div>`
            listBody += temp;
        }
        userItem.innerHTML = listBody;
    }

    function invitedMemberList(teamMember) {
        let temp = "";
        userItem.innerHTML = "";
        let listBody = "";

        for (let i = 0; i < teamMember.length; i++) {
            temp = `
                <div class="invited-user">
                    <span class="invited-image">
                        <img src="../images/profile-icon.png">
                    </span>
                    <span class="invited-member">
                        <span><b>${teamMember[i].uid}</b> (${teamMember[i].nickname})</span>
                        <span>${teamMember[i].email}</span>
                    </span>
                </div>`
            listBody += temp;
        }
        userItem.innerHTML = listBody;
    }

})