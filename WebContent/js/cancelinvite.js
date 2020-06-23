window.addEventListener("load", function () {

    const userItem = document.querySelector(".invite-item");
    const team = document.querySelector(".teamId");
    const user = document.querySelector(".invited-user");


    userItem.addEventListener("click", function (e) {
        if (!e.target.classList.contains("cancel-btn")) return;
        const invitee = document.querySelector(".invitee-id");
        let inviteeId = invitee.dataset.value;
        let teamId = team.value;


        let xhr = new XMLHttpRequest();
        xhr.open("GET", `members-cancel?invitee=${inviteeId}&teamId=${teamId}`, true);

        xhr.addEventListener("load", function (e) {
            let result = JSON.parse(xhr.responseText);
            if (result === 0) {
                alert("초대를 취소하지 못했습니다.");
            } else {
                alert("초대를 취소 했습니다.");

                if (e.target.classList.contains("invited-user")) {
                    e.target.remove();
                }
            }
        })

        xhr.send(null);
    });


});
