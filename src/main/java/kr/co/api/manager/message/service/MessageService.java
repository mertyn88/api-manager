package kr.co.api.manager.message.service;

import kr.co.api.manager.message.model.MessageSettingModel;

import java.io.IOException;

public interface MessageService {

    Integer sendMessage(MessageSettingModel messageSettingModel) throws IOException;
}
