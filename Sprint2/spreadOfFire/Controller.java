

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Controller extends JFrame{
  ModelClass myModel;
  View myView;
  Thread startThread;
  JButton startButton,stopButton,moveButton;
  JLabel probability,ratio,empty,size,delay;
  JSlider probabilityScale,ratioScale,emptyScale,sizeScale,delayScale;
  JTextField setProp = new JTextField(15);
  JTextField setSize =new JTextField(15);
  JTextField setHight =new JTextField(15);
  
  
  public Controller(){
    
    super("Spread of Fire");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(860, 430);
    setResizable(false);
    setLayout(new GridLayout(1, 2));
    
    
    myModel=new ModelClass();
    
    
    int boxWidth=(int)((400)/myModel.width);
    int boxHeight=(int)(400/myModel.height);
    myView=new View(boxWidth,boxHeight);
    
    
    add(myView);
    
    
    myModel.addObserver(myView);
    
    
    JPanel controller=new JPanel();
    controller.setLayout(new GridLayout(4,1));
    
    
    add(controller);
    
    
    {
      
      JPanel controller2=new JPanel();
      controller.add(controller2);
      {
        JLabel prop = new JLabel("PropCatch : ");
        controller2.add(prop);
        controller2.add(setProp);
        setProp.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            
            String text = setProp.getText();
            myModel.setProbability(Integer.parseInt(text));
            System.out.println(Integer.parseInt(text));
          }
        });
      }
      
      
      JPanel controller3=new JPanel();
      controller.add(controller3);
      
      {
        JLabel size = new JLabel("Set Size : ");
        controller3.add(size);
        controller3.add(setSize);
      }
      
      
      JPanel controller4=new JPanel();
      controller.add(controller4);
      
      {
        JLabel hight = new JLabel("Set Hight : ");
        controller4.add(hight);
        controller4.add(setHight);
      }
      
      
      JPanel controller1=new JPanel();
      controller.add(controller1);
      
      {
        
        startButton=new JButton("Auto");
        startButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            if(startThread==null||!startThread.isAlive()){
              startThread=new Thread() {  
                public void run() { 
                  myModel.applySpread();
                  
                  myModel.update();
                  
                }  
              };
              startThread.start();
            }
          }
        });
        controller1.add(startButton);
        
        
        stopButton=new JButton("Step");
        stopButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            if(startThread!=null&&startThread.isAlive()){
              startThread.stop();
            }
            
          }
        });
        controller1.add(stopButton);
        
        
        JButton resetButton=new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            if(startThread!=null&&startThread.isAlive()){
              startThread.stop();
            }
            
            myModel.fieldReset();
          }
        });
        controller1.add(resetButton);
      }
      
      
      
      
    }
    
    setVisible(true);
    
  }
  
}
