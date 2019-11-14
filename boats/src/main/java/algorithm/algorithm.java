package algorithm;

import boat_table.boat_table;
import boats.Pair;
import boats.path;
import map_part.*;
import full_map.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class algorithm implements abstract_algorithm{

    private int[][] getWavesMatrix(full_map fm, boat_table boat) {
        int[][] matrix = fm.mapToMatrix();
        for (int i = 0; i < fm.m.height; i++) {
            for (int j = 0; j < fm.m.width; j++) {
                if (matrix[i][j] == 0)
                    matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        int fin_x = boat.getX_fin();
        int fin_y = boat.getY_fin();
        matrix[fin_y][fin_x] = 0;
        Pair p = new Pair (fin_x, fin_y);
        Queue<Pair> queue = new LinkedList<>();
        queue.add(p);
        while (!(queue.isEmpty())) {
            p = queue.remove();
            List<Pair> pairs = fm.getNearPairs(p);
            for (Pair pair : pairs) {
                if (matrix[pair.y][pair.x] > matrix[p.y][p.x] + 1) {
                    matrix[pair.y][pair.x] = matrix[p.y][p.x] + 1;
                    queue.add(pair);
                }
            }
        }
        return matrix;
    }

    @Override
    public path getPath(full_map fm, boat_table boat){
        path pth = new path();
        pth.boat_name = boat.getName();
        int[][] matrix = getWavesMatrix(fm, boat);
        int fin_x = boat.getX_fin();
        int fin_y = boat.getY_fin();
        map_part tmp_map_part = fm.m.map_list.get(boat.getX_cur() + boat.getY_cur() * fm.m.width);
        while (tmp_map_part.getX()!= fin_x || tmp_map_part.getY() != fin_y) {
            Pair path_pair = new Pair(tmp_map_part.getX(), tmp_map_part.getY());
            List<Pair> near = fm.getNearPairs(path_pair);
            int dist = matrix[path_pair.y][path_pair.x];
            for (Pair pair : near) {
                if (matrix[pair.y][pair.x] < matrix[path_pair.y][path_pair.x] && matrix[pair.y][pair.x] > -1) {
                    path_pair.x = pair.x;
                    path_pair.y = pair.y;
                }
            }
            tmp_map_part = fm.m.map_list.get(path_pair.x + fm.m.width * path_pair.y);
            pth.boat_path.add(tmp_map_part);
        }
        return pth;
    }

    @Override
    public void moveBoat(full_map fm, path path1) {
        if (path1.boat_name != null) {
            boat_table boat = null;
            for (boat_table b : fm.boats) {
                if (b.getName().equals(path1.boat_name))
                    boat = b;
            }
            if (!path1.boat_path.isEmpty()) {
                if (path1.boat_path.peek().getCur_boat() == "")
                    fm.moveBoatby1(boat, path1.boat_path.remove());
                else {
                    Pair p = new Pair(boat.getX_cur(), boat.getY_cur());
                    List<Pair> near = fm.getNearPairs(p);
                    int [][] matrix = getWavesMatrix(fm, boat);
                    int cur = matrix[boat.getY_cur()][boat.getX_cur()] + 2;
                    for (Pair pair : near) {
                        if (matrix[pair.y][pair.x] < cur && matrix[pair.y][pair.x] > -1 &&
                                fm.m.map_list.get(pair.x + pair.y * fm.m.width).getCur_boat() == "") {
                            cur = matrix[pair.y][pair.x];
                            p = pair;
                        }
                    }
                    fm.moveBoatby1(boat, fm.m.map_list.get(p.x + p.y * fm.m.width));
                    path1.boat_path.clear();
                }
            }
            else {
                if (!(boat.getX_cur() == boat.getX_fin() && boat.getY_cur() == boat.getY_fin()))
                {
                    path1 = getPath(fm, boat);
                    moveBoat(fm, path1);
                }
            }
            if (boat.getX_cur() == boat.getX_fin() && boat.getY_cur() == boat.getY_fin())
            {
                fm.mod.deleteBoat(boat);
            }
        }
    }


}
