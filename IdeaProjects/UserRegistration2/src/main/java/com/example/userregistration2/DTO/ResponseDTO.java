package com.example.userregistration2.DTO;

import com.example.userregistration2.model.UserData;
import lombok.Data;

import java.util.List;

public @Data class ResponseDTO {

    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
