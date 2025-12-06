package body.auth;

import org.json.JSONObject;
import Utils.ConfigReader;

public class LoginBody {
    private String email;
    private String password;

    public LoginBody() {
        this.email = ConfigReader.getProperty("email");
        this.password = ConfigReader.getProperty("password");
    }

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public JSONObject loginData() {
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);
        return body;
    }
}