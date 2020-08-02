package kr.co.api.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCode {

    SYS_ERROR_CODE_9001("9001", "시스템 에러 : 관리자에게 문의해 주세요.")
    ,   SYS_ERROR_CODE_9002("9002", "통신 에러 : 잘못된 URL을 호출 하였습니다.")
    ,   SYS_ERROR_CODE_9003("9003", "통신 에러 : Http Method가 잘못 되었습니다.")
    ,   SYS_ERROR_CODE_9004("9004", "DB 에러! 재시도 해주세요.")
    ,   SYS_ERROR_CODE_9005("9005", "CACHE 에러!")
    ,   SYS_ERROR_CODE_9006("9006", "(%d) 통신에 실패 하였습니다. 재시도 해주세요.")
    ,   SYS_ERROR_CODE_9007("9007", "(%d) 통신 에러! 재시도 해주세요.")
    ,   SYS_ERROR_CODE_9008("9008", "NOSQL 에러! 재시도 해주세요.")
    ,   SYS_ERROR_CODE_9009("9009", "(%d) 통신에 실패 하였습니다. 재시도 해주세요. (%s)")
    ,   SYS_ERROR_CODE_9999("9999", "DB 에러 : 프로시저 오류! 재시도 해주세요.")



    ,   ERROR_CODE_4000("4000","이미 존재하는 주제어 입니다.")
    ,   ERROR_CODE_4001("4001","연관검색어 저장이 실패하였습니다.")
    ,   ERROR_CODE_4002("4002","존재하지않는 주제어입니다.")

    ,   ERROR_CODE_5000("5000","이미 존재하는 내부도메인입니다.")
    ,   ERROR_CODE_5001("5001","존재하지않는 배포서버 입니다.");

    @Getter
    private final String code;
    @Getter
    private final String description;

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
