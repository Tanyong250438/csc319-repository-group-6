
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class View extends JPanel {
  Cell cell[][];
  int blockWidth,blockHeight;
  
  
  public void paintComponent(Graphics g){
    
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 400, 400);
    
    
    int border=(400-(blockWidth*cell.length))/2;
    
    
    if(cell==null)return;
    
    for(int i=0;i<cell.length;i++){
      for(int j=0;j<cell[0].length;j++){
        
        g.setColor(cell[i][j].getColor());
        g.fillRect(border+j*blockWidth,border+i*blockHeight,blockWidth,blockHeight);
        
        g.setColor(Color.BLACK);
        g.drawRect(border+j*blockWidth,border+i*blockHeight,blockWidth,blockHeight);
      }
    }
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
}
