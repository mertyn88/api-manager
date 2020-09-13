package kr.co.api.manager.message.model;

import lombok.Data;

@Data
public class MessageSettingModel {

    private String from;     //보내는 사람 키값
    private String to;       //받는 사람 키값
    private String priority;    //우선순위값? 고정
    private MessageModel data;       //전달받는 메세지
    private Integer chatSeq;    //채팅방 번호

}
