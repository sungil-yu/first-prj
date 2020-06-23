window.addEventListener("load", function () {
    const fileSection = document.querySelector(".profile-setting");
    const profileBtn = fileSection.querySelector(".profile-fileBtn");
    const triggerBtn = fileSection.querySelector(".profile-img-input");
    const img = triggerBtn.querySelector(".profile-img");
    const submit = document.querySelector(".profile-submit");
    const profileSetting = document.querySelector(".profile-click");


    triggerBtn.addEventListener("click", function () {
    	
        let event = new MouseEvent("click", {
            'view': window,
            'bubbles': true,
            'cancelable': true
        });

        profileBtn.dispatchEvent(event)

    })


    let reader = new FileReader();

    reader.onload = (readerEvent) => {
        img.setAttribute('src', readerEvent.target.result);
    };

    profileBtn.addEventListener("change", (changeEvent) => {

        let imgFile = changeEvent.target.files[0];
        reader.readAsDataURL(imgFile);
    });

    profileSetting.addEventListener("click", function () {

        fileSection.classList.toggle("d-none");
    })

    submit.addEventListener("click", function () {

        fileSection.classList.toggle("d-none");
    })

})