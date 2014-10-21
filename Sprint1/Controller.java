import javax.swing.*;

public class Controller extends JFrame{
    JButton stepButton,autoButton,resetButton
    JLabel size,prop;
    JTextField text1 =new JTextField(20);
    JTextField text4 =new JTextField(20);
    public Controller(){
        //set the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 430);
        setResizable(false);
        //to arrange the frame
        setLayout(new GridLayout(1, 2));
        
        add(forest);
        
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
                
                                             
}
                
                
                
                
                
                
                
                
                
                
                
                
