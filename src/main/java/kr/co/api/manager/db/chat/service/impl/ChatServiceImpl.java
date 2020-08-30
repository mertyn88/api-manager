package kr.co.api.manager.db.chat.service.impl;

import kr.co.api.manager.db.chat.mapper.ChatMapper;
import kr.co.api.manager.db.chat.model.request.ChatRequestModel;
import kr.co.api.manager.db.chat.model.ChatDetailModel;
import kr.co.api.manager.db.chat.model.ChatModel;
import kr.co.api.manager.db.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    /**
     * 마이페이지 채팅 목록 가져오기
     * @return
     */
    @Override
    public List<ChatModel> selectMyChat(ChatRequestModel chatRequestModel) {
        return chatMapper.selectMyChat(chatRequestModel);
    }


    /**
     * 마이페이지 채팅 목록 선택 했을 경우
     * @param chatRequestModel
     * @return
     */
    @Override
    public List<ChatDetailModel> selectMyChatDetail(ChatRequestModel chatRequestModel) {
        return chatMapper.selectMyChatDetail(chatRequestModel);
    }

    @Override
    public int insertMyChat(ChatModel chatModel) {
        try{
            chatMapper.insertMyChat(chatModel);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }

    @Override
    public int insertMyChatDetail(ChatDetailModel chatDetailModel){
        try{
            chatMapper.insertMyChatDetail(chatDetailModel);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }
}