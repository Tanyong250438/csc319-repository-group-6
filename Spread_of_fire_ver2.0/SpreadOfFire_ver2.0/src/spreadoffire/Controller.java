package spreadoffire;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *The controller class of this project
 * 
 * author KMUTT student 
 * 56130500206
 * 56130500221
 * 56130500254
 * @version 2.0
 */
public class Controller extends JFrame{
  
  ModelClass myModel;
  
  View myView;
  
  Thread startThread;
  
  JButton startButton,stepButton,moveButton,northwind,southwind,eastwind,westwind;
  
  JLabel probability,ratio,empty,size,delay;
  
  JTextField setProp = new JTextField(15);
  JTextField setWidth =new JTextField(15);
  JTextField setProbBurn = new JTextField(15);
  JTextField setProbTree = new JTextField(15);
  JTextField setProbLight = new JTextField(15);
  
  static boolean northWind = false;
  static boolean southWind = false;
  static boolean eastWind = false;
  static boolean westWind = false;
  
  /*
   Constructer-contain the all controller panel
  */
  public Controller(){
    
    super("Spread of Fire");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(860, 450);
    setResizable(false);
    setLayout(new GridLayout(1, 2));
    
    
    myModel=new ModelClass();
    
    // To resize the cell to fit in the field
    int boxWidth=(int)((400)/myModel.width);
    int boxHeight=(int)(400/myModel.height);
    myView=new View(boxWidth,boxHeight);
    
    
    add(myView);
    
    
    myModel.addObserver(myView);
    
    /*
    create new JPanel for major controller to load buttons and textFields
    */
    JPanel controller=new JPanel();
    
    /*
    Set major controller with GridLayout to format controller button and textField
    */
    controller.setLayout(new GridLayout(4,1));
    
    //add controller to the JFrame
    add(controller);
    
    
    {
      /*
        create new controller2 to pack each button and textField below and add to the major controller that will be more neatness
      */
      JPanel controller2=new JPanel();
      //add controller2 to sort by GridLayout in the major controller
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
      
      //do the same thing with the controller2
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
      //do the same thing with the controller2
      JPanel controller4=new JPanel();
      controller.add(controller4);
      
      {
        JLabel probBurn = new JLabel("Set ProbBurn : ");
        controller4.add(probBurn);
        controller4.add(setProbBurn);
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
      //do the same thing with the controller2
      JPanel controller5=new JPanel();
      controller.add(controller5);
      
      {
        JLabel probTree = new JLabel("Set ProbTree : ");
        controller5.add(probTree);
        controller5.add(setProbTree);
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
      //do the same thing with the controller2
      JPanel controller6=new JPanel();
      controller.add(controller6);
      
      {
        JLabel probLight = new JLabel("Set ProbLight : ");
        controller6.add(probLight);
        controller6.add(setProbLight);
        setProbLight.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            try{
              String text5 = setProbLight.getText();
              myModel.setProbLightning(Integer.parseInt(text5));
              System.out.println(Integer.parseInt(text5));
              
             
              if(Integer.parseInt(text5)<0 || Integer.parseInt(text5)>100){
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
      
      //do the same thing with the controller2
      JPanel controller7=new JPanel();
      controller.add(controller7);
      
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
        controller7.add(startButton);
        
        
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
        
        controller7.add(stepButton);
        
        JButton resetButton=new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            if(startThread!=null&&startThread.isAlive()){
              startThread.stop();
            }
            
            myModel.fieldReset();
            myView.updateStep(0);
            myModel.step = 0;
            southWind = false;
            eastWind = false;
            northWind = false;
            westWind = false;
          }
        });
        controller7.add(resetButton);

        /*
            create new cotroller8 for pack wind buttons together
        */
        JPanel controller8=new JPanel();
        //and set it with GridLayout for neat of button 
        controller8.setLayout(new GridLayout(4,1));
        // add controller8 to the major controller
        controller.add(controller8);
        
        
        //create 4 button fot control the wind and load into the controller8
        northwind=new JButton("Noth Wind");//It can not use this button
        northwind.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            northWind = true;
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
        
        controller8.add(northwind);
        
        southwind=new JButton("South Wind");//It can not use this button
        southwind.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            southWind = true;
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
        controller8.add(southwind);
        
        eastwind=new JButton("East Wind");//It can not use this button
        eastwind.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            eastWind = true;
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
        controller8.add(eastwind);
        
        westwind=new JButton("West Wind");//It can not use this button
        westwind.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            
            westWind = true;
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
        controller8.add(westwind);
        
        
        
        
        
        JButton helpButton=new JButton("HELP");
        helpButton.addActionListener(new ActionListener(){
          
          public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null,"Set ProbCatch :set the probability of spread of fire(integer 0-100)"+"\n"+"\n"+
                                          "Set Size : set the size of the forest (integer 0-200)"+"\n"+"\n"+
                                          "Set ProbBurn: set the probability of burning point(integer 0-100)"+"\n"+"\n"+
                                          "Set ProbTree: set probability of tree(integer 0-100)"+"\n"+"\n"+
                                          "Set ProbLightning : set probability of Lightning(integer 0-100)"+"\n"+"\n"+
                                          "Auto Button : click to show the spread of fire automatically"+"\n"+"\n"+
                                          "Step Button : click to show the spread of fire step by step"+"\n"+"\n"+
                                          "Reset Button : click to reset state"+"\n"+"\n"+
                                          "North Wind : The wind came from North"+"\n"+"\n"+
                                          "South Wind : The wind came from South"+"\n"+"\n"+
                                          "West Wind : The wind came from West" +"\n"+"\n"+
                                          "East Wind : The wind came from East", "MANUAL",
        JOptionPane.INFORMATION_MESSAGE);
            
            
          }
        });
        controller1.add(helpButton);
      }
      
      
      
      
    }
    
    setVisible(true);
    
  }
  
}

