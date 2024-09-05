package com.jalil_be_app.madang_app.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BaseResponse<T> {
    private Integer code;
    private String message;
    private Boolean status;
    private T data;

    public static <T> BaseResponse<T> success(T data, String message){
        return new BaseResponse<>(200, message, true, data);
    }

    public static <T> BaseResponse<T> failure(Integer code, String message){
        return new BaseResponse<>(code, message, false, null);
    }
}
