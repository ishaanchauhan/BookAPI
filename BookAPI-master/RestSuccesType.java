package com.bcsutilities;

public class RestSuccesType {

    private String succesMessage;

    public RestSuccesType(String succesMessage){
        this.succesMessage = succesMessage;
    }

    public String getMessage() {
        return succesMessage;
    }
}