/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nifty;

import java.awt.Color;
import java.awt.Color.*;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Board {

    Agent[][] board = new Agent[30][30];
    private double similar = 70;
    private int ratio = 100000;
    private int empty = 10;
    private int size = 30;
    private int delay = 100;
    private boolean hasSatisfy = false;
    public boolean isMoving=true;
    public Board(Frame observer) {
        this.observer = observer;
        restart();
        check();
    }

    public void restart() {
        int emptybox = 90;
        int redbox = 405;
        int bluebox = 405;
        ArrayList<Agent> temp = new ArrayList();
        for (int i = 0; i < emptybox; i++) {
            temp.add(new Agent(Color.white));
        }
        for (int i = 0; i < redbox; i++) {
            temp.add(new Agent(Color.red));
        }
        for (int i = 0; i < bluebox; i++) {
            temp.add(new Agent(Color.blue));
        }
        Collections.shuffle(temp);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                board[i][j] = temp.remove(0);
            }
        }
    }

    public void print() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (board[i][j].color == Color.blue) {
                    if (board[i][j].satisfy == false) {
                        System.out.print("X*");
                    } else {
                        System.out.print("X ");
                        //hasSatisfy = false;
                    }
                } else if (board[i][j].color == Color.red) {
                    if (board[i][j].satisfy == false) {
                        System.out.print("O*");
                    } else {
                        System.out.print("O ");
                        //hasSatisfy = false;
                    }
                } else if (board[i][j].color == Color.white) {
                    System.out.print("  ");
                }

            }
            System.out.println(""); //loop ใน29ครั้งละprintln
        }
    }

    public void check() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                double aroundred = 0;
                double aroundblue = 0;
                for (int x = max(i - 1, 0); x < min(30, i + 1); x++) {
                    for (int y = max(j - 1, 0); y < min(30, j + 1); y++) {
                        if (board[x][y].color == Color.red) {
                            aroundred += 1;
                        }
                        if (board[x][y].color == Color.blue) {
                            aroundblue += 1;
                        }

                    }
                }
                if (board[i][j].color == Color.red) {
                    if (aroundred / (aroundred + aroundblue) < similar / 100) {
                        board[i][j].satisfy = false;

                        hasSatisfy = true;
                    }

                }
                if (board[i][j].color == Color.blue) {
                    if (aroundblue / (aroundred + aroundblue) < similar / 100) {
                        board[i][j].satisfy = false;
                        hasSatisfy = true;
                    }

                }

            }
        }
    }

    public void move() {
        try {
            ArrayList<int[]> keepwhite = new ArrayList();
            ArrayList<int[]> keepunsatisfy = new ArrayList();
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (board[i][j].color == Color.white) {
                        keepwhite.add(new int[]{i, j});
                        
                    } else if (board[i][j].satisfy == false) {
                        keepunsatisfy.add(new int[]{i, j});
                    }
                }
                
            }
            Collections.shuffle(keepunsatisfy);
            while (!keepunsatisfy.isEmpty()) {
                int[] temp = keepunsatisfy.remove((int)(Math.random()*keepunsatisfy.size()));
                //System.out.println(board[temp[0]][temp[1]].color);
                int[] temp2 = keepwhite.remove((int)(Math.random()*keepwhite.size()));
                Color t = board[temp[0]][temp[1]].color;
                board[temp[0]][temp[1]].color = Color.white;
                board[temp2[0]][temp2[1]].color = t;
                board[temp2[0]][temp2[1]].satisfy = true;
                keepwhite.add(temp);
                Collections.shuffle(keepwhite);
                hasSatisfy = false;
                //System.out.println(keepwhite);
                
            }
            
            update();
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Frame observer;

    public void update() {
        if (observer != null) {
            observer.update(board);
        }
        //System.out.println("xx");
    }
    
    public void start(){
        isMoving=true;
        restart();
        check();
        while (hasSatisfy) {
            move();
            check();
           // System.out.println("\n\n");

            //print();
            ratio--;
            if (ratio == 0) {
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
        isMoving=false;
    }

}
