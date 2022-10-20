package swlee.logiclist.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Todo {
    private int id;
    private String content;
    private Date date;

    public Todo() {
        this.date=new Date();
    }
}

