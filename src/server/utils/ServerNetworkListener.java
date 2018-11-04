package server.utils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.objects.SignUpObject;
import packets.requests.LoginRequest;
import packets.requests.SignUpRequest;
import packets.responses.SignUpResponse;
import server.ServerNetworkMain;

public class ServerNetworkListener extends Listener
{
    public void received(Connection connection, Object object)
    {
        int connectionID = connection.getID();

        if(object instanceof SignUpRequest)
        {
            SignUpRequest request = (SignUpRequest)object;

            if(request.getSignUpObject() != null) {
                SignUpObject signUpObject = request.getSignUpObject();

                String uuid = signUpObject.getUuid();
                String studentNumber = signUpObject.getStudentNumber();
                String usernameGiven = signUpObject.getUsername();
                String fullName = signUpObject.getFullName();
                String emailGiven = signUpObject.getEmail();
                String passwordGiven = signUpObject.getPassword();

                System.out.println("RECEIVED REGISTER PACKET!");

                System.out.println("==================================");
                System.out.println("uuid = " + uuid);
                System.out.println("student_number = " + studentNumber);
                System.out.println("usernameGiven = " + usernameGiven);
                System.out.println("full_name = " + fullName);
                System.out.println("email = " + emailGiven);
                System.out.println("password = " + passwordGiven);

                SignUpResponse response = new SignUpResponse();

                response.setRegisterSuccessFull(true);
                response.setMessage("YEEEEEEET  YOU HAVE SUCCESSFULLY REGISTERED @@@@@@@@@@@@");

                ServerNetworkMain.server.sendToTCP(connectionID, response);
            } else {
                System.out.println("SIGN UP OBJECT WAS NULL WHAT THE FUCK");
            }
        }

        if(object instanceof LoginRequest)
        {
            LoginRequest request = (LoginRequest)object;

            System.out.println("Login request received from: " + request.getUsername());
            System.out.println("Password is :" + request.getPassword());
        }
    }

    @Override
    public void connected(Connection connection)
    {
        System.out.println("New connection made with ID: " + connection.getID());
    }

    @Override
    public void disconnected(Connection connection)
    {
        System.out.println("Connection with ID " + connection.getID() + " disconnected!");
    }

}