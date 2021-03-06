/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadoffire;

import java.awt.Color;
/**
 *
 * @author admin
 */

public class Cell {
  public static final int GREEN=2,RED=1,YELLOW=0;
  private int color;
  public Cell(){
    this.color=Cell.YELLOW;
  }
  
  public Cell(int color){
    this.color=color;
  }
  public int get(){
    return color;
  }
  public void set(int color){
    this.color=color;
  }
  public boolean isEmpty(){
    return color==YELLOW;
  }
  public Color getColor(){
    if(color==GREEN)return Color.GREEN;
    if(color==RED)return Color.RED;
    return Color.YELLOW;
  }
}

