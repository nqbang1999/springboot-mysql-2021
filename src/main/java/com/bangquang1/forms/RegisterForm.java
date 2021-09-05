package com.bangquang1.forms;

import javax.validation.constraints.Email;

public class RegisterForm {
    private String usernameInput;
    @Email
    private String emailInput;
    private String passwordInput;
    private String re_passwordInput;

    public RegisterForm() {
    }

    public String getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public String getEmailInput() {
        return emailInput;
    }

    public void setEmailInput(String emailInput) {
        this.emailInput = emailInput;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public String getRe_passwordInput() {
        return re_passwordInput;
    }

    public void setRe_passwordInput(String re_passwordInput) {
        this.re_passwordInput = re_passwordInput;
    }
}
