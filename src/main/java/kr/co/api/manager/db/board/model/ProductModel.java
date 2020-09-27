package kr.co.api.manager.db.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {

    /** 상품정보 **/
    private Integer productId;    //물품 ID
    private String productName;    //물품 명
    private String status;  //물품 상태
    private String saleYn; //물품 판매여부(Y: 판매완료, R: 예약중, S: 판매중)
    private String brand;           //제조사이름
    private String modelNo;        //모델명넘버
    private String serialNo;       //시리얼넘버
    private String repairYn;       //제품수리이력여부


    /** 상품글정보 **/
    private String title;           //제목
    private String content;         //내용
    private String classification;  //분류
    private String tag;             //태그
    private String baseUrl;         //이미지 베이스 URL
    private String imageUrl;       //이미지 URL
    private String displayYn;      //글 노출 여부(삭제기능)
    private String useDate;        //사용기간ß
    private String purchaseKind;   //구매방법
    private String purchasePrice;  //구매당시가격
    private String boxYn;          //제품박스유무
    private String purpose;         //용도
    private String productRelatedUrl; //제품관련URL

    /** 판매자 정보 **/
    private String uid;         //판매자ID
    private String regDate;    //등록날짜
    private String chgDate;    //변경날짜

}
