package socket_listener;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;

import algorithm.algorithm;
import boat_table.boat_table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import server.*;

public class socket_listener extends Thread{
    public Socket socket;
    public DataInputStream is;
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

    public void run() {
        while (true) {
            String boat_line;
            try {
                if (is != null && os != null) {
                    boat_line = is.readUTF();
                    Gson json = new GsonBuilder().setPrettyPrinting().create();

                    Type type1 = new TypeToken<boat_table>(){}.getType();
                    boat_table b1 = json.fromJson(boat_line, type1);
                    int id = 0;
                    for (boat_table b2 : server.fm.boats) {
                        if (b2.getId() > id)
                            id = b2.getId();
                    }
                    id += 1;
                    b1.setId(id);
                    b1.setName("boat" + id);
                    server.fm.boats.add(b1);
                    server.fm.m.map_list.get(b1.getX_cur() + server.fm.m.width*b1.getY_cur()).setCur_boat("");
                    server.fm.mod.insertBoat(b1);
                    server.fm.boats = server.fm.mod.getBoats();
                    algorithm alg = new algorithm();
                    for (boat_table b3 : server.fm.boats) {
                        alg.findPath(server.fm, b3);
                    }
                    String b_list = json.toJson(server.fm.boats);
                    os.writeUTF(b_list);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
