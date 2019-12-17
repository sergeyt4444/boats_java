package Client;
//import MainFrame;

import boat_table.boat_table;
import full_map.full_map;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import map.map;
import map_part.map_part;

import java.lang.reflect.Type;


public class Client  extends MainFrame {
    // адрес сервера
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;

     static full_map mapS =new full_map();


    public static int[][] myTwoDimentionalArray;
    public static int[][] myArray;


    public int max=0;

    /*public static int getHEIGHT() {
        return mapS.m.height;

    }
    public static int getWIDTH()
    {
        return mapS.m.width;
    }*/

    public void Client(){}

    public static void main(String[] args)
    { /*
        MainFrame.WIDTH=getWIDTH();
        MainFrame.HEIGHT=getHEIGHT();*/
        model mod = new model();
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        String adress = "127.0.0.1";
        InetAddress ip = null;
        Socket socket = null;
        try {
            ip = InetAddress.getByName(adress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket = new Socket(ip, 4443);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream sin = null;
        OutputStream sout = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
            if (sout != null) {
                MainFrame.dout = out;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = null;
        try {
            line = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type tmap = new TypeToken<map>(){}.getType();
        map mloc = json.fromJson(line, tmap);
        MainFrame.WIDTH = mloc.width;
        MainFrame.HEIGHT = mloc.height;
        createGUI();
        createElements();
        mouseListener();
        try {
            line = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type tblist = new TypeToken<List<boat_table>>(){}.getType();
        List<boat_table> boatsloc = json.fromJson(line, tblist);

        mapS.m = mloc;
        mapS.boats = boatsloc;
        convertMap();
        convertRoute();
        MainFrame.JFrame.repaint();

        while (true) {
            try {
                line = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<boat_table> inboats = json.fromJson(line, tblist);

            mapS.boats = inboats;
            convertMap();
            convertRoute();
            MainFrame.JFrame.repaint();
        }


    }
    public static void convertMap(){
        for (int i = 0; i< MainFrame.HEIGHT; i++)
            for (int j=0;j<MainFrame.WIDTH;j++) {
                myTwoDimentionalArray[i][j] = mapS.mapToMatrixT()[i][j];
            }
        for (boat_table b : mapS.boats) {
            myTwoDimentionalArray[b.getX_cur()][b.getY_cur()] =2;
        }

    }

    public static void convertRoute()
    {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++)
                myArray[i][j]=0;
        for (boat_table b : mapS.boats)
            if (b.boat_path!= null && b.boat_path.boat_path != null) {
                for (map_part mp : b.boat_path.boat_path) {
                    myArray[mp.getX()][mp.getY()] += 1;
                }
            }
    }



}
