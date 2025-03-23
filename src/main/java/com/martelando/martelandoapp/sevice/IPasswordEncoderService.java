package com.martelando.martelandoapp.sevice;

public interface IPasswordEncoderService {
    String encode(final String password);

    boolean matches(String rawPassword, String encodedPassword);
}
