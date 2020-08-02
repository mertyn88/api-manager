package kr.co.api.manager.db.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataModel {

    private String boardKind;       //게시판 종류
    private Integer boardNo;        //게시판 번호
    private String boardTitle;      //게시판 제목
    private String boardContent;    //게시판 내용
    private String boardWriter;     //게시판 작성자
    //private Integer replyNo;        //댓글 번호
    private String replyContent;    //댓글 내용
    private Integer replySort;      //댓글 정렬 번호
    private Integer replyDepth;     //댓글 깊이
    private String regDate;         //게시판 등록일
    private String chgDate;         //게시판 수정일

    private String boardImgUrl;         //파일 경로
    private String boardImgOriginNm;    //원본 파일 이름
    private String boardImgDestinationNm;   //변환된 파일 이름
}
