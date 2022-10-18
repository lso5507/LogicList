package swlee.logiclist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swlee.logiclist.domain.Todo;
import swlee.logiclist.repository.TodoRepository;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
public class TodoService {

    private static final ArrayList<Todo> memoryTodoList = new ArrayList<>();
    @Autowired
    TodoRepository todoRepository;
    public void upload(Todo todo) {
        todoRepository.save(todo);
    }
    public String memorySave(Todo todo) {
        if(getTodoByContent(todo.getContent())!=null){
            return "Todo Already Exist";
        }
        memoryTodoList.add(todo);
        return "success";
    }
    public ArrayList<Todo> getMemoryTodoList() {
        //memoryTodolist 5개 전달
        if(memoryTodoList.size()>5) {
            return new ArrayList<>(memoryTodoList.subList(0, 5));
        }
        return memoryTodoList;
    }
    public Todo getTodoByContent(String content) {
        for (Todo todo : memoryTodoList) {
            if (todo.getContent().equals(content)) {
                return todo;
            }
        }
        return null;
    }
    public void remove(Todo todo) {
        //todo.getContent()와 같은 내용을 가진 Todo를 삭제
        memoryTodoList.removeIf(t -> t.getContent().equals(todo.getContent()));
    }
}
