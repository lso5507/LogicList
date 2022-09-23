package swlee.logiclist.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swlee.logiclist.domain.Board;
import swlee.logiclist.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/edit")
    public String edit(){
        return "board/edit";
    }
    @PostMapping("/edit")
    @ResponseBody
    public String edit_post(HttpServletRequest req, Principal principal){
        log.info("content::{}",req.getParameter("content"));
        //Board Binding
        Board board = new Board(req.getParameter("title"),
                req.getParameter("content"), 0, null, principal.getName());
        Board save = boardService.save(board);
        //JSON Response
        Gson gson = new Gson();
        // Json key, value 추가
         JsonObject jsonObject = new JsonObject();
         if(save==null){
             jsonObject.addProperty("result", "failed");

         }else{
             jsonObject.addProperty("result", "success");

         }
        // JsonObject를 Json 문자열로 변환
         String jsonStr = gson.toJson(jsonObject);
        // 생성된 Json 문자열 출력
         System.out.println(jsonStr); // {"name":"anna","id":1}
        return jsonStr;
    }
}
