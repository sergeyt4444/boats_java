package Client;
//import MainFrame;

import boat_table.boat_table;
import full_map.full_map;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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
        createGUI();
        createElements();
        mouseListener();





    }


}
