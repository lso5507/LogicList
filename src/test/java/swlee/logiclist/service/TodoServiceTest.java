package swlee.logiclist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swlee.logiclist.domain.Todo;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class TodoServiceTest {
//    private final static TodoService todoService = new TodoService();
    @Autowired
    private  TodoService todoService;



    @BeforeEach
    void setUp() {
        //Todo Domain 5개만들기
        Todo todo1 = new Todo();
        todo1.setContent("todo1");
        Todo todo2 = new Todo();
        //todo2는 하루 전 데이터
        todo2.setContent("todo2");
        todo2.setDate(new Date(System.currentTimeMillis()-86400000));
        Todo todo3 = new Todo();
        todo3.setContent("todo3");
        Todo todo4 = new Todo();
        todo4.setContent("todo4");
        Todo todo5 = new Todo();
        todo5.setContent("todo5");
        //메모리에 저장
        todoService.memorySave(todo1);
        todoService.memorySave(todo2);
        todoService.memorySave(todo3);
        todoService.memorySave(todo4);
        todoService.memorySave(todo5);
    }
    @Test
    void upload(){
        Todo todo=new Todo();

        todo.setContent("todo6");

        todoService.upload(todo);
    }

    @Test
    void getMemoryTodoList() {
        ArrayList<Todo> memoryTodoList = todoService.getMemoryTodoList();
        for (Todo todo : memoryTodoList) {
            System.out.println(todo.getContent());
            //getdate
            System.out.println(todo.getDate());
        }
    }
}