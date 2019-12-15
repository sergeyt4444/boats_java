package Client;
import boat_table.boat_table;
import full_map.full_map;
import map_part.map_part;


public  class mapM extends Client{

    public int[][] myTwoDimentionalArray;
    public int[][] myArray;


    public int max=0;

    public void convertMap(){
        for (int i = 0; i< MainFrame.HEIGHT; i++)
            for (int j=0;j<MainFrame.WIDTH;j++) {
                myTwoDimentionalArray[i][j] = mapS.mapToMatrix()[i][j];
            }
        for (boat_table b : mapS.boats) {
            myTwoDimentionalArray[b.getX_cur()][b.getY_cur()] =2;
        }

    }
    public void convertRoute()
    {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++)
                myArray[i][j]=0;
        for (boat_table b : mapS.boats)
            for (map_part mp : b.boat_path.boat_path)
            {
                myArray[mp.getX()][mp.getY()] += 1;
            }
    }

    public mapM() {

        myTwoDimentionalArray= new int[][]{
                {-1, -1, 0, -1, 0,},
                {2, 0, 0, 0, 0,},
                {0, -1, 2, 0, 0,},
                {0, 0, -1, 0, 0,},
                {0, 2, 0, 0, 0,},
        };





        myArray= new int[][]{
                {0,0,0,0,0,},
                {0,0,0,0,0,},
                {0,0,0,0,0,},
                {0,0,0,0,0,},
                {0,0,0,0,0,},
        };



    }


}
