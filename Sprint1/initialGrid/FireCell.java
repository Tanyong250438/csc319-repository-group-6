public class FireCell
{
    public static final int DIRT = 0, GREEN = 1, BURNING = 2;
    int state; 

    public FireCell()
    {
        state = DIRT;
    }
    
    public int getState()
    {
        return state;
    }
    
    public void setState(int n)
    {
        state = n;
    }
}

