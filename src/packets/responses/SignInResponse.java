package packets.responses;

/**
 * @author Junaid Talpur
 * - phoenix
 * - 2018-11-08
 * - 1:15 PM
 */
public class SignInResponse
{
    private boolean canLogin;
    private String message;

    public SignInResponse()
    {

    }

    public void setCanLogin(boolean login) {
        this.canLogin = login;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean canLogin() {
        return canLogin;
    }

    public String getMessage() {
        return message;
    }
}
