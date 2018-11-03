package packets.requests;

public class LoginRequest
{
    private String username;
    private String password;

    public LoginRequest()
    {

    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
