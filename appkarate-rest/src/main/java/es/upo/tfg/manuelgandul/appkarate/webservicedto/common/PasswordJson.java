package es.upo.tfg.manuelgandul.appkarate.webservicedto.common;

public class PasswordJson {

    String oldPassword;

    String newPassword;

    public PasswordJson() {}

    public PasswordJson(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
