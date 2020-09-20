package kr.co.api.manager.message.model;

import lombok.Data;

@Data
public class MessageRequestModel {

    private Integer chatSeq;
    private String chatSourceName;
    private String chatSourceMessage;
    private String chatSourceMessageToken;
    private String chatSourceImageUrl;

    private String chatTargetName;
    private String chatTargetMessage;
    private String chatTargetMessageToken;
    private String chatTargetImageUrl;
}
