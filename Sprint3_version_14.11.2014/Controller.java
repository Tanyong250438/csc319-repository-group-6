

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
  JTextField setProp = new JTextField(15);
  JTextField setWidth =new JTextField(15);
  JTextField setProbBurn = new JTextField(15);
  JTextField setProbTree = new JTextField(15);
  public Controller(){
    
    super("Spread of Fire");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(860, 450);
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
        JLabel prop = new JLabel("Set PropCatch : ");
        controller2.add(prop);
        controller2.add(setProp);
        setProp.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            
            try{
              String text = setProp.getText();
              myModel.setProbability(Integer.parseInt(text));
              System.out.println(Integer.parseInt(text));
              if(Integer.parseInt(text)<0 || Integer.parseInt(text)>100){
                JOptionPane.showMessageDialog(null,"please input only 0-100","Warning",
                                              JOptionPane.WARNING_MESSAGE);
              }
            }catch(Exception a){
              JOptionPane.showMessageDialog(null,"please input only integer","Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
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
            
            try{
              String text2 = setWidth.getText();
              myModel.setSize(Integer.parseInt(text2),Integer.parseInt(text2));
              System.out.println(Integer.parseInt(text2));
              
              int boxSize=(int)((400)/Integer.parseInt(text2));
              myView.setSize(boxSize, boxSize);
              
              myModel.fieldReset();
              if(Integer.parseInt(text2)<0 || Integer.parseInt(text2)>200){
                JOptionPane.showMessageDialog(null,"please input only 0-200","Warning",
                                              JOptionPane.WARNING_MESSAGE);
              }
            }catch(Exception a){
              JOptionPane.showMessageDialog(null,"please input only integer","Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
            
          }
        });
        
      }
      
      JPanel controller5=new JPanel();
      controller.add(controller5);
      
      {
        JLabel probBurn = new JLabel("Set ProbBurn : ");
        controller5.add(probBurn);
        controller5.add(setProbBurn);
        setProbBurn.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            try{
              String text3 = setProbBurn.getText();
              myModel.setProbBurn(Integer.parseInt(text3));
              System.out.println(Integer.parseInt(text3));
              
              myModel.fieldProbBurnReset();
              if(Integer.parseInt(text3)<0 || Integer.parseInt(text3)>100){
                JOptionPane.showMessageDialog(null,"please input only 0-100","Warning",
                                              JOptionPane.WARNING_MESSAGE);
              }
            }catch(Exception a){
              JOptionPane.showMessageDialog(null,"please input only integer","Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
          }
        });
      }
      
      JPanel controller6=new JPanel();
      controller.add(controller6);
      
      {
        JLabel probTree = new JLabel("Set ProbTree : ");
        controller6.add(probTree);
        controller6.add(setProbTree);
        setProbTree.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            try{
              String text4 = setProbTree.getText();
              myModel.setProbTree(Integer.parseInt(text4));
              System.out.println(Integer.parseInt(text4));
              
              myModel.fieldProbBurnReset();
              if(Integer.parseInt(text4)<0 || Integer.parseInt(text4)>100){
                JOptionPane.showMessageDialog(null,"please input only 0-100","Warning",
                                              JOptionPane.WARNING_MESSAGE);
              }
            }catch(Exception a){
              JOptionPane.showMessageDialog(null,"please input only integer","Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
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
            myView.updateStep(0);
            myModel.step = 0;
            
          }
        });
        controller1.add(resetButton);
        
        
        JButton helpButton=new JButton("HELP");
        helpButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null,"Set ProbCatch : to set the probability of spread of fire(integer 0-100)"+"\n"+"\n"+
                                          "Set Size : to set the size of the forest (integer 0-200)"+"\n"+"\n"+
                                          "Set ProbBurn: to set the probability of burning point(integer 0-100)"+"\n"+"\n"+
                                          "Set ProbTree: to set probability of tree(integer 0-100)"+"\n"+"\n"+
                                          "Auto Button : click to show the spread of fire automatically"+"\n"+"\n"+
                                          "Step Button : click to show the spread of fire step by step"+"\n"+"\n"+
                                          "Reset Button : click to regrowth the tree and initial the forest state", "MANUAL",
        JOptionPane.INFORMATION_MESSAGE);
            
            
          }
        });
        controller1.add(helpButton);
      }
      
      
      
      
    }
    
    setVisible(true);
    
  }
  
}
