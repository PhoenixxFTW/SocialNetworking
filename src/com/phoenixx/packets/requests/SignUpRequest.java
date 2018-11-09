package com.phoenixx.packets.requests;

import com.phoenixx.packets.objects.SignUpObject;

public class SignUpRequest
{
    private SignUpObject signUpObject;

    public SignUpRequest()
    {

    }

    public SignUpObject getSignUpObject() {
        return signUpObject;
    }

    public void setSignUpObject(SignUpObject signUpObject) {
        this.signUpObject = signUpObject;
    }
}
