package kr.co.api.manager.db.chat.mapper;

import kr.co.api.core.db.annotation.MasterConnection;
import kr.co.api.manager.db.chat.model.request.ChatRequestModel;
import kr.co.api.manager.db.chat.model.ChatDetailModel;
import kr.co.api.manager.db.chat.model.ChatModel;

import java.util.List;

@MasterConnection
public interface ChatMapper {
    List<ChatModel> selectMyChat(ChatRequestModel chatRequestModel);

    List<ChatDetailModel> selectMyChatDetail(ChatRequestModel chatRequestModel);

    int insertMyChatDetail(ChatDetailModel chatDetailModel);

    int insertMyChat(ChatModel chatModel);

    ChatModel selectChatSeq(ChatModel chatModel);
}