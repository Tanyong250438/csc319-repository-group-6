package spreadoffire;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author KMUTT student 
 * 56130500206
 * 56130500221
 * 56130500254
 * @version 2.0
 */

public class View extends JPanel {
  Cell cell[][];
  int blockWidth,blockHeight;
  private int step;
  
  /*
  @override
   Implement paintcomponent
  */
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
  
  /*
   *Constructor - initial size of block(tree)
   *@param blockwidth
   *@param blockheight
  */
  public View(int blockWidth,int blockHeight) {
    this.blockWidth=blockWidth;
    this.blockHeight=blockHeight;
    cell=null;
  }
  
  /*
   To set size of block
   @param blockwidth
   @param blockheight
  */
  public void setSize(int blockWidth,int blockHeight) {
    this.blockWidth=blockWidth;
    this.blockHeight=blockHeight;
  }
  
  /*
   Update call repaint()
   @param cell[][]
  */
  public void update(Cell cell[][]){
    this.cell=cell;
    repaint();
  }
  /*
   updateStep
   @param step
  */
  public void updateStep(int step) {
    this.step=step;
  }
  
}
