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
    public void memorySave(Todo todo) {
        memoryTodoList.add(todo);
    }
    public ArrayList<Todo> getMemoryTodoList() {
        return memoryTodoList;
    }
}
