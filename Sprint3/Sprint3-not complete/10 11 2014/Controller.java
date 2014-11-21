

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Controller extends JFrame{
  ModelClass myModel;
  View myView;
  Thread startThread;
  JButton startButton,stepButton,moveButton;
  JLabel probability,ratio,empty,size,delay;
  JSlider probabilityScale,ratioScale,emptyScale,sizeScale,delayScale;
  JTextField setProp = new JTextField(15);
  JTextField setWidth =new JTextField(15);
  JTextField setHeight =new JTextField(15);
  JTextField setProbBurn = new JTextField(15);
  JTextField setProbTree = new JTextField(15);
  public Controller(){
    
    super("Spread of Fire");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(840, 500);
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
        JLabel width = new JLabel("Set Size : ");
        controller3.add(width);
        controller3.add(setWidth);
        setWidth.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            
            String text2 = setWidth.getText();
            myModel.setSize(Integer.parseInt(text2),Integer.parseInt(text2));
            System.out.println(Integer.parseInt(text2));
            
            int boxSize=(int)((400)/Integer.parseInt(text2));
            myView.setSize(boxSize, boxSize);
            
            myModel.fieldReset();
            //myModel = new ModelClass(Integer.parseInt(text2),Integer.parseInt(text2));
          }
        });
        
      }
      
      JPanel controller5=new JPanel();
      controller.add(controller5);
      
      {
        JLabel probBurn = new JLabel("Set ProbBurn : ");
        controller5.add(probBurn);
        controller5.add(setProbBurn);
        setHeight.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            
           
          }
        });
      }
      
      JPanel controller6=new JPanel();
      controller.add(controller6);
      
      {
        JLabel probTree = new JLabel("Set ProbTree : ");
        controller6.add(probTree);
        controller6.add(setProbTree);
        setHeight.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            
            
          }
        });
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
        
        
        stepButton=new JButton("Step");//It can not use this button
        stepButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            if(startThread==null||!startThread.isAlive()){
              startThread=new Thread() {  
                public void run() { 
                  myModel.spread();
                  
                  myModel.update();
                  
                }  
              };
              startThread.start();
            }
          }
        });
       
        controller1.add(stepButton);
        
        
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
        
        
        
       /*JButton randomBurn=new JButton("RandomBurn");
        randomBurn.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            if(startThread!=null&&startThread.isAlive()){
              startThread.stop();
            }
            
            myModel.fieldRandomStartReset();
          }
        });
        controller1.add(randomBurn);*/
      }
      
      
      
      
    }
    
    setVisible(true);
    
  }
  
}
