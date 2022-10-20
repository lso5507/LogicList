package swlee.logiclist.controller;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swlee.logiclist.domain.Board;
import swlee.logiclist.domain.Todo;
import swlee.logiclist.domain.User;
import swlee.logiclist.service.BoardService;
import swlee.logiclist.service.TodoService;
import swlee.logiclist.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static swlee.logiclist.utils.ScriptUtils.alert;

@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private  UserService userservice;
    @Autowired
    private BoardService boardService;
    // TODOService-> 추후 분리필요
    @Autowired
    private TodoService todoService;
    @GetMapping("/main")
    public String main(Principal principal,Model model){
        log.info("ViewController In {}",this.getClass());
        if(principal!=null){
            //현재 세션 존재(로그인 유저 있음)
            log.info("Principal::{}",principal.getName());
            model.addAttribute("user",principal.getName());
        }
        List<Board> byOrder = boardService.findByOrder();
        model.addAttribute("boards",byOrder);

        ArrayList<Todo> memoryTodoList = todoService.getMemoryTodoList();
        model.addAttribute("todolist",memoryTodoList);

        return "main";
    }
    @GetMapping("/login")
    public String login(User user){
        log.info("ViewController In Login");
        return "login";
    }
    @GetMapping("/todo")
    public String todo(){
        log.info("Todo Test");
        return "todo_test";
    }
    @ResponseBody
    @PostMapping("/todo")
    public String todo_post(HttpServletResponse res, @RequestBody @NotNull Todo todo)  {
        log.info("Todo_POst Test{}",todo);
        JsonObject obj =new JsonObject();
        try {
            log.info("todo_date::{}",todo.getDate());
            String result = todoService.memorySave(todo);
            if(!result.equals("success")){
                log.error("result::{} is Already Exist",todo.getContent());
                obj.addProperty("result","result:"+todo.getContent()+" is Already Exist");
                return obj.toString();
                }
        }
        catch (Exception e){
            log.error("Todo Post Error",e);
            obj.addProperty("result","failed");
            return obj.toString();
        }
        obj.addProperty("result","success");
        return obj.toString();
    }
    @ResponseBody
    @PostMapping("/todo_data")
    public String todo_complete_todo(@RequestBody @NotNull Todo todo,@RequestParam("param") String param){
        log.info("param:::{}",param);
        try {
            if(param.equals("complete")){
                log.info("complete Todo");
                todoService.upload(todo);
                todoService.remove(todo);
            }
            else if(param.equals("remove")){
                log.info("Delete Todo");
                todoService.remove(todo);
            }
        }
        catch (Exception e){
            log.error("Todo_complete Post Error",e);
            return "failed";
        }
        return "success";
    }
    /*
    @PostMapping("/login")
    public String login_post(User user , Model model){
        User findUser = userservice.findByName(user);
        if(findUser==null){
            return "status/404";
        }
        model.addAttribute("user",user);
        return "main";
    }*/

}
