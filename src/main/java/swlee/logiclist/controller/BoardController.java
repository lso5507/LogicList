package swlee.logiclist.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swlee.logiclist.domain.Board;
import swlee.logiclist.service.BoardService;
import swlee.logiclist.utils.ScriptUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
    @GetMapping("/list")
    public String list(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        // 비정상 접근 핸들링
        if(keyword.isBlank()||keyword.isEmpty()||keyword==null){
            ScriptUtils.alertAndBackPage(response,"비정상적인 접근입니다.");
        }
        List<Board> boards = boardService.findByName(keyword);
        if(boards.isEmpty()){
            ScriptUtils.alertAndBackPage(response,"검색결과가 없습니다.");

        }
        model.addAttribute("boards",boards);

        return "board/list";
    }
    @GetMapping("/update")
    public String update_form(HttpServletRequest req,Model model){
        int id = Integer.parseInt(req.getParameter("bid"));
        Board result = boardService.findById(id);
        model.addAttribute("board",result);
        return "board/update";
    }
    @PostMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest req, Principal principal){
        log.info("update_content::{}",req.getParameter("content"));
        //Board Binding
        Board board = new Board(Integer.parseInt(req.getParameter("bid")),req.getParameter("title"),
                req.getParameter("content"), 0, null, principal.getName());
        Board save = boardService.update(board);
        //JSON Response
        String jsonStr = createJson(save);
        return jsonStr;
    }
    @PostMapping("/edit")
    @ResponseBody
    public String edit_post(HttpServletRequest req, Principal principal){
        log.info("content::{}",req.getParameter("content"));
        log.info("imgArr::{}",req.getParameter("imgArr"));

        //Board Binding
        Board board = new Board(req.getParameter("title"),
                req.getParameter("content"), 0, null, principal.getName());
        Board save = boardService.save(board);
        //JSON Response
        String jsonStr = createJson(save);
        return jsonStr;
    }


    private static String createJson(Board save) {
        Gson gson = new Gson();
        // Json key, value 추가
        JsonObject jsonObject = new JsonObject();
        if(save ==null){
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
