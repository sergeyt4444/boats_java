package Client;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import boat_table.boat_table;


public class MainFrame  extends JPanel implements IObserver {
    public static JFrame JFrame;
    public static final int SCALE=32;
    public static int WIDTH=5;
    public static  int HEIGHT=5;
    public static final int shift=SCALE/4;
    public static  boolean flag=false;
    public static int  startX=-1;
    public static int startY=-1;
    public static int  endX=-1;
    public static int endY=-1;
    public static boolean flagBotat=false;
    public static int number=0; //только для теста


    static mapM m=new mapM();
    static boat_table boat = new boat_table();
    static model cm = CModel.build();
    Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 20);


    public void MainFrame(){
        this.setDoubleBuffered(true);
    }


    public void paint(Graphics g){


        g.setColor(Color.blue);
        g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);

        for(int x=0;x<WIDTH*SCALE;x+=SCALE)
        {
            g.setColor(Color.white);
            g.drawLine(x,0,x,HEIGHT*SCALE);
        }

        for(int y=0;y<HEIGHT*SCALE;y+=SCALE)
        {
            g.setColor(Color.white);
            g.drawLine(0,y,WIDTH*SCALE,y);
        }

        g.setColor(Color.green);
        for(int i=0;i<WIDTH;i++)
            for(int j=0;j<HEIGHT;j++)
                if (m.myTwoDimentionalArray[i][j] == -1) {
                    g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                }

        g.setColor(Color.CYAN);
        for(int i=0;i<WIDTH;i++)
            for(int j=0;j<HEIGHT;j++)
                if (m.myTwoDimentionalArray[i][j] == 2)
                    g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE -1);
        if(flag==true) {                                                                                        //отображение маршрутов
            for (int i = 0; i < WIDTH; i++)
                for (int j = 0; j < HEIGHT; j++) {
                    if (m.myArray[i][j] >= 1 && m.myArray[i][j] <= 2)
                    {
                        g.setColor(Color.yellow);
                        g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                        g.setColor(Color.black);
                        g.setFont(font);
                        g.drawString(Integer.toString(m.myArray[i][j]), i * SCALE + shift, j * SCALE + shift * 3);

                    }
                    if (m.myArray[i][j] >2 &&m.myArray[i][j] <=3)
                    {
                        g.setColor(Color.orange);
                        g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                        g.setColor(Color.black);
                        g.setFont(font);
                        g.drawString(Integer.toString(m.myArray[i][j]), i * SCALE + shift, j * SCALE + shift * 3);
                    }
                    if (m.myArray[i][j] > 3)
                    {
                        g.setColor(Color.red);
                        g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                        g.setColor(Color.black);
                        g.setFont(font);
                        g.drawString(Integer.toString(m.myArray[i][j]), i * SCALE + shift, j * SCALE + shift * 3);
                    }
                }

        }
        g.setColor(Color.red);                                                                                  //добавление лодки
        for(int i=0;i<=WIDTH;i++)
            for(int j=0;j<=HEIGHT;j++)
            {
                if (startX == i)
                    if (startY == j)
                        g.fillRect(i * SCALE, j * SCALE , SCALE - 1, SCALE - 1);
                if (endX == i)
                    if (endY == j)
                        g.fillRect(i * SCALE, j * SCALE , SCALE - 1, SCALE - 1);


            }




    }


    public static void createGUI(){
        JFrame =new JFrame("Client");
        JFrame.setSize(WIDTH*SCALE+200,HEIGHT*SCALE+75);
        JFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JFrame.setResizable(false);
        JFrame.setLocationRelativeTo(null);
        JFrame.add(new MainFrame());
        JFrame.setVisible(true);



    }
    public static void  createElements() {
        JButton b = new JButton("отправить лодку");
        JCheckBox check = new JCheckBox("показать маршрут", false);
        JButton clear=new JButton("очистить");
        JButton update=new JButton("обновить");
        check.setSize(150, 30);

        JFrame.add(update);
        JFrame.add(b);
        JFrame.add(check);
        JFrame.add(clear);


        b.setBounds(WIDTH * SCALE + 25, 0, 150, 50);
        update.setBounds(WIDTH * SCALE + 25,b.getY()+60, 150, 50);
        clear.setBounds(WIDTH*SCALE+25,update.getY()+60,150,50);
        check.setLocation(0, HEIGHT * SCALE );

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagBotat=false;
                startX=-1;
                startY=-1;
                endX=-1;
                endY=-1;
                JFrame.repaint();
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startX!=endX||startY!=endY){
                m.myTwoDimentionalArray[startX][startY]=2;
                boat.setX_cur(startX);
                boat.setY_cur(startY);
                boat.setX_fin(endX);
                boat.setY_fin(endY);
                startX=-1;
                startY=-1;
                endX=-1;
                endY=-1;
                flagBotat=false;
                JFrame.repaint();
                }
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                flag=check.isSelected();
                JFrame.repaint();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number++;
               // m.copy(number);
                cm.updateMap();
            }
        });
    }
    public static void mouseListener()
    {
        JFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX()/SCALE < WIDTH)
                    if (e.getY()/SCALE <= HEIGHT)
                        if(m.myTwoDimentionalArray[e.getX()/SCALE][e.getY()/SCALE-1]==0){
                            if(flagBotat==false)
                            {
                                System.out.println("cor x" + e.getX());
                                System.out.println("cor y" + e.getY());
                                startX = e.getX()/SCALE;
                                startY = e.getY()/SCALE-1;
                                System.out.println("X  " + startX);
                                System.out.println("Y  " + startY);
                                JFrame.repaint();


                            }
                            else
                            {
                                System.out.println("cor x" + e.getX());
                                System.out.println("cor y" + e.getY());
                                endX = e.getX()/SCALE;
                                endY = e.getY()/SCALE-1;
                                System.out.println("X  " + endX);
                                System.out.println("Y  " + endY);
                                JFrame.repaint();
                            }
                            flagBotat=true;
                        }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    @Override
    public void update() {
        this.repaint();

    }

}
