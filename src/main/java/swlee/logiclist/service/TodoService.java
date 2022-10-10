package swlee.logiclist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swlee.logiclist.domain.Todo;
import swlee.logiclist.repository.TodoRepository;

@Service
@Slf4j
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    public void save(Todo todo) {
        todoRepository.save(todo);

    }
}
