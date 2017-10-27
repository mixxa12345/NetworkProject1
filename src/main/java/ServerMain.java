import controllers.ServerSide;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        int port = 6789;
        ServerSide server = new ServerSide(port);
        server.connect();
    }

}
