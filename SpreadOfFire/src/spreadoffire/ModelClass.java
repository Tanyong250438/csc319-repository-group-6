package spreadoffire;

import java.awt.Color;

/**
 *
 * @author KMUTT student 
 * 56130500206
 * 56130500221
 * 56130500254
 * @version 2.0
 */

public final class ModelClass {
  
  public int width,height,probability,probBurn,probTree,probLightning;
  private int green,red,yellow;
  private View observer;
  private Cell cell[][];
  private boolean check[][];
  public int step;
  
  
  /*
   Constructor-initialize width height prob
  */
  public ModelClass() {
    this(30,30,50);
  }
  /*
   Constructor-Field
   @param width
   @param height
  */
  public ModelClass(int width,int height){
    this(width,height,50);
    fieldReset();
    check = new boolean[width][height];
  }
  /*
   Constructor-Field
   @param width
   @param height
   @param probability
  */
  public ModelClass(int width, int height, int probability) {
    
    this.width = width;
    this.height = height;
    this.probability = probability;
    observer=null;
    
    fieldReset();
    check = new boolean[width][height];
  }
  /*
   SetProbability method to set value of prob
   @param int probability
  */
  public void setProbability(int probability) {
    this.probability = probability;
    
  }
  /*
   Set the size of field and reset size of field
   @param width
   @param height
  */
  public void setSize(int width,int height){
    this.width=width;
    this.height=height;
    fieldReset();
  }
  /*
   Set probBurn
   @param probBurn
  */
  public void setProbBurn(int probBurn) {
    this.probBurn = probBurn;
    
  }
  /*
   Set probTree
   @param probTree
  */
  public void setProbTree(int probTree) {
    this.probTree = probTree;
    
  }
  /*
   Set probLightning
   @param probLightning
  */
   public void setProbLightning(int probLightning) {
    this.probLightning = probLightning;
    
  }
 
  
  /*
    To reset field state and update
   */
  public void fieldReset(){
    
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
      
      cell[width/2][height/2] = new Cell(Cell.RED);
    }
    
    
    update();
  }
  
  /*
   To reset field state and update from probBurn
  */
  public void fieldProbBurnReset(){
    
    
    cell=new Cell[width][height];
    check = new boolean[width][height];
    for(int i = 0; i < cell.length; i++){
      for(int j=0; j<cell[0].length; j++){
        if(i==0 || j==0 || i==cell.length-1 || j==cell[0].length-1 ){
          cell[i][j] = new Cell(Cell.YELLOW);
        }else{
          int rd = (int)(Math.random()*100);
          int rdd = (int)(Math.random()*100);
          cell[i][j] = new Cell(Cell.GREEN);
          if( probBurn != 0 && rd < probBurn){
            cell[i][j] = new Cell(Cell.RED);
          }if(probTree!= 0 && rdd > probTree){
            cell[i][j] = new Cell(Cell.YELLOW);
          }
          
        }
      }
      
      
    }
    
    
    update();
    
  }
  
  /*
   get Color method to getColor from cell x y coordinate
   @param x 
   @param Y 
  */
  public Color getColor(int x,int y){
    try{
      return cell[x][y].getColor();
    }
    catch(Exception e){
      
      return Color.WHITE;
    }
  }
  /*
   Get color from position
   @param x
   @param y
   @return int
  */
  
  private int get(int x,int y){
    try{
      return cell[x][y].get();
    }
    catch(Exception e){
      
      return Cell.YELLOW;
    }
  }
  
  /*
   Overload
   Spread the fire to neighbour
   @param x
   @param y
  */
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
  /*
   Check burn method to find a red cell and spread
  */
  public void checkBurn(){
    step++;
    for(int i = 0; i < cell.length; i++){
      for(int j = 0; j<cell[0].length; j++){
        if(get(i,j) == Cell.RED && check[i][j] == false){
          if(Controller.northWind == true){
            northWind(i,j);
          }
          else if(Controller.southWind == true){
            southWind(i,j);
          }
          else if(Controller.eastWind == true){
            eastWind(i,j);
          }
          else if(Controller.westWind == true){
            westWind(i,j);
          }else{
          spread(i,j);
          }
        }if(get(i,j) == Cell.GREEN && check[i][j] == false){
         
          int rnd2 = (int)(Math.random()*3000);
          int rnd1 = (int)(Math.random()*100);
          if(rnd1<probLightning){
            
            if( rnd2 < probLightning*probability/100 ){
              cell[i][j].set(Cell.RED);
            }
            else cell[i][j].set(Cell.GREEN);
          }
        }
      }
    }
  }
  /*
   add observer to view 
   @param view
  */
  public void addObserver(View view){
    observer=view;
    update();
  }
  /*
   implement update
  */
  public void update(){
    if(observer!=null){
      observer.update(cell);
      if(!checkFire()){
        observer.updateStep(step);
      }
    } 
  }
  /*
   checkFire to check is it has red cell or not
  */
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
  /*
   spread and implement thread sleep to delay
  */
  public void spread() {
    try {
      if (!checkFire()) {
        checkBurn();
      }
      resetCheck();
      update();
      Thread.sleep(150);
    } catch (InterruptedException e) {
      //
    }
  }
  /*
   to reset check
  */
  public void resetCheck() {
    for (int i = 1; i < check.length - 1; i++) {
      for (int j = 1; j < check[0].length - 1; j++) {
        check[i][j] = false;
      }
    }
  }
  /*
   to apply spread
  */
  public void applySpread() {
    while (!checkFire()) {
        spread();
    }
  }
  /*
   change the behavior of spread(wind factor)
   @param x
   @param y
  */
   public void northWind(int x, int y){
    
    if(get(x,y-1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability)){
        cell[x][y-1].set(Cell.RED);
        check[x][y-1] = true;
      }
    }
    if(get(x,y+1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability)){
        cell[x][y+1].set(Cell.RED);
        check[x][y+1] = true;
      }      
    }
    if(get(x-1,y) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability-50){
        cell[x-1][y].set(Cell.RED);
        check[x-1][y] = true;
      }      
    }
    if(get(x+1,y) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability+20){
        cell[x+1][y].set(Cell.RED);
        check[x+1][y] = true;
      }      
    }
    
    cell[x][y].set(Cell.YELLOW);
  }
  
  public void southWind(int x, int y){
    
    if(get(x,y-1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability)){
        cell[x][y-1].set(Cell.RED);
        check[x][y-1] = true;
      }
    }
    if(get(x,y+1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability)){
        cell[x][y+1].set(Cell.RED);
        check[x][y+1] = true;
      }      
    }
    if(get(x-1,y) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability+20){
        cell[x-1][y].set(Cell.RED);
        check[x-1][y] = true;
      }      
    }
    if(get(x+1,y) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<probability-50){
        cell[x+1][y].set(Cell.RED);
        check[x+1][y] = true;
      }      
    }
    
    cell[x][y].set(Cell.YELLOW);
  }
  
  public void eastWind(int x, int y){
    
    if(get(x,y-1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability+20)){
        cell[x][y-1].set(Cell.RED);
        check[x][y-1] = true;
      }
    }
    if(get(x,y+1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability-50)){
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
  
  public void westWind(int x, int y){
    
    if(get(x,y-1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability-50)){
        cell[x][y-1].set(Cell.RED);
        check[x][y-1] = true;
      }
    }
    if(get(x,y+1) == Cell.GREEN){
      int rnd = (int)(Math.random()*100);
      if(rnd<(probability+20)){
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
}