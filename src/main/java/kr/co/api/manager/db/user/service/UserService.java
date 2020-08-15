package kr.co.api.manager.db.user.service;

import kr.co.api.manager.db.user.model.UserModel;

public interface UserService {
    /**
     *  유저 정보에 대한 삽입
     */
    int insertUser(UserModel userModel);
}
