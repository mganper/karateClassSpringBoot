package es.upo.tfg.manuelgandul.appkarate.webservicedto.common;

public class Token {
    protected String user;

    protected String token;

    public Token() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
