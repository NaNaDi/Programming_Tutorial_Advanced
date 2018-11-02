import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Example extends JFrame {


    public Example(){
        initUI();
    }

    private JButton quitButton;
    private JButton testButton;
    private JPanel pane;
    private GroupLayout gl;
    private JTextField tf;
    private JTextArea ta;
    private Font font = new Font("SansSerif", Font.PLAIN, 20);

    private void initUI(){

        //new Button
        quitButton = new JButton("Quit");
        //set tooltip text
        quitButton.setToolTipText("Want to quit?");

        //ActionListener (Quit on Button Press)
        quitButton.addActionListener((event) -> System.exit(0));

        testButton = new JButton("test");
        testButton.setToolTipText("Press me");
        testButton.addActionListener((event) -> onSubmit());

        tf = new JTextField();
        tf.setFont(font);
        tf.setText("Hello World!");

        ta = new JTextArea();
        ta.setFont(font);
        ta.setText("Output goes here");

        setTitle("Simple example");

        createFlowLayout();
        //uncomment for group layout
        //createGroupLayout

        //setSize(300,200);

        //Position in screen center
        setLocationRelativeTo(null);
        //Close Application when window is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createGroupLayout (){
        //create and set pane. Children can be placed in a pane of a JFrame (to make Button visible)
        pane = (JPanel) getContentPane();
        gl = new GroupLayout(pane);
        pane.setLayout(gl);

        //set tooltip text for whole pane
        pane.setToolTipText("This is a content pane");

        //gaps between components for design reasons
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        //layout for each dimension
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(tf)
                                                        .addComponent(testButton)
                                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(ta)
                                                            .addComponent(quitButton)));

        gl.setVerticalGroup(gl.createSequentialGroup().addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(tf)
                                                    .addComponent(testButton)
                                                    .addComponent(ta))
                                                    .addComponent(quitButton));

        //set size automatically according to components
        pack();
    }

    private void createFlowLayout(){
        pane = (JPanel) getContentPane();
        pane.add(tf);
        pane.add(testButton);
        pane.add(ta);
        pane.add(quitButton);
        pane.setLayout(new FlowLayout());
        pane.setSize(300, 300);
        pack();
    }

    private void onSubmit(){
        ta.setText("You entered: " + tf.getText());
    }

    public static void main(String[] args){
        //places the application on the Swing Event Queue such that all UI updates are concurrency-safe
        EventQueue.invokeLater(() ->{
            Example ex = new Example();
            ex.setVisible(true);
        });
    }

}
