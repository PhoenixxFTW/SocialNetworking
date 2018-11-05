package server.utils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.objects.SignUpObject;
import packets.requests.LoginRequest;
import packets.requests.SignUpRequest;
import packets.responses.SignUpResponse;
import server.ServerNetworkMain;
import server.managers.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                Database database = ServerNetworkMain.getDatabase();

                boolean doesUserExist = false;
                try {
                    doesUserExist = ServerNetworkMain.getDatabaseManager().doesUserExist(usernameGiven, passwordGiven, studentNumber);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println("RECEIVED REGISTER PACKET!");

                System.out.println("==================================");
                System.out.println("uuid = " + uuid);
                System.out.println("student_number = " + studentNumber);
                System.out.println("usernameGiven = " + usernameGiven);
                System.out.println("full_name = " + fullName);
                System.out.println("email = " + emailGiven);
                System.out.println("password = " + passwordGiven);

                if(!doesUserExist)
                {
                    ServerNetworkMain.getDatabaseManager().createNewUserinDB(uuid, studentNumber, usernameGiven, emailGiven,passwordGiven);

                    SignUpResponse response = new SignUpResponse();

                    response.setRegisterSuccessFull(doesUserExist);
                    response.setMessage(doesUserExist ? "User already exists!" : "Successfully registered!");

                    ServerNetworkMain.server.sendToTCP(connectionID, response);
                }


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