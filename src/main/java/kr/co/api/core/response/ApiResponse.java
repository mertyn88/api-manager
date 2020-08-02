package kr.co.api.core.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class ApiResponse {
    @NonNull
    private Object data;
    //@NonNull
    //private List<T> dataList;
}