package kr.co.api.manager.db.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDetailModel {

    private Integer chatSeq;            // 채팅 시퀀스
    private String productName;         // 물품 명
    private String chatSourceMessage;   // 판매자 메세지
    private String chatTargetMessage;   // 구매자 메세지
    private String chatDate;            // 메세지 전송 시간

}
