package controllers;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
    int port;

    public ServerSide(int port) {
        this.port = port;
    }

    public void connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Starting Server...");
        while(true) {
            Socket connectionSocket = serverSocket.accept();
            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient
                    = new DataOutputStream(connectionSocket.getOutputStream());

            String exp = inFromClient.readLine();
            System.out.print(exp);
            String ans = null;
            try {
                ans = cal(exp);
            } catch (ScriptException e) {
                ans = "error";
            }
            System.out.println(" = " + ans);
            outToClient.writeBytes(ans + "\n");
        }

    }

    private String cal(String expression) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String ans;
        try {
            //if Double
            Double calculated = (Double)engine.eval(expression);
            ans = String.format("%1$,f", calculated);
        } catch (ClassCastException e) {
            //if Int
            Integer calculated = (Integer)engine.eval(expression);
            ans = String.format("%1$,d", calculated);

        }
        return ans;
    }

}
