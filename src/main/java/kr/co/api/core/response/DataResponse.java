package kr.co.api.core.response;

import lombok.Builder;

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