package swlee.logiclist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swlee.logiclist.domain.User;
import swlee.logiclist.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private  UserService userservice;
    @GetMapping("/main")
    public String main(){
        log.info("ViewController In {}",this.getClass());
        return "main";
    }
    @GetMapping("/login")
    public String login(User user){
        log.info("ViewController In Login");
        return "login";
    }
    @PostMapping("/login")
    public String login_post(User user , Model model){
        User findUser = userservice.findByName(user);
        if(findUser==null){
            return "status/404";
        }
        return "main";
    }
}
