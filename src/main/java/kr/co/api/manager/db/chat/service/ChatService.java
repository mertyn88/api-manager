package kr.co.api.manager.db.chat.service;

import kr.co.api.manager.db.chat.model.request.ChatRequestModel;
import kr.co.api.manager.db.chat.model.ChatDetailModel;
import kr.co.api.manager.db.chat.model.ChatModel;

import java.util.List;

public interface ChatService {


    List<ChatModel> selectMyChat(ChatRequestModel chatRequestModel);
    List<ChatDetailModel> selectMyChatDetail(ChatRequestModel chatRequestModel);
    int insertMyChat (ChatModel chatModel);
    int insertMyChatDetail (ChatDetailModel chatDetailModel);
}
