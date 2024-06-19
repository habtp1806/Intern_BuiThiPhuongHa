package model;

public class User {
    private String email;
    private String password;
    private String confirmPass;
    private String pidNumber;
    

    //login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //register
    public User(String email, String password, String confirmPass, String pidNumber) {
        this.email = email;
        this.password = password;
        this.confirmPass = confirmPass;
        this.pidNumber = pidNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getPidNumber() {
        return pidNumber;
    }

    public void setPidNumber(String pidNumber) {
        this.pidNumber = pidNumber;
    }
}
