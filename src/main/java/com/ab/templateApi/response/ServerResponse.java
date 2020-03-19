package com.ab.templateApi.response;

import lombok.Getter;

@Getter
public class ServerResponse {

    private String msg;

    private String code;

    public ServerResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(this == o)
            return true;
        if(o.getClass() != getClass())
            return false;
        return msg.equals(((ServerResponse) o).getMsg()) && code.equals(((ServerResponse) o).getCode());
    }
}

