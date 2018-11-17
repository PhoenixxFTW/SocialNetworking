package com.phoenixx.server.utils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.SignUpObject;
import com.phoenixx.packets.requests.PostDataRequest;
import com.phoenixx.packets.requests.SignInRequest;
import com.phoenixx.packets.requests.SignUpRequest;
import com.phoenixx.packets.responses.PostDataResponse;
import com.phoenixx.packets.responses.SignInResponse;
import com.phoenixx.packets.responses.SignUpResponse;
import com.phoenixx.server.ServerNetworkMain;

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

                boolean doesUserExist = ServerNetworkMain.getDatabaseManager().doesUserExist(usernameGiven, passwordGiven, studentNumber);

                System.out.println("RECEIVED REGISTER PACKET!");

                System.out.println("==================================");
                System.out.println("uuid = " + uuid);
                System.out.println("student_number = " + studentNumber);
                System.out.println("usernameGiven = " + usernameGiven);
                System.out.println("full_name = " + fullName);
                System.out.println("email = " + emailGiven);
                System.out.println("password = " + passwordGiven);
                System.out.println("DOES USER EXIST = " + doesUserExist);

                ServerNetworkMain.getDatabaseManager().createNewUserInDB(uuid, studentNumber, usernameGiven, emailGiven,passwordGiven, fullName);

                SignUpResponse response = new SignUpResponse();

                response.setRegisterSuccessFull(doesUserExist);
                response.setMessage(doesUserExist ? "User already exists!" : "Successfully registered!");

                ServerNetworkMain.server.sendToTCP(connectionID, response);

            } else {
                System.out.println("SIGN UP OBJECT WAS NULL WHAT THE FUCK");
            }
        }

        if(object instanceof SignInRequest)
        {
            SignInRequest request = (SignInRequest)object;

            String usernameGiven = request.getUsername();
            String passwordGiven = request.getPassword();

            boolean doesUserExist = ServerNetworkMain.getDatabaseManager().doesUserExist(usernameGiven, passwordGiven, "null");

            SignInResponse response = new SignInResponse();

            response.setCanLogin(doesUserExist);
            response.setMessage(doesUserExist ? "Successfully registered!" : "Unable to find user!" );

            ClientUserObject clientUserObject = ServerNetworkMain.getDatabaseManager().getUserData(usernameGiven, passwordGiven);

            if(clientUserObject != null && doesUserExist)
            {
                response.setClientUserObject(clientUserObject);
                response.setPostDataObjects(ServerNetworkMain.getDatabaseManager().getLatestPostData());
                ServerNetworkMain.server.sendToTCP(connectionID, response);

            } else if (!doesUserExist) {
                ServerNetworkMain.server.sendToTCP(connectionID, response);
            }
        }

        if(object instanceof PostDataRequest)
        {
            PostDataRequest request = (PostDataRequest)object;

            PostDataResponse postDataResponse = new PostDataResponse();

            postDataResponse.setPostDataObject(ServerNetworkMain.getDatabaseManager().getPostDataFromID(request.getPostID()));
            postDataResponse.setPostOwnerObject(ServerNetworkMain.getDatabaseManager().getUserDataFromUUID(request.getUserUUID()));
            ServerNetworkMain.server.sendToTCP(connectionID, postDataResponse);
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