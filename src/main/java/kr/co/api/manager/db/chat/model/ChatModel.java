package kr.co.api.manager.db.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatModel {

    private Integer chatSeq;        //채팅 시퀀스 및 ID
    private Integer chatIndex;      //채팅 인덱스
    private String productName;     //물품 이름
    private String productId;       //물품 ID
    private String chatSourceName;  //본인 이름
    private String chatSourceUid;   //본인 UID
    private String chatSourceMessageToken;  //본인 메세지 토큰값
    private String chatSourceImageUrl;   //본인 이미지URL

    private String chatTargetName;  //상대 이름
    private String chatTargetUid;   //상대 UID
    private String chatTargetMessageToken;  //상대 메세지 토큰값
    private String chatTargetImageUrl;   //상대 이미지URL

    private String chatLastMessage; //채팅 내용
    private String chatLastDate;        //채팅 날짜

}
