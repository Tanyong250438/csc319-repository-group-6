/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nifty;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
/**
 *
 * @author admin
 */
public class Frame extends JFrame {
    Agent board[][];
    JButton startButton;
    JPanel divide;
    Thread startThread,checkReset;
    Board b;
    Frame(){
        setSize(300,300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and Add the startButton
                startButton=new JButton("Start");
                startButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //If there is no Thread or Thread is dead, create new Thread and start
                        if(startThread==null||!startThread.isAlive()){
                            startThread=new Thread() {  
                                public void run() { 
                                    b.start();
                                }  
                            };
                            startThread.start();
                        }
                        remove(startButton);
                    }

                });
                add(startButton);
        setVisible(true);
        checkReset=new Thread(){
            public void run(){
                while(true){
                    if(b!=null&&!b.isMoving){
                        int dialogButton=JOptionPane.showConfirmDialog (null, "Reset ?");

                        if(dialogButton == JOptionPane.YES_OPTION){ //The ISSUE is here
                            try{
                                b.start();
                            } catch(Exception e){}
                        }
                        else{
                            b.isMoving=true;
                        }
                    }
                    System.out.print("");
                }
            }
        };
        checkReset.start();
    }
    public void addBoard(Board b){
        this.b=b;
    }
    
    public void remap() {
        //remove(divide);
        divide = new JPanel(new GridLayout (30,30));
        
        for(int i = 0 ; i<30 ; i++){
            for(int j = 0 ; j<30 ; j++){
                JLabel color = new JLabel ();
                color.setOpaque(true);
                /*if((i+j)%2==0){
                    color.setBackground(Color.black);
                }else{
                    color.setBackground(Color.pink);
                }*/
                color.setBackground(board[i][j].color);
                color.setBorder(LineBorder.createBlackLineBorder());
                divide.add(color);
            }
        }
        add(divide);
        setVisible(true);
    }
    public void update(Agent board[][]){
        this.board=board;
        
        remap();
    }
}
