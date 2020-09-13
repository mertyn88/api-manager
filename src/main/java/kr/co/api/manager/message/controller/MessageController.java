package kr.co.api.manager.message.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.message.model.MessageModel;
import kr.co.api.manager.message.model.MessageSettingModel;
import kr.co.api.manager.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/sendMessage")
    @ApiOperation(value = "SEND 채팅 메세지 전송", response = Integer.class)
    public DataResponse sendMessage(@Valid @RequestBody MessageSettingModel messageSettingModel) throws IOException {

        //테스트
        /*
        messageSettingModel.setFrom("AAAAHjkhH_E:APA91bGuDoU6hDxqlsDwpor5jR0ariodMxZe0mx8m9KISRHFGu9FMLO7G4ajBCkSZrBpVPhQsmR4WYRQmdUPkL7eXwB1MmpWhLNaKHftz4rGZ4xNSXq7uNtxqBt2CNS-7Ixs0CyFcDh0");
        messageSettingModel.setTo("d0iWN7eUQuem4KP-U5-FzB:APA91bHOePqi5KLVPIbJJCjRnGcd9C-ViUEv2RxboEFQiRjCHG6E-3FdsQoXqha9PlrESNZSkZVvonNgbpY0O02yxLEQmoXn_PRG9IF_3KhCmiNPdlzwGYiihN2wok8Z5Gs7z7WMuZSe");
        messageSettingModel.setPriority("high");
        messageSettingModel.setChatSeq(1);
        MessageModel messageModel = new MessageModel();
        messageModel.setChatSourceMessage("판매자가 쓰는 글");
        messageModel.setChatTargetMessage("구매자가 쓰는 글");
        messageSettingModel.setData(messageModel);
        */

        return DataResponse.builder().data(messageService.sendMessage(messageSettingModel)).build();
    }
}
