package kr.co.api.manager.db.test.mapper;

import kr.co.api.core.db.annotation.MasterConnection;
import kr.co.api.manager.db.test.model.Test;

import java.util.List;

@MasterConnection
public interface TestMasterMapper {
    List<Test> selectTest();
    int insertTest(Test test);
    int updateTest(Test test);
    int deleteTest(int id);
}