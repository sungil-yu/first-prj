window.addEventListener("load", function () {
    const teamWrap = document.querySelector(".teams-wrap");
    const teamContainer = document.querySelector(".team");


    teamWrap.addEventListener("click", function (e) {

        if (!e.target.classList.contains("remove-team")) return;

        let teamId = e.target.dataset.value;
        let currentTeamContainer = e.target.parentNode;

        while (!currentTeamContainer.classList.contains("team"))
            currentTeamContainer = currentTeamContainer.parentNode;


        let xhr = new XMLHttpRequest();

        xhr.open("GET", `team-remove?teamId=${teamId}`, true)

        xhr.addEventListener("load", function () {

            let teamRemove = JSON.parse(xhr.responseText);

            if (teamRemove == 0) {
                alert("해체하지 못했습니다.")

            } else {
                alert("해체 하였습니다.")
                removeTeam(currentTeamContainer);
            }
        })
        xhr.send(null);

    })

    function removeTeam(currentTeamContainer) {
        currentTeamContainer.remove();
    }

})