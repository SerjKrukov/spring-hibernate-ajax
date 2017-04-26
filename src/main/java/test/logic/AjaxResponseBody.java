package test.logic;

/**
 * Created by Serj on 26.04.2017.
 */
public class AjaxResponseBody {
    private String message;
    private UserData userData;

    public AjaxResponseBody() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
