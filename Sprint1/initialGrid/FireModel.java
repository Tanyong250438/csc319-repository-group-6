public class FireModel
{
    public static int SIZE = 10;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int i=0;i<SIZE; i++)
        {
            for (int j=0; j<SIZE; j++)
            {
                myGrid[i][j] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
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
   
    
}

