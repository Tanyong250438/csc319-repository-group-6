import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The controller class of project from MVC pattern
 * 
 * @author ....
 * @version 2014.04.11
 */
public class Controller extends JFrame{
    ModelClass myModel;
    View myView;
    Thread startThread;
    JButton startButton,stopButton,moveButton;
    JLabel probability,ratio,empty,size,delay;
    JSlider probabilityScale,ratioScale,emptyScale,sizeScale,delayScale;
    JTextField setProp =new JTextField(15);
    JTextField setSize =new JTextField(15);
    JTextField setHight =new JTextField(15);

    /**
     * Create the GUI of project
     */
    public Controller(){
        //Create the main frame
        super("Spread of Fire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 430);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        
        //Create the model, the main process of project
        myModel=new ModelClass();
        
        //Create the view, the output of main process of project
        int boxWidth=(int)((400)/myModel.width);
        int boxHeight=(int)(400/myModel.height);
        myView=new View(boxWidth,boxHeight);
        
        //Add the myView panel to the left
        add(myView);
        
        //Let myView be the observer of myModel
        myModel.addObserver(myView);
        
        //Create the controller panel
        JPanel controller=new JPanel();
        controller.setLayout(new GridLayout(4,1));
        
        //Add the controller panel to the right
        add(controller);
        
        //Properties of controller panel
        {
            
            JPanel controller2=new JPanel();
            controller.add(controller2);
            {
                JLabel prop = new JLabel("PropCatch : ");
                controller2.add(prop);
                controller2.add(setProp);
           
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
            
            //Create and Add the 1st row to controller panel
            JPanel controller1=new JPanel();
            controller.add(controller1);
            //Properties of 1st row
            {
                           
                //Create and Add the startButton
                startButton=new JButton("Auto");
                startButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //If there is no Thread or Thread is dead, create new Thread and start
                        if(startThread==null||!startThread.isAlive()){
                            startThread=new Thread() {  
                                public void run() { 
                                    myModel.checkBurn();
                                }  
                            };
                            startThread.start();
                        }
                    }
                });
                controller1.add(startButton);
                
                //Create and Add the stopButton
                stopButton=new JButton("Step");
                stopButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //If There is an alive Thread, stop it
                        if(startThread!=null&&startThread.isAlive()){
                            startThread.stop();
                        }

                    }
                });
                controller1.add(stopButton);
                
                //Create and Add the resetButton
                JButton resetButton=new JButton("Reset");
                resetButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //If There is an alive Thread, stop it
                        if(startThread!=null&&startThread.isAlive()){
                            startThread.stop();
                        }
                        //Reset the main process
                        myModel.fieldReset();
                    }
                });
                controller1.add(resetButton);
            }
            
            
            
            
        }
        //Set the frame Visible
        setVisible(true);
        
    }

}