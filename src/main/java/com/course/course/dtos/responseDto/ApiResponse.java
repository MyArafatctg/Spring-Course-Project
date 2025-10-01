package com.course.course.dtos.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private Integer statusCode;
    private String message;
    private T data;
    private Object error;

    public static <T> ApiResponse<T> success(String message, T data,Integer statusCode) {
        return new ApiResponse<>(true,statusCode,message,data,null);
    }

    public static <T> ApiResponse<T> error(String message, Object error,Integer statusCode) {
        return new ApiResponse<>(false,statusCode,message,null,error);
    }
}
