package kr.co.api.manager.db.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatModel {

    private Integer chatSeq;        //채팅 시퀀스 및 ID
    private String productName;     //물품 이름
    private String chatSourceName;  //본인 이름
    private String chatTargetName;  //상대 이름
    private String chatDate;        //채팅 날짜

    //Insert시에 필요
    private String chatSourceUid;   //본인 UID
    private String chatTargetUid;   //상대 UID
    private String productId;       //물품 ID
}
