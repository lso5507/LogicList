    document.getElementById("todolist__input__button").addEventListener("click", addTodoList);
    function createCustomElement(ele){
        const input = document.getElementById("todoList__input__text");

        if(input==null || input.value==""){
            alert("할 일을 입력해주세요.");
            return null;
        }
        if(postTodoList(input)){
            //데이터 전송 성공

        }
        const todoContentDiv_text = document.createElement("div");
        todoContentDiv_text.className = "todoList__body__list__item__text";
        const todoContentDiv_button = document.createElement("div");
        todoContentDiv_button.className = "todoList__body__list__item__button";

        const todoContentDivButton_com = createAddBtn(input.value);
        const todoContentDivButton_rem = createRemoveBtn();

        todoContentDiv_text.appendChild(document.createTextNode(input.value));
        ele.appendChild(todoContentDiv_text);
        todoContentDiv_button.appendChild(todoContentDivButton_com);
        todoContentDiv_button.appendChild(todoContentDivButton_rem);
        ele.appendChild(todoContentDiv_button);
        input.value="";

        return ele
}
    function createRemoveBtn(){
        const todoContentDivButton_rem = document.createElement("button");
        todoContentDivButton_rem.className = "todoList__body__list__item__button__remove";
        todoContentDivButton_rem.addEventListener("click", removeTodoList);
        todoContentDivButton_rem.innerText="삭제";
        return todoContentDivButton_rem;
    }
    function createAddBtn(value){
        //todoContentDiv     내 button 생성
        const todoContentDivButton_com = document.createElement("button");
        todoContentDivButton_com.className = "todoList__body__list__item__button__complete";
        todoContentDivButton_com.addEventListener("click", completeTodoList);
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
            ele.addEventListener("click", removeTodoList);
}
//fetch post
    function postTodoList(ele){

        fetch('/view/todo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "content": ele.value
            })
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(err => console.log(err));
    }
      function completePost(ele){

            fetch('/view/todo_complete', {
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

    function addTodoList(){
        var todoList = document.getElementById("todoList__body__list");

        var todoContent = document.createElement("span");
    //    실제 텍스트를 넣어준다.
        todoContent.className = "todoList__body__list_content";
        const result=createCustomElement(todoContent);
        if(result!=null){
            todoList.appendChild(result);
        }

        //todoContent 내 div 생성
    //    특정 부모 노드의 자식 노드 리스트 중 마지막 자식으로 붙입니다.
//        todoContent.appendChild(document.createTextNode(input.value));


    }
    //TodoList Remove
    function removeTodoList(evt){
       console.log(evt.currentTarget)
       evt.currentTarget.parentNode.parentNode.remove();

    }
    //TodoList Complete
    function completeTodoList(evt){
       console.log(evt.currentTarget.parentNode.parentNode)
       evt.currentTarget.parentNode.parentNode.remove();

    }
