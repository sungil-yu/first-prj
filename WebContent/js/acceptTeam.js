window.addEventListener("load", function () {
    const userItem = document.querySelector(".invite-item");
    const userForm = document.querySelector(".invited-user");

    //accept
    userItem.addEventListener("click", function (e) {

        if (!e.target.classList.contains("accept-team")) return;
        let teamId = e.target.dataset.value;

        let xhr = new XMLHttpRequest();
        xhr.open("GET", `accept?teamId=${teamId}`, true);

        let parent = e.target.parentNode;

        xhr.addEventListener("load", function () {

            let result = JSON.parse(xhr.responseText);

            if (result == 1) {
                alert("수락하였습니다.");
                acceptTeam(parent);
            } else {
                alert("수락에 실패하였습니다.")
            }
        })

        function acceptTeam(parent) {
            parent.remove();
        }
        xhr.send(null);
    })


    userItem.addEventListener("click", function (e) {

        if (!e.target.classList.contains("refuse-team")) return;
        let teamId = e.target.dataset.value;

        let xhr = new XMLHttpRequest();
        xhr.open("GET", `refuse?teamId=${teamId}`, true);

        let parent = e.target.parentNode;

        xhr.addEventListener("load", function () {

            let result = JSON.parse(xhr.responseText);

            if (result == 1) {
                alert("거절 하였습니다.");
                acceptTeam(parent);
            } else {
                alert("거절에 실패하였습니다.")
            }
        })

        function acceptTeam(parent) {
            parent.remove();
        }
        xhr.send(null);
    })
})