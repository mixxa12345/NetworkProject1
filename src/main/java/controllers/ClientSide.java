package controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSide {

    Socket socket;
    String host;
    int port;

    public ClientSide(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        DataOutputStream outToServer
                = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer
                = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //System.out.println(expression);
        //outToServer.writeBytes(expression + "\n");
        //String modText = inFromServer.readLine();
        //System.out.println("REC: " + modText);
        //socket.close();
    }

    public String contactToServer(String expression) throws IOException {
        String receivedText = "";

        DataOutputStream outToServer
                = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer
                = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //System.out.println(expression);
        outToServer.writeBytes(expression + "\n");
        receivedText = inFromServer.readLine();
        System.out.println("RECEIVE: " + receivedText);

        return receivedText;
    }

    public Socket getSocket() {
        return socket;
    }
}
