package com.fault_reporting.fault_reporting_service.util;

import org.springframework.http.HttpStatus;

import com.fault_reporting.fault_reporting_service.model.ResponseDto;

public class ResponseUtil {
    public static <T> ResponseDto<T> createSuccessReponse(T data) {
        return new ResponseDto<>(HttpStatus.OK.value(), "SUCCESS", data);
    }

    public static <T> ResponseDto<T> createErrorResponse(int statusCode, String message) {
        return new ResponseDto<>(statusCode, message, null);
    }
}
