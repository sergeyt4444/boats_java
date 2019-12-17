package socket_listener;

import java.io.*;
import java.net.Socket;

public class socket_listener extends Thread{
    public Socket socket;
    public InputStream is;
    public DataOutputStream os;

    public socket_listener() {
        socket = null;
        is = null;
        os = null;
    }

    public socket_listener(Socket s, DataInputStream dis, DataOutputStream dos) throws IOException {
        socket = s;
        is = dis;
        os = dos;
    }

    public void run(String s) {

    }
}
