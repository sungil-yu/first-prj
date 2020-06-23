window.addEventListener("load",function(){
    var contentBox = document.querySelector(".content-box");
    var boardMain = document.querySelector(".board-main");

    var addListButton = contentBox.querySelector(".add-list-button");
    var cancleAddButton = contentBox.querySelector(".cancle-add-button");
    var spans = contentBox.querySelectorAll(".list-add-form>span");
    var listAddForm = contentBox.querySelector(".list-add-form");
    var regListButton = contentBox.querySelector(".reg-list-button");
    var listTitleInput = contentBox.querySelector(".list-title-input");

    var hiddenPopup = contentBox.querySelector(".hidden-popup");
    var hiddenPopupClose = hiddenPopup.querySelector(".hidden-popup-close");

    var trashSpace = document.querySelector(".trash-space");

    var placeHolder = document.createElement("div");
    var isDragging = false;
    var offset = {x:0,y:0};
    var left = boardMain.offsetLeft
    var top = boardMain.offsetTop;
    var current = null;
    
    var inTrash = false;

    var inbox = true;


    //카드 이동 및 순서바꾸기 


    //카드 체크박스
    contentBox.addEventListener("click",function(e){
        if(!e.target.classList.contains("card-todo-check")) return;
        
        var cardId = e.target.parentElement.dataset.value;
        var ischeck = e.target.checked;
        var xhr = new XMLHttpRequest();

        xhr.open('GET',`cardcheck?cid=${cardId}&chk=${ischeck}`,true);
        xhr.onload = function(){
            var listCheckSpan = e.target.parentElement.parentElement.firstElementChild.firstElementChild.firstElementChild;
            var listCheck = xhr.responseText;
            if(listCheck == 100)
                listCheckSpan.style.color = "red";
            else
                listCheckSpan.style.color = "black";

            listCheckSpan.innerHTML = `(&nbsp;${listCheck}%&nbsp;)`;

        }
        xhr.send();
    });


    //카드 에딧버튼 - 못하겠음(에딧버튼 위에 올리면 빠져나간걸로 판정하여 버튼이 사라짐)
    //포커스 아웃 - 못하겠음 (input에만 적용되서 버튼도 적용안됨)
    //리스트 이동 - 못하겠음 (리스트 이동후에 카드 움직임 이상)

    // trashSpace
    trashSpace.onmouseenter = function(e){
        inTrash = true;
    }
    trashSpace.onmouseleave = function(e){
        inTrash = false;
    }

    //카드 드래그(삭제)
    boardMain.addEventListener("mousedown",function(e){
        if(!e.target.classList.contains("card-box")) return;
        isDragging = true;
        offset.x = e.offsetX;
        offset.y = e.offsetY;
        current = e.target;

        var currentStyle = window.getComputedStyle(current,null);
        var currentWidth = currentStyle.getPropertyValue("width");
        var currentHeight = currentStyle.getPropertyValue("height");

        placeHolder.style.width = currentWidth;
        placeHolder.style.height = currentHeight;
        placeHolder.style.background = "#dddddd";
        placeHolder.style.borderRadius = "3px";
        placeHolder.style.margin = "4px";
        placeHolder.classList.add("d-none");

        var nextCard = e.target.nextElementSibling;
        nextCard.insertAdjacentElement("beforebegin",placeHolder);
      
    });

    boardMain.addEventListener("mouseup",function(e){
        if(current == null) return;
        isDragging = false;
        
        if(placeHolder != null){
            placeHolder.remove();
            current.style.position = "initial";
            current.style.left = "initial";
            current.style.top = "initial";
        }
        
        if(inTrash){
            //current card삭제하기
            var cardId = current.dataset.value;
            var xhr = new XMLHttpRequest();
            xhr.open('GET',`delcard?id=${cardId}`,true);
            xhr.send();
            current.remove();
        }

        if(inbox){
            //detail page 연결 -> card id 가지고..
            var cardId = e.target.dataset.value;
            window.location.href = `detail?cid=${cardId}`;
        }

        trashSpace.classList.add("d-none");
        current.style.transform = "rotate(0deg)";

        current = null;
        inbox = true;
    });

    boardMain.addEventListener("mousemove",function(e){
        e.preventDefault();
        if(!isDragging) return;
        if(current == null) return;

        trashSpace.classList.remove("d-none");
        placeHolder.classList.remove("d-none");
        current.style.position = "absolute";
        current.style.transform = "rotate(3deg)";
        current.style.top = e.y - offset.y - top + "px";
        current.style.left = e.x - offset.x - left + "px";
        inbox = false;
    });

    var oldListTitle = null;
    //listTitle 수정(update)
    contentBox.addEventListener("click", function(e){
        if(!e.target.classList.contains("list-name")) return;
        e.target.classList.add("d-none");
        e.target.nextElementSibling.classList.remove("d-none");
        oldListTitle = e.target.nextElementSibling.innerText;
        e.target.nextElementSibling.focus();
        e.target.nextElementSibling.select();
    });
    //포커스 잃을때
    contentBox.addEventListener("focusout",function(e){
        if(!e.target.classList.contains("list-update-input")) return;
        var newListTitle = e.target.value;
        if(oldListTitle == newListTitle) return;
        var listId = e.target.nextElementSibling.nextElementSibling.value;
        var xhr = new XMLHttpRequest();
        xhr.open('GET',`updatelist?nlt=${newListTitle}&id=${listId}`,true)

        xhr.send();

        e.target.previousElementSibling.innerText = newListTitle;
        
        e.target.classList.add("d-none");
        e.target.previousElementSibling.classList.remove("d-none");
    });

    //list-del-button (리스트삭제)
    contentBox.addEventListener("click", function(e){
        if(!e.target.classList.contains("list-del-button")) return;
        var id = e.target.parentElement.previousElementSibling.lastElementChild.value;
        var xhr = new XMLHttpRequest();
        xhr.open('GET','dellist?id='+id,true); 
        
        xhr.onload = function(){
            var lists = contentBox.querySelectorAll(".todo-list-box");
            for(var i in lists){
                if(Number.isNaN(Number.parseInt(i))) break;
                if(lists[i].firstElementChild.lastElementChild.value == id)
                    lists[i].parentElement.classList.add("d-none");
            }

            hiddenPopup.classList.add("d-none");
        }

        xhr.send();
    });

    //regCardButton
    contentBox.addEventListener("click",function(e){
        if(!e.target.classList.contains("reg-card-button")) return;
        var listId = e.target.previousElementSibling.value;
        var content = e.target.previousElementSibling.previousElementSibling.value;
        
        if(content ==""){
            alert("내용을 입력하지 않았습니다.")
            return;
        }
        var xhr = new XMLHttpRequest();
        xhr.open('POST','regcard',true); 
        xhr.setRequestHeader("content-type","application/json");
        
        xhr.onload = function(){
            var result = JSON.parse(xhr.responseText);
            var listCheck = result.archive;
            var listCheckSpan = e.target.parentElement.parentElement.firstElementChild.firstElementChild.firstElementChild;
            
            if(listCheck == 100)
                listCheckSpan.style.color = "red";
            else
                listCheckSpan.style.color = "black";

            listCheckSpan.innerHTML = `(&nbsp;${listCheck}%&nbsp;)`;

            cancleCardButton(e);
            e.target.previousElementSibling.previousElementSibling.value = "";
            bindCard(result,e);
        }

        var json = {"listId":listId,"createrId":2,"content":content};
        var data = JSON.stringify(json);
        xhr.send(data);
    });

    //regListButton
    listAddForm.onsubmit = function(e){
        e.preventDefault();
        var title = listTitleInput.value;
        

        if(title ==""){
            alert("제목을 입력하지 않았습니다.")
            return;
        }
        var xhr = new XMLHttpRequest();
        xhr.open('POST','reglist',true); //데이터 얻어오기
        xhr.setRequestHeader("content-type","application/json");

        xhr.onload = function(){
            cancleListAdd();
            var result = JSON.parse(xhr.responseText);
            bindList(result);
        }

    var boardId = document.querySelector(".board-info>span").dataset.value;
    var json = {"name":title,"createrId":2,"boardId":boardId};
        var data = JSON.stringify(json);
        xhr.send(data);
    }

    // addListButton(View)
    addListButton.onclick = function(e){
        e.preventDefault();
        spans[0].classList.add("d-none");
        spans[1].classList.remove("d-none");
        spans[1].firstElementChild.focus();
    };

    cancleAddButton.onclick = function(e){
        cancleListAdd();
    };

    // addCardButton(view) -> 여러개
    contentBox.addEventListener("click",function(e){
        if(!e.target.classList.contains("add-card-button")) return;
        e.target.nextElementSibling.classList.remove("d-none");
        e.target.classList.add("d-none");
        e.target.nextElementSibling.firstElementChild.focus();
    });
    contentBox.addEventListener("click",function(e){
        if(!e.target.classList.contains("cancle-card-button"))return;
        cancleCardButton(e);
        e.target.previousElementSibling.previousElementSibling.previousElementSibling.value="";
    });

    //hidden-popup
    contentBox.addEventListener("click",function(e){
        if(!e.target.classList.contains("hidden-menu-button"))return;
        hiddenPopup.style.left = e.x+"px";
        hiddenPopup.classList.toggle("d-none");
        var listIdInput = hiddenPopup.querySelector(".list-id");
        listIdInput.value = e.target.nextElementSibling.value
    });

    hiddenPopupClose.onclick = function(){
        hiddenPopup.classList.add("d-none");
    };

    


    function cancleListAdd(){
        spans[0].classList.remove("d-none");
        spans[1].classList.add("d-none");
        spans[1].firstElementChild.value="";
    }

    function cancleCardButton(e){
        e.target.parentElement.previousElementSibling.classList.remove("d-none");
        e.target.parentElement.classList.add("d-none");
    }

    function bindList(result){
        var template =`<div>
                        <section class="todo-list-box">
                            <header>
                                <h1 class="list-name">${result.name}<span class="list-check-span">(&nbsp;${result.check }%&nbsp;)</span></h1>
                                <input type="text" class="list-update-input d-none" value = "${result.name}">
                                <button class="hidden-menu-button">...</button> 
                                <input type="text" hidden value="${result.id }"> 
                                <!-- ... 버튼 -->
                            </header>
                            <div class="add-card-button">
                                + 카드 추가
                            </div>
                            <div class="d-none">
                                <input class="card-content-input" type="text" placeholder="내용 입력..">
                                <input type="text" hidden value="${result.id }"> 
                                <input class="reg-card-button" type="submit" value="카드 추가">
                                <span class="cancle-card-button">X</span>
                            </div>
                        </section>
                        </div> `
        var addFormDiv = contentBox.querySelector(".list-add-form").parentElement;
        addFormDiv.insertAdjacentHTML("beforebegin",template);
    }

    function bindCard(result,e){
        var addCardDiv = e.target.parentElement.previousElementSibling;
        var template = `<div class="card-box" data-value="${result.id}">
                            ${result.content }
                            <input type="checkbox" class="card-todo-check" name="todo-check">
                        </div>`;
        addCardDiv.insertAdjacentHTML("beforebegin",template);
    }
});