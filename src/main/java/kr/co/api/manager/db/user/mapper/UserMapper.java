package kr.co.api.manager.db.user.mapper;

import kr.co.api.core.db.annotation.MasterConnection;
import kr.co.api.manager.db.user.model.UserModel;

@MasterConnection
public interface UserMapper {
    void insertUser(UserModel userModel);
}