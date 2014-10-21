public class FireModel
{
    public static int SIZE = 10;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int i=0; j<SIZE; i++)
        {
            for (int i=0; j<SIZE; j++)
            {
                myGrid[i][j] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    
   
    
}

