package kr.co.api.manager.db.user.service;

import kr.co.api.manager.db.user.model.UserModel;

public interface UserService {
    /**
     *  유저 정보에 대한 삽입
     */
    int insertUser(UserModel userModel);

    /**
     * 유저 정보 로그인 여부
     */
    //boolean isLoginUser(String uid);

    /**
     * 유저 로그아웃 업데이트
     */
    int updateLogoutUser(String uid);
}
