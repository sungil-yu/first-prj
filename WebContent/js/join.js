window.addEventListener("load",function(){
    var section = document.querySelector(".join");
    var idInput = section.querySelector(".id-input");
    var pwdInput = section.querySelector(".pwd-input");
    var repwdInput = section.querySelector(".repwd-input");
    var nameInput = section.querySelector(".name-input");
    var nickInput = section.querySelector(".nick-input");
    var emailInput = section.querySelector(".email-input");
    
    var joinForm = section.querySelector(".join-form");

    var idErr = section.querySelector(".id-err")
    var pwdErr = section.querySelector(".pwd-err")
    var repwdErr = section.querySelector(".repwd-err")
    var nickErr = section.querySelector(".nick-err")
    var emailErr = section.querySelector(".email-err")
    var emailOverErr = section.querySelector(".email-over-err")

    var idCheck = false;
    var pwdCheck = false;
    var repwdCheck = false;
    var nameCheck = false;
    var nickCheck = false;
    var emailCheck = false;

    //입력된 아이디 중복확인하기
    idInput.addEventListener("blur",function(e){
        var inputUid = idInput.value;

        if(inputUid == ""){
            idCheck = false;
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open('GET','idsearch?uid='+inputUid,true);

        xhr.onload = function(){
           var isOverrap = xhr.responseText;
           if(isOverrap == 1){
                idInput.value="";
                idCheck = false;
                idErr.classList.remove("d-none");
           }
           else{
                idErr.classList.add("d-none");
                idCheck = true;
           }
        }
        xhr.send();
    });

    var inputPwd = "";
    //pwd확인
    pwdInput.addEventListener("blur",function(e){
        inputPwd = pwdInput.value;

        if(inputPwd == ""){
            pwdCheck = false;
            return;
        }

            pwdErr.classList.add("d-none");
            pwdCheck = true;
    });

    repwdInput.addEventListener("input",function(e){
        if(!pwdCheck){
            pwdErr.classList.remove("d-none");
            repwdInput.value = "";
            return;
        }
    });

    repwdInput.addEventListener("blur",function(e){
        var inputRePwd = repwdInput.value;
        if(inputRePwd!=inputPwd){
            repwdErr.classList.remove("d-none");
            repwdCheck = false;
        }
        else{
            repwdErr.classList.add("d-none");
            repwdCheck = true;
        }
    });

    nameInput.addEventListener("blur",function(e){
        var inputName = nameInput.value;
        if(inputName="")
            nameCheck=false;
        else
            nameCheck=true;
    });

    nickInput.addEventListener("blur",function(e){
        var inputNick = nickInput.value;

        if(inputNick == ""){
            nickCheck = false;
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open('GET','nicksearch?nick='+inputNick,true);
        xhr.onload = function(){
           var isOverrap = xhr.responseText;
           if(isOverrap == 1){
                nickInput.value="";
                nickCheck = false;
                nickErr.classList.remove("d-none");
           }
           else{
                nickErr.classList.add("d-none");
                nickCheck = true;
           }
        }
        xhr.send();
    });

    emailInput.addEventListener("blur",function(e){
        var inputEmail = emailInput.value;

        if(inputEmail=="" || !inputEmail.includes("@")){
            emailCheck = false;
            emailInput.select();
            emailErr.classList.remove("d-none");
            return;
        }
        else
            emailErr.classList.add("d-none");

        var xhr = new XMLHttpRequest();
        xhr.open('GET','emailsearch?email='+inputEmail,true);
        xhr.onload = function(){
           var isOverrap = xhr.responseText;
           if(isOverrap == 1){
                emailInput.value="";
                emailCheck = false;
                emailOverErr.classList.remove("d-none");
           }
           else{
                emailOverErr.classList.add("d-none");
                emailCheck = true;
           }

        }
        xhr.send();
    });

    joinForm.addEventListener("submit",function(e){
        if(!(idCheck&&pwdCheck&&repwdCheck&&nameCheck&&nickCheck&&emailCheck)){
            e.preventDefault();
            alert("필수항목에 올바른 값을 입력하세요.")
        }
    });
})