package kr.co.api.core.response;

import kr.co.api.manager.db.test.model.Test;
import lombok.Builder;

import java.util.List;

public class DataResponse extends ApiResponse {

  /*  @Builder
    public DataResponse(final Test test, final List<String> errors) {
        super(test);
        this.setErrors(errors);
    }*/

    @Builder
    public DataResponse(final Object data) {
        super(data);
    }
}