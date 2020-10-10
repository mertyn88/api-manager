package kr.co.api.manager.message.model;

import lombok.Data;

@Data
public class MessageModel {

    /** 채팅 **/
    private Integer chatSeq;
    private String chatSourceMessage;
    private String chatSourceImageUrl;
    private String chatSourceName;
    private String chatTargetMessage;
    private String chatTargetImageUrl;
    private String chatTargetName;

    /** 로그아웃용 **/
    private boolean logoutForce;

    /** 상품판매용 **/
    private String tradeTitle;
    private String tradeBaseUrl;
    private String tradeImageUrl;
    private String type;



}