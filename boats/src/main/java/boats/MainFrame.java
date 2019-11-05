package boats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.EventHandler;
import java.util.EventListener;


public class MainFrame extends JPanel  {
    public static JFrame JFrame;
    public static final int SCALE=32;
    public static final int WIDTH=10;
    public static final int HEIGHT=10;
    public static final int shift=SCALE/4;
    public static  boolean flag=false;




    mapM m=new mapM();
    Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 20);


    public void MainFrame(){
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
                if (m.myTwoDimentionalArray[i][j] == 1)
                    g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                g.setColor(Color.CYAN);
        for(int i=0;i<WIDTH;i++)
            for(int j=0;j<HEIGHT;j++)
            if (m.myTwoDimentionalArray[i][j] == 2)
            g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE -1);
                if(flag==true) {
                    for (int k = 0; k <= m.max; k++) {
                        for (int i = 0; i < WIDTH; i++)
                            for (int j = 0; j < HEIGHT; j++) {
                                if (m.myArray[i][j] == 1)
                                    if (k == 1) {
                                        g.setColor(Color.yellow);
                                        g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                                        g.setColor(Color.black);
                                        g.setFont(font);
                                        g.drawString(Integer.toString(k), i * SCALE + shift, j * SCALE + shift * 3);

                                    }
                                if (m.myArray[i][j] == 2)
                                    if (k == 2) {
                                        g.setColor(Color.orange);
                                        g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                                        g.setColor(Color.black);
                                        g.setFont(font);
                                        g.drawString(Integer.toString(k), i * SCALE + shift, j * SCALE + shift * 3);
                                    }
                                if (m.myArray[i][j] == 3)
                                    if (k == 3) {
                                        g.setColor(Color.red);
                                        g.fillRect(i * SCALE, j * SCALE, SCALE - 1, SCALE - 1);
                                        g.setColor(Color.black);
                                        g.setFont(font);
                                        g.drawString(Integer.toString(k), i * SCALE + shift, j * SCALE + shift * 3);
                                    }
                            }
                    }
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


    public static void main(String[] args) {
        createGUI();
        JButton b = new JButton("отправить лодку");
        b.setBounds(WIDTH * SCALE + 25, 0, 150, 50);
        JCheckBox check = new JCheckBox("показать маршрут", false);
        check.setSize(150, 30);
        check.setLocation(WIDTH * SCALE + 20, HEIGHT * SCALE - 20);
        JFrame.add(b);
        JFrame.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                flag=check.isSelected();
                JFrame.repaint();
            }
        });
    }



}
