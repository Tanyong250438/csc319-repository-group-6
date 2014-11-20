/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadoffire;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author admin
 */

public class View extends JPanel {
  Cell cell[][];
  int blockWidth,blockHeight;
  private int step;
  
  
  public void paintComponent(Graphics g){
    
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 400, 400);
    
    
    int border=(400-(blockWidth*cell.length))/2;
    
    
    if(cell==null)return;
    
    for(int i=0;i<cell.length;i++){
      for(int j=0;j<cell[0].length;j++){
        
        g.setColor(cell[i][j].getColor());
        g.fillRect(border+j*blockWidth,border+i*blockHeight,blockWidth,blockHeight);
        
        g.setColor(Color.WHITE);
        g.drawRect(border+j*blockWidth,border+i*blockHeight,blockWidth,blockHeight);
      }
    }
    g.fillRect(0, 400, 400, 25);
    g.setColor(Color.BLACK);
    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
    g.drawString("Step: "+step, 170, 420);
  }
  
  
  public View(int blockWidth,int blockHeight) {
    this.blockWidth=blockWidth;
    this.blockHeight=blockHeight;
    cell=null;
  }
  
  
  public void setSize(int blockWidth,int blockHeight) {
    this.blockWidth=blockWidth;
    this.blockHeight=blockHeight;
  }
  
  
  public void update(Cell cell[][]){
    this.cell=cell;
    repaint();
  }
  public void updateStep(int step) {
    this.step=step;
  }
  
}
