package kr.co.api.manager.db.user.service.impl;

import kr.co.api.manager.db.user.mapper.UserMapper;
import kr.co.api.manager.db.user.model.UserModel;
import kr.co.api.manager.db.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 유저 정보 삽입
     * @param userModel
     * @return
     */
    public int insertUser(UserModel userModel) {
        try{
            userMapper.insertUser(userModel);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }


}
