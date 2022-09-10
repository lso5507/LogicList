package swlee.logiclist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/main")
    public String main(){
        log.info("ViewController In {}",this.getClass());
        return "main";
    }
    @GetMapping("login")
    public String login(){
        log.info("ViewController In Login");
        return "login";
    }
}
