package swlee.logiclist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMaker {

    private int totalCount; // 게시글 총 갯수
    private int startPage; // 5개의 페이지중 첫번째
    private int endPage; // 5개의 페이지중
    private int totalPage;
    private boolean next; // 페이지 다음 버튼
    private int displayPageNum = 5;


    public void setTotalCount(int totalCount) {
        // Repository 에서 총 Count를 받아온다.
        this.totalCount = totalCount; //12
        calcData();
    }

    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage){
        //Controller 에서 page 값을 받아온다.
        setEndPage(startPage+4);

    }
    public boolean isNext() {
        return next;
    }

    private void calcData() { // 페이지 데이터 처리
        if(this.totalCount%5==0){
            this.totalPage=(int)(Math.ceil(this.totalCount/5.0));

        }else{
            this.totalPage=(int)(Math.ceil(this.totalCount/5.0))+1;
        }
        // totalPage(총 게시글 갯수를 page 단위로 나눈 값)가 endPage보다 크면 Next 버튼 생성
        if(totalPage>endPage){
            setNext(true);
        }
        else{ // totalPage가 endPage보다 작으면 Next 버튼 생성하지 않으며, endPage를 totalPage로 변경
            setEndPage(totalPage);
            setNext(false);
        }
    }



}