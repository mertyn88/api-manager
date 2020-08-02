package kr.co.api.manager.db.test.service;

import java.util.List;

import kr.co.api.manager.db.test.mapper.TestMasterMapper;
import kr.co.api.manager.db.test.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMasterMapper testMasterMapper;

    public List<Test> selectTest() {
        return testMasterMapper.selectTest();
    }

    public int insertTest(Test test) {
        return testMasterMapper.insertTest(test);
    }

    public int updateTest(Test test) {
        return testMasterMapper.updateTest(test);
    }

    public int deleteTest(int id) {
        return testMasterMapper.deleteTest(id);
    }

}
