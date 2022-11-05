package swlee.logiclist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swlee.logiclist.domain.Todo;
import swlee.logiclist.repository.TodoRepository;
import swlee.logiclist.utils.TodoResult;

import java.util.ArrayList;

import static swlee.logiclist.utils.TodoResult.*;

@Service
@Slf4j
public class TodoService {

    private static final ArrayList<Todo> memoryTodoList = new ArrayList<>();
    @Autowired
    TodoRepository todoRepository;
    public void upload(Todo todo) {
        todoRepository.save(todo);
    }
    //ArrayList Todo upload
    public void upload(ArrayList<Todo> todoArrayList) {
        todoRepository.saveAll(todoArrayList);
    }

    /**
     * @param todo
     * @return - 1 : Todo Upload Success
     * - 0 : Todo Upload Fail (Exception)
     * - -1 : Todo Upload Fail (Todo is null)
     * - -2 : Todo Already Exist
     */
    public TodoResult memorySave(Todo todo) {
        if (todo == null) {
            return FAIL_NULL;
        }
        if(getTodoByContent(todo.getContent())!=null){
            return FAIL_EXIST;
        }
        try {
            memoryTodoList.add(todo);
        } catch (Exception e) {
            log.error("TodoService.memorySave() error", e);
            return FAIL;
        }
        return SUCCESS;
    }
    public ArrayList<Todo> getMemoryTodoList() {
        //memoryTodoList 같은 일자 체크
        //memoryTodolist 5개 전달
        ArrayList<Todo> todos = memoryFilter();
        return todos;
    }

    private ArrayList<Todo> memoryFilter() {
        //new ArrayList
        ArrayList<Todo> removeTodoList = new ArrayList<>();
        for (Todo todo : memoryTodoList) {
            //todo.getData()가 24시간이 지났을경우
            if (todo.getDate().getTime() + 86400000 < System.currentTimeMillis()) { //86400000=24시간
                removeTodoList.add(todo);
                log.info("RemoveTodoList_ADD::{}",todo.getContent());
            }
        }
        if(removeTodoList.size()>0){
            //지우기 전 revmoveTodoList를 DB저장
            upload(removeTodoList);
            //removeTodoList를 memoryTodoList에서 제거
            memoryTodoList.removeAll(removeTodoList);
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
        try {
            memoryTodoList.removeIf(t -> t.getContent().equals(todo.getContent()));
            //같은 내용을 가진 Todo가 없을때
        }catch (Exception e){
            log.error("TodoService.remove() error", e);
        }
    }
}
