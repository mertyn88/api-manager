package kr.co.api.manager.db.user.service.impl;

import kr.co.api.manager.db.user.mapper.UserMapper;
import kr.co.api.manager.db.user.model.UserModel;
import kr.co.api.manager.db.user.service.UserService;
import kr.co.api.manager.message.model.MessageModel;
import kr.co.api.manager.message.model.MessageSettingModel;
import kr.co.api.manager.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final String FIRE_BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private final String SEND_MASSAGE_ACCESS_TOKEN = "AAAAHjkhH_E:APA91bGuDoU6hDxqlsDwpor5jR0ariodMxZe0mx8m9KISRHFGu9FMLO7G4ajBCkSZrBpVPhQsmR4WYRQmdUPkL7eXwB1MmpWhLNaKHftz4rGZ4xNSXq7uNtxqBt2CNS-7Ixs0CyFcDh0";


    /**
     * 유저 정보 삽입
     * @param userModel
     * @return
     */
    public int insertUser(UserModel userModel) {

        try{
            /** 유저 로그인 중인지 여부 확인 **/
            boolean isLogin = userMapper.isLoginUser(userModel.getUid());

            /** 로그인중이 아닐 경우 업데이트 **/
            if(!isLogin){
                userMapper.insertUser(userModel);
            }else{
                /** 로그인중인 메세지토큰값을 가져오기 **/
                UserModel loginUserModel = userMapper.selectUser(userModel.getUid());

                /** 모델값 변환 **/
                MessageSettingModel messageSettingModel = new MessageSettingModel(){{
                    setTo(loginUserModel.getMessageToken());
                    setPriority("high");
                    setData(
                        new MessageModel(){{
                            setLogoutForce(true);
                        }}
                    );
                }};

                System.out.println(loginUserModel);
                System.out.println(userModel);

                /** 새로운 데이터로 업데이트 **/
                userMapper.insertUser(userModel);

                return HttpUtil.getPostUrl(messageSettingModel, FIRE_BASE_URL, SEND_MASSAGE_ACCESS_TOKEN);
            }

        }catch (DataAccessException | IOException e){
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }

    @Override
    public int updateLogoutUser(String uid) {
        try {
            userMapper.updateLogoutUser(uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }
}
