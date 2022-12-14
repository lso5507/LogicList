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
import swlee.logiclist.utils.TodoResult;

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
    @PostMapping("/login")
    public String login_post(User user , Model model){
        log.debug("login_POST IN");
        User findUser = userservice.findByName(user);
        if(findUser==null){
            return "status/404";
        }
        model.addAttribute("user",user);
        return "main";
    }
    @GetMapping("/signup")
    public String signup(){
        log.info("ViewController In Signup");
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(@Valid User user, HttpServletResponse response) throws IOException {
        log.info("ViewController In Signup Post");
        log.info("user::{}",user);
//        if(userservice.findByName(user)!=null){
//            alert(response,"이미 존재하는 아이디입니다.");
//            return "redirect:/view/signup";
//        }
        userservice.save(user);
        return "redirect:/view/login";
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
            TodoResult result = todoService.memorySave(todo);
            obj.addProperty("result", result.getCode());
            obj.addProperty("errorMsg",result.getMessage());
            return obj.toString();
        }
        catch (Exception e){
            log.error("Todo Post Error",e);
            obj.addProperty("result", 0);
            obj.addProperty("errorMsg",e.getMessage());
            return obj.toString();
        }

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
            else{
                log.error("Not Supported Parameter::{}",param);
            }
        }
        catch (Exception e){
            log.error("Todo_complete Post Error",e);
            return "failed";
        }
        return "success";
    }


}
