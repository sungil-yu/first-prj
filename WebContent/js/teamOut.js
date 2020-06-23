window.addEventListener("load", function () {
    const teamWrap = document.querySelector(".teams-wrap");
    const teamContainer = document.querySelector(".team");


    teamWrap.addEventListener("click", function (e) {
        console.log("gd");

        if (!e.target.classList.contains("out-team")) return;

        let teamId = e.target.dataset.value;
        let currentTeamContainer = e.target.parentNode;

        while (!currentTeamContainer.classList.contains("team"))
            currentTeamContainer = currentTeamContainer.parentNode;

        console.log(teamId);
        let xhr = new XMLHttpRequest();

        xhr.open("GET", `team-out?teamId=${teamId}`, true)

        xhr.addEventListener("load", function () {

            let teamOut = JSON.parse(xhr.responseText);

            if (teamOut == 0) {
                alert("탈퇴하지 못했습니다.")

            } else {
                alert("탈퇴 하였습니다.")
                removeTeam(currentTeamContainer);
            }
        })
        xhr.send(null);

    })

    function removeTeam(currentTeamContainer) {
        currentTeamContainer.remove();
    }

})