

import java.awt.Color;
/**
 *
 * @author KMUTT student 
 * 56130500206
 * 56130500221
 * 56130500254
 * @version 2.0
 */

public class Cell {
  public static final int GREEN=2,RED=1,YELLOW=0;
  private int color;
  /*
   * Constructor - initialize cell state
   */
  public Cell(){
    this.color=Cell.YELLOW;
  }
  /*
   * Constructor - initialize cell state
   * @param color
   */
  public Cell(int color){
    this.color=color;
  }
  /*
   get Color(cell)
   @return int color
  */
  public int get(){
    return color;
  }
  /*
   set Color(cell)
   @param int color
  */
  public void set(int color){
    this.color=color;
  }
  /*
   check Cell is it dirt?
   @return boolean
  */
  public boolean isEmpty(){
    return color==YELLOW;
  }
   /*
   get Color from cell
   @return Color
  */
  public Color getColor(){
    if(color==GREEN)return Color.GREEN;
    if(color==RED)return Color.RED;
    return Color.YELLOW;
  }
}

