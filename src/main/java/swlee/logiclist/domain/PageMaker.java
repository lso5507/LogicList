package swlee.logiclist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMaker {

    private int totalCount; // 게시글 총 갯수
    private int startPage; // 현재 페이지
    private int endPage; // 마지막 페이지
    private int totalPage;// 전체 페이지
    private boolean next; // 페이지 다음 버튼 (사용안함)
//    private int displayPageNum = 5;


    public void setTotalCount(int totalCount) {
        // BoardRepository 에서 총 Count를 받아온다.
        this.totalCount = totalCount; //12
        //페이지 관련 데이터 처리
        calcData();
    }

    public void setStartPage(int startPage){
        //Controller 에서 page 값을 받아와 저장
        this.startPage=startPage;
        // 총 10개의 페이지를 보여줌
        setEndPage(startPage+9);

    }
    //현재 사용안함
    public boolean isNext() {
        return next;
    }

    private void calcData() { // 페이지 데이터 처리
        // totalCount%10==0 일시 총 페이지갯수가 10으로 나누어 떨어짐
        if(this.totalCount%10==0){
            this.totalPage=(int)(Math.ceil(this.totalCount/10.0));

        }
        // 맞아 떨이지지 않는다면 +1 하여 나머지 게시글도 출력필요
        else{
            this.totalPage=(int)(Math.ceil(this.totalCount/10.0))+1;
        }
        // totalPage(총 게시글 갯수를 page 단위로 나눈 값)가 endPage보다 크면 Next 버튼 생성
        if(totalPage>endPage){
            //사용안함
            setNext(true);
        }
        else{
            // totalPage가 endPage보다 작으면 Next 버튼 생성하지 않으며, endPage를 totalPage로 변경
            setEndPage(totalPage);
            setNext(false);
        }
    }



}