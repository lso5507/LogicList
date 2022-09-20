package swlee.logiclist.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swlee.logiclist.domain.Board;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/edit")
    public String edit(){
        return "board/edit";
    }
    @PostMapping("/edit")
    @ResponseBody
    public String edit_post(HttpServletRequest req){
        log.info("content::{}",req.getParameter("content"));
        //Board Binding
        new Board()
        //JSON Response
        Gson gson = new Gson();
        // Json key, value 추가
         JsonObject jsonObject = new JsonObject();
         jsonObject.addProperty("result", "success");
         jsonObject.addProperty("id", 1);
        // JsonObject를 Json 문자열로 변환
         String jsonStr = gson.toJson(jsonObject);
        // 생성된 Json 문자열 출력
         System.out.println(jsonStr); // {"name":"anna","id":1}
        return jsonStr;
    }
}
