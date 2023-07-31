package com.example.myfpl.data.DTO;

import java.io.Serializable;

public class BaseDTO implements Serializable {
    private int status;

    public BaseDTO(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
