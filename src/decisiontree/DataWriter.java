package decisiontree;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.*;
public class DataWriter {
    int v,i,x,y;
    JFrame frame;
    Container con;
    JPanel pan;
    JLabel filenamel,datanamel,result,category;
    JTextField filenamet,datanamet;
    JButton readb,saveb,additem,addvar,chooser,up,down,left,right;
    ArrayList <JTextField> var;
    ArrayList <ItemWriter> items;
    
    DataWriter(){
        //initialize number count
        i=0;v=0;x=0;y=0;
        
        //initialize frame
        frame=new JFrame("Data Edition");
        frame.setBounds(100, 100, 1000, 1000);
        con=frame.getContentPane();
        con.setLayout(null);
        
        //initialize basic elements
        filenamel=new JLabel("FileName");datanamel=new JLabel("DataName");result=new JLabel("Result");category=new JLabel("items\\properties");
        filenamet=new JTextField();datanamet=new JTextField();
        readb=new JButton("Read");saveb=new JButton("Save");additem=new JButton("AddItem");addvar=new JButton("AddProperty");
        up=new JButton("up");down=new JButton("down");left=new JButton("left");right=new JButton("right");
        chooser=new JButton("Chooce File");
        var=new ArrayList();items=new ArrayList();
        pan=new JPanel(null);
        
        
        con.add(filenamel);con.add(datanamel);con.add(filenamet);con.add(datanamet);con.add(pan);
        con.add(readb);con.add(saveb);con.add(additem);con.add(addvar);con.add(chooser);con.add(up);con.add(down);con.add(left);con.add(right);
        pan.add(result);pan.add(category);
        
        //set locations
        chooser.setBounds(200,50,120,40);filenamel.setBounds(330, 50, 80, 40);filenamet.setBounds(420, 50, 200, 40);readb.setBounds(630, 50, 80, 40);
        saveb.setBounds(720, 50, 80, 40);
        datanamel.setBounds(330, 110, 80, 40);datanamet.setBounds(420, 110, 200, 40);
        additem.setBounds(250, 160, 70, 40);addvar.setBounds(330, 160, 100, 40);
        up.setBounds(440, 160, 70, 40);down.setBounds(520, 160, 70, 40);
        left.setBounds(600, 160, 70, 40);right.setBounds(680, 160, 70, 40);
        pan.setBounds(100,210,1000,1000);
        category.setBounds(0,0,150,40);result.setBounds(160, 0, 100, 40);
        
        
        //100 260 370 480
        //210 260 310 360
        
        //set button effects
        readb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                read();
            }
        });
        
        saveb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        
        additem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                addi();
            }
        });
        
        addvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                addv();
            }
        });
        
        up.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                letup();
            }
        });
        
        down.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                letdown();
            }
        });
        
        left.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                letleft();
            }
        });
        
        right.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                letright();
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
        
    }
    
    public void save(){
        File file;
        if("".equals(filenamet.getText())){file=new File("data.txt");}
        else file=new File(filenamet.getText());
        try{
            if(!file.exists()){file.createNewFile();}
            FileWriter fw=new FileWriter(file);
            BufferedWriter bw=new BufferedWriter(fw);
            
            bw.write(datanamet.getText()+"\n");
            
            ArrayList<Integer>index=new ArrayList();
            for(int j=0;j<var.size();j++){
                if(!var.get(j).getText().equals("")){index.add(j);bw.write(var.get(j).getText()+"\n");}
            }
            bw.write("#\n");
            for(int j=0;j<items.size();j++){
                if(!items.get(j).result.getText().equals("")){
                    bw.write(items.get(j).result.getText()+"\n");
                    for(int k=0;k<index.size();k++){
                        bw.write(items.get(j).var.get(index.get(k)).getText()+"\n");
                    }
                }
            }
            bw.close();
            fw.close();
        }catch(Exception e){}
    }
    
    public void read(){
        clear();
        Data data=new Data();
        data.readData(filenamet.getText());
        datanamet.setText(data.dataname);
        for(int j=0;j<data.var.size();j++){
            addv();
            var.get(j).setText(data.var.get(j));
        }
        
        for(int j=0;j<data.items.size();j++){
            addi();
            items.get(j).result.setText(data.items.get(j).result);
            for(int k=0;k<data.var.size();k++){
                items.get(j).var.get(k).setText(data.items.get(j).var.get(k));
            }
        }
    }
    
    public void clear(){
        //return to original position
        setlocation(100*x,100*y);
        x=0;y=0;
        
        while(var.size()>0){
            pan.remove(var.get(0));
            var.remove(0);
        }
        result.setBounds(160, 0, 100, 40);
        while(items.size()>0){
            items.get(0).clear();
            items.remove(0);
        }
        i=0;v=0;
    }
    
    public void addi(){
        //return to original position
        setlocation(100*x,100*y);
        x=0;y=0;
        
        i++;
        items.add(new ItemWriter(i,v,pan));
        
    }
    
    public void addv(){
        //return to original position
        setlocation(100*x,100*y);
        x=0;y=0;
        
        v++;
        var.add(new JTextField());
        pan.add(var.get(v-1));
        result.setBounds(160+v*110,0,100,40);
        var.get(v-1).setBounds(50+v*110,0,100,40);
        for(int j=0;j<i;j++){items.get(j).addvar(j+1);}
    }
    
    public void letup(){
        if(y>0){y--;setlocation(0,100);}
    }
    
    public void letdown(){
        y++;setlocation(0,-100);
    }
    
    public void letleft(){
        if(x>0){x--;setlocation(100,0);}
    }
    
    public void letright(){
        x++;setlocation(-100,0);
    }
    
    public void setlocation(int mx,int my){
        category.setBounds(category.getX()+mx, category.getY()+my, category.getWidth(), category.getHeight());
        result.setBounds(result.getX()+mx, result.getY()+my, result.getWidth(), result.getHeight());
        for(int j=0;j<var.size();j++){
            var.get(j).setBounds(var.get(j).getX()+mx,var.get(j).getY()+my,var.get(j).getWidth(),var.get(j).getHeight());
        }
        for(int j=0;j<items.size();j++){
            items.get(j).setlocation(mx,my);
        }
    }
}
