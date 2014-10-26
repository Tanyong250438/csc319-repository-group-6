import java.awt.*;
import javax.swing.*;


class FireView extends JPanel
{
    private FireCell[][] myGrid;

    
    public void updateView( FireCell[][] mg )
    {
        myGrid = mg;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int Width = getWidth() / (FireModel.SIZE+1);
        int Height = getHeight() / (FireModel.SIZE+1);
        int boxSize = Math.min(Height,Width);

        for ( int i = 0; i < FireModel.SIZE; i++ )
        {
            for (int j = 0; j < FireModel.SIZE; j++ )
            {
                if (myGrid[i][j] != null)
                {
                    int ulX = (i+1) * boxSize;
                    int ulY = (j+1) * boxSize;
                    if ( myGrid[i][j].getState() == FireCell.DIRT )            
                        g.setColor( Color.yellow);
                    else if ( myGrid[i][j].getState() == FireCell.GREEN )      
                        g.setColor( Color.green );
                    else
                        g.setColor( Color.red );                   

                    int topLeftX = ulX+2, topLeftY = ulY+2;
                    int sizeX = boxSize-2, sizeY = boxSize-2;
                    g.fillRect( topLeftX, topLeftY, sizeX, sizeY);
                }
            }
        }
    }
}
