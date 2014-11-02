import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Fire extends JFrame
{
  private FireView view;
  private FireModel model;
  JButton stepButton,autoButton,resetButton;
  JLabel size,prop;
  JTextField text1 =new JTextField(20);
  JTextField text4 =new JTextField(20);
  
  Fire()
  {
    super("Spread of Fire");
     setLayout(new GridLayout(1, 2));
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view = new FireView();
    view.setBackground(Color.white);
    Container c = getContentPane();
    c.add(view, BorderLayout.CENTER);
    
    
    model = new FireModel(view);
     JPanel controller=new JPanel();
        controller.setLayout(new GridLayout(6,1));
        add(controller);
        {
            JPanel controller1=new JPanel();
            controller.add(controller1);
            {   
                stepButton=new JButton("Step");
                stepButton.addActionListener(new ActionListener(){});
                controller1.add(stepButton);
                
                autoButton=new JButton("Auto");
                autoButton.addActionListener(new ActionListener(){});
                controller1.add(autoButton);
                
                JButton resetButton=new JButton("Reset");
                resetButton.addActionListener(new ActionListener(){
                controller1.add(resetButton);
            }
    public void actionPerformed(ActionEvent e) {
   
    
     
  }
  
  public static void main(String[] args)
  {
    Fire smokey = new Fire();
    smokey.addWindowListener(new WindowAdapter()
                               {
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    }
    );
    smokey.setSize(570, 640);
    smokey.show();
  }
}