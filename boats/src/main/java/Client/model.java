package Client;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class model {

   ArrayList<IObserver> arrO = new ArrayList<>();


        void update(){
            for (IObserver iObserver : arrO) {
                iObserver.update();
}

        }

        public void addObserver(IObserver o){
            arrO.add(o);
        }
        public void updateMap() {
            MainFrame.JFrame.repaint();
            update();
    }

    }


