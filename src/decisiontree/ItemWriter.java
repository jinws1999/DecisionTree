package decisiontree;
import javax.swing.*;
import java.util.*;
public class ItemWriter {
    JLabel index;
    JTextField result;
    JPanel pan;
    ArrayList<JTextField>var;
    public ItemWriter(int i,int v,JPanel p){
        index=new JLabel(String.valueOf(i));
        result=new JTextField();
        var=new ArrayList();
        for(int j=0;j<v;j++){var.add(new JTextField());}
        pan=p;
        
        pan.add(index);pan.add(result);
        for(int j=0;j<v;j++){pan.add(var.get(j));}
        
        index.setBounds(0, 50*i, 50, 40);result.setBounds(160+110*v, 50*i, 100, 40);
        for(int j=0;j<v;j++){var.get(j).setBounds(160+110*j, 50*i, 100, 40);}
        
    }
    public void setlocation(int mx,int my){
        index.setBounds(index.getX()+mx,index.getY()+my,index.getWidth(),index.getHeight());
        result.setBounds(result.getX()+mx,result.getY()+my,result.getWidth(),result.getHeight());
        for(int j=0;j<var.size();j++){
            var.get(j).setBounds(var.get(j).getX()+mx,var.get(j).getY()+my,var.get(j).getWidth(),var.get(j).getHeight());
        }
    }
    
    public void addvar(int i){
        var.add(new JTextField());
        pan.add(var.get(var.size()-1));
        result.setBounds(160+var.size()*110,i*50,100,40);
        var.get(var.size()-1).setBounds(50+var.size()*110,i*50,100,40);
    }
    
    public void clear(){
        index.setBounds(-100, -100, 0, 0);
        result.setBounds(-100, -100, 0, 0);
        pan.remove(index);
        pan.remove(result);
        for(int j=0;j<var.size();j++){
            pan.remove(var.get(j));
        }
    }
    
}
