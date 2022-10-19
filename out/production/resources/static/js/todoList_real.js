    // 입력 버튼 클릭 시
    document.getElementById("todolist__input__button").addEventListener("click", addTodoList);
    async function createCustomElement(ele){
        const input = document.getElementById("todoList__input__text");

        if(input==null || input.value==""){
            alert("할 일을 입력해주세요.");
            return null;
        }
        //await으로 받기
        const result=await postTodoList(input);
        if(result){
            //데이터 전송 성공
            const todoContentDiv_text = document.createElement("div");
            todoContentDiv_text.className = "todoList__body__list__item__text";
            const todoContentDiv_button = document.createElement("div");
            todoContentDiv_button.className = "todoList__body__list__item__button";

            const todoContentDivButton_com = createAddBtn(input.value);
            const todoContentDivButton_rem = createRemoveBtn(input.value);

            todoContentDiv_text.appendChild(document.createTextNode(input.value));
            ele.appendChild(todoContentDiv_text);
            todoContentDiv_button.appendChild(todoContentDivButton_com);
            todoContentDiv_button.appendChild(todoContentDivButton_rem);
            ele.appendChild(todoContentDiv_button);
            input.value="";
            return ele;
        }
        return null;
}
    function createRemoveBtn(value){
        const todoContentDivButton_rem = document.createElement("button");
        todoContentDivButton_rem.className = "todoList__body__list__item__button__remove";
        todoContentDivButton_rem.addEventListener("click", (evt)=>{
            removePost(value);
            removeTodoList(evt);
        });
        todoContentDivButton_rem.innerText="삭제";
        return todoContentDivButton_rem;
    }
    function createAddBtn(value){
        //todoContentDiv     내 button 생성
        const todoContentDivButton_com = document.createElement("button");
        todoContentDivButton_com.className = "todoList__body__list__item__button__complete";
        todoContentDivButton_com.addEventListener("click", (evt)=>{
            completePost(value);
            completeTodoList(evt);
        });
        todoContentDivButton_com.innerText="완료";
        todoContentDivButton_com.setAttribute("content", value);
        return todoContentDivButton_com;
    }
    function completeEvent(ele){
        let content=ele.getAttribute("content");
        let result=completePost(content);
        ele.addEventListener("click", completeTodoList);

    }
    function removeEvent(ele){
        let content=ele.getAttribute("content");
        let result=removePost(content);
        ele.addEventListener("click", removeTodoList);
}
//fetch post
    async function postTodoList(ele){
        let data = await fetch(location.origin.toString()+"/view/todo", {
            method: 'POST',
             headers: {'Content-Type': 'application/json'},
            cache: 'no-cache',
            body: JSON.stringify({
              "content": ele.value})
        })
        data=await data.json();

        if(data.result!="success"){
          alert(data.result);
          return false;
        }
        return true;
    }
    function completePost(ele){

            fetch('/view/todo_data?param=complete', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "content": ele
                })
            })
                .then(data => console.log(data))
                .catch(err => console.log(err));
    }
    function removePost(ele){

                fetch('/view/todo_data?param=remove', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        "content": ele
                    })
                })
                    .then(data => console.log(data))
                    .catch(err => console.log(err));
        }

    async function addTodoList(){
        var todoList = document.getElementById("todoList__body__list");

        var todoContent = document.createElement("span");
    //    실제 텍스트를 넣어준다.
        todoContent.className = "todoList__body__list_content";
        const result=await createCustomElement(todoContent);
        console.log("result::",result);
        if(result!=null){
            alert("addTodoListAppendIn!!")

            todoList.appendChild(result);
        }

        //todoContent 내 div 생성
    //    특정 부모 노드의 자식 노드 리스트 중 마지막 자식으로 붙입니다.
//        todoContent.appendChild(document.createTextNode(input.value));


    }
    //TodoList Remove
    function removeTodoList(evt){
       evt.currentTarget.parentNode.parentNode.remove();

    }
    //TodoList Complete
    function completeTodoList(evt){
       evt.currentTarget.parentNode.parentNode.remove();

    }
