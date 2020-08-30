package kr.co.api.manager.db.chat.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.chat.model.request.ChatRequestModel;
import kr.co.api.manager.db.chat.model.ChatDetailModel;
import kr.co.api.manager.db.chat.model.ChatModel;
import kr.co.api.manager.db.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/selectMyChat")
    @ApiOperation(value = "SELECT 마이페이지 > 채팅", response = ChatModel.class)
    public DataResponse selectMyChat(@Valid @RequestBody ChatRequestModel chatRequestModel) {
        return DataResponse.builder().data(chatService.selectMyChat(chatRequestModel)).build();
    }

    @PostMapping("/selectMyChatDetail")
    @ApiOperation(value = "SELECT 마이페이지 > 채팅 > 채팅내역", response = ChatDetailModel.class)
    public DataResponse selectMyChatDetail(@Valid @RequestBody ChatRequestModel chatRequestModel) {
        return DataResponse.builder().data(chatService.selectMyChatDetail(chatRequestModel)).build();
    }

    @PostMapping("/insertMyChat")
    @ApiOperation(value = "INSERT 채팅 정보 저장", response = Integer.class)
    public DataResponse insertChat(@Valid @RequestBody ChatModel chatModel) {
        return DataResponse.builder().data(chatService.insertMyChat(chatModel)).build();
    }

    @PostMapping("/insertMyChatDetail")
    @ApiOperation(value = "INSERT 채팅 정보 저장", response = Integer.class)
    public DataResponse insertMyChatDetail(@Valid @RequestBody ChatDetailModel chatDetailModel) {
        return DataResponse.builder().data(chatService.insertMyChatDetail(chatDetailModel)).build();
    }
}
