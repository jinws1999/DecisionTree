package decisiontree;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.time.Clock.system;
import javax.swing.*;
import java.util.*;
public class DecisionTree {
    
    public static void main(String[] args) {
        /*
        Data d=new Data();
        d.readData("dataset.txt");
        Node n=new Node(d);
        TreeDrawer t=new TreeDrawer();
        t.constructGraph(n);
        */
        //initialize
        JFrame mainf=new JFrame("Decision Tree Generator 1.0");
        Container mainc=mainf.getContentPane();
        mainc.setLayout(null);
        mainf.setVisible(true);
        mainf.setBounds(150,50,1100,800);
        mainf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        DataWriter dw=new DataWriter();
        TreeDrawer tree=new TreeDrawer();
        JButton writeb=new JButton("Create/Edit Data");
        JButton chooser=new JButton("Choose File");
        JButton construct=new JButton("Construct Tree");
        JButton up=new JButton("up");
        JButton down=new JButton("down");
        JButton left=new JButton("left");
        JButton right=new JButton("right");
        JTextField filenamet=new JTextField();
        
        mainc.add(writeb);mainc.add(chooser);mainc.add(construct);mainc.add(filenamet);mainc.add(tree);
        mainc.add(up);mainc.add(down);mainc.add(left);mainc.add(right);
        
        writeb.setBounds(50, 50, 150, 40);
        chooser.setBounds(250, 50, 120, 40);
        construct.setBounds(610, 50, 130, 40);
        filenamet.setBounds(390, 50, 200, 40);
        tree.setBounds(50, 150, 1000, 1000);
        up.setBounds(850, 30, 80, 40);
        down.setBounds(850, 70, 80, 40);
        left.setBounds(770, 50, 80, 40);
        right.setBounds(930, 50, 80, 40);
        
        writeb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dw.frame.setVisible(true);
            }
        });
        
        chooser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser choose=new JFileChooser();
                    choose.showOpenDialog(choose);
                    filenamet.setText(choose.getSelectedFile().getAbsolutePath());
                }catch(Exception ex){}
            }
        });
        
        construct.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Data data=new Data();
                data.readData(filenamet.getText());
                Node node=new Node(data);
                tree.constructGraph(node);
                tree.repaint();
            }
        });
        
        left.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tree.x+=100;tree.repaint();
            }
        });
        
        right.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tree.x-=100;tree.repaint();
            }
        });
        
        up.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tree.y-=100;tree.repaint();
            }
        });
        
        down.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tree.y+=100;tree.repaint();
            }
        });
    }
    
}
