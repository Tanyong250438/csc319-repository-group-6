import java.util.*;
import java.awt.Color;

public final class ModelClass {
  
  public int width,height,probability;
  private int green,red,yellow;
  private View observer;
  private Cell cell[][];
  private boolean check[][];
  
  public ModelClass() {
    this(30,30,50);
  }
  public ModelClass(int width,int height){
    this(width,height,50);
    fieldReset();
    check = new boolean[width][height];
  }
  
  public ModelClass(int width, int height, int probability) {
    
    this.width = width;
    this.height = height;
    this.probability = probability;
    observer=null;
    
    fieldReset();
    check = new boolean[width][height];
  }
  public void setProbability(int probability) {
    this.probability = probability;
   
  }
  public void setSize(int width,int height){
    this.width=width;
    this.height=height;
    fieldReset();
  }
   public void setWidth(int width){
    this.width=width;
    fieldReset();
  }
  public void setHeight(int height){
    this.height=height;
    fieldReset();
  }
  public void fieldReset(){
    
    cell=new Cell[width][height];
    check = new boolean[width][height];
    for(int i = 0; i < cell.length; i++){
      for(int j=0; j<cell[0].length; j++){
        if(i==0 || j==0 || i==cell.length-1 || j==cell[0].length-1 ){
          cell[i][j] = new Cell(Cell.YELLOW);
        }else{
          if((int)Math.random()*100 < 100){
            if((int)Math.random()*100 < 20){
               cell[i][j] = new Cell(Cell.GREEN);
            }
          cell[i][j] = new Cell(Cell.GREEN);
          }
          
        }
      }
      
      cell[width/2][height/2] = new Cell(Cell.RED);
    }
    
    
    update();
  }
  
 /* public void fieldRandomStartReset(){
    
    cell=new Cell[width][height];
    check = new boolean[width][height];
    for(int i = 0; i < cell.length; i++){
      for(int j=0; j<cell[0].length; j++){
        if(i==0 || j==0 || i==cell.length-1 || j==cell[0].length-1 ){
          cell[i][j] = new Cell(Cell.YELLOW);
        }else{
          cell[i][j] = new Cell(Cell.GREEN);
        }
      }
      
      cell[(int)(Math.random()*width-1)][(int)(Math.random()*height-1)] = new Cell(Cell.RED);
    }
    
    
    update();
  }*/
  
  public void print(){
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        if(cell[i][j].get()==Cell.GREEN)System.out.print(". ");
        else if(cell[i][j].get()==Cell.RED)System.out.print("O ");
        else if(cell[i][j].get()==Cell.YELLOW)System.out.print("  ");
      }
      System.out.println("");
    }
  }
  public Color getColor(int x,int y){
    try{
      return cell[x][y].getColor();
    }
    catch(Exception e){
      
      return Color.WHITE;
    }
  }
  private int get(int x,int y){
    try{
      return cell[x][y].get();
    }
    catch(Exception e){
      
      return Cell.YELLOW;
    }
  }
  
  public void spread(int x, int y){
    
    if(get(x,y-1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability){
        cell[x][y-1].set(Cell.RED);
        check[x][y-1] = true;
      }
    }
    if(get(x,y+1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability){
        cell[x][y+1].set(Cell.RED);
        check[x][y+1] = true;
      }      
    }
    if(get(x-1,y) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability){
        cell[x-1][y].set(Cell.RED);
        check[x-1][y] = true;
      }      
    }
    if(get(x+1,y) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability){
        cell[x+1][y].set(Cell.RED);
        check[x+1][y] = true;
      }      
    }
    
    cell[x][y].set(Cell.YELLOW);
  }
  public void checkBurn(){
    for(int i = 0; i < cell.length; i++){
      for(int j = 0; j<cell[0].length; j++){
        if(get(i,j) == Cell.RED && check[i][j] == false){
          spread(i,j);
        }
      }
    }
  }
  public void addObserver(View view){
    observer=view;
    update();
  }
  public void update(){
    if(observer!=null)observer.update(cell);    
  }
  public boolean checkFire() {
    for (int i = 1; i < width - 1; i++) {
      for (int j = 1; j < height - 1; j++) {
        if (get(i,j) == Cell.RED) {
          return false;
        }
      }
    }
    return true;
  }
  
  public void spread() {
    try {
      if (!checkFire()) {
        checkBurn();
      }
      resetCheck();
      update();
      Thread.sleep(100);
    } catch (InterruptedException e) {
    //  
    }
  }
  
  public void resetCheck() {
    for (int i = 1; i < check.length - 1; i++) {
      for (int j = 1; j < check[0].length - 1; j++) {
        check[i][j] = false;
      }
    }
  }
  
  public void applySpread() {
    while (!checkFire()) {
      spread();
    }
  }
}

