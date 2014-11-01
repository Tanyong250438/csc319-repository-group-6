import java.util.*;
import java.awt.Color;

public final class ModelClass {
   
 public int width,height,probability;
    private int green,red,yellow;
    private View observer;
    private Cell cell[][];
    
    public ModelClass() {
        this(30,30,50);
    }
    public ModelClass(int width,int height){
        this(width,height,50);
    }
    
    public ModelClass(int width, int height, int similarity) {
         //Set the properties of field
        this.width = width;
        this.height = height;
        this.probability = probability;
        observer=null;
        //Reset the field
        fieldReset();
    }
    public void setProbability(int probability) {
        this.probability = probability;
    }
    public void setSize(int width,int height){
        this.width=width;
        this.height=height;
        //Reset the field after set
        fieldReset();
    }
    public void fieldReset(){
        //Create new field with current size
        cell=new Cell[width][height];
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
        
        //Update the field
        update();
    }
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
            //If x,y out of bound, treat as empty
            return Color.WHITE;
        }
    }
    private int get(int x,int y){
        try{
            return cell[x][y].get();
        }
        catch(Exception e){
            //If x,y out of bound, treat as empty
            return Cell.YELLOW;
        }
    }
    public void spread(int x, int y){
        
                
        if(get(x,y-1) == Cell.GREEN){
            int rnd = (int)(Math.random()*100);
            if(rnd<probability){
                cell[x][y-1].set(Cell.RED);
        
            }
        }
        if(get(x,y+1) == Cell.GREEN){
            int rnd = (int)(Math.random()*100);
            if(rnd<probability){
                cell[x][y+1].set(Cell.RED);
               
            }      
        }
        if(get(x-1,y) == Cell.GREEN){
            int rnd = (int)(Math.random()*100);
            if(rnd<probability){
                cell[x-1][y].set(Cell.RED);
                
            }      
        }
        if(get(x+1,y) == Cell.GREEN){
            int rnd = (int)(Math.random()*100);
            if(rnd<probability){
                cell[x+1][y].set(Cell.RED);
                
            }      
        }
        
        cell[x][y].set(Cell.YELLOW);
    }
    public void checkBurn(){
        step++;
        update();
        try {
            Thread.sleep(100);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        for(int i = 0; i < cell.length-1; i++){
            for(int j=0; j<cell[0].length-1; j++){
                if(get(i,j) == Cell.RED && cellCheck[i][j] == false){
                    cell[i][j].set(Cell.YELLOW);
                    spread(i,j);    spread(i,j);  
                    spread(i,j);    spread(,i,j);     
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
}