package com.martelando.martelandoapp.exception;

public class PhoneInUseException extends RuntimeException{
    public PhoneInUseException(String phone) {
        super("Numero de telefone " + phone + "já esta em uso");
    }
}
