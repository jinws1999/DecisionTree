package decisiontree;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
public class TreeDrawer extends JPanel{
    int x=0,y=0;
    ArrayList<Row>g=new ArrayList();
    
    public class Row{
        ArrayList<Node>row=new ArrayList();
    }
    
    public void constructGraph(Node n){
        //clear
        x=0;y=0;
        while(g.size()>0){g.remove(0);}
        //let every node get into Rows
        Row row=new Row();
        row.row.add(n);
        g.add(row);
        while(true){
            row=new Row();
            for(int i=0;i<g.get(g.size()-1).row.size();i++){
                for(int j=0;j<g.get(g.size()-1).row.get(i).choices.size();j++){
                    row.row.add(g.get(g.size()-1).row.get(i).next.get(j));
                }
            }
            if(row.row.isEmpty())break;
            g.add(row);
        }
        //let each node has its row and col
        for(int i=0;i<g.size();i++){
            for(int j=0;j<g.get(i).row.size();j++){
                g.get(i).row.get(j).row=i+1;
                g.get(i).row.get(j).col=j+1;
            }
        }
    }
    public void paint(Graphics g1){
        super.paint(g1);
        Graphics2D g2=(Graphics2D)g1;
        g2.setFont(new Font("宋体",0,20));
        //word(+15,+35)
        for(int i=0;i<g.size();i++){
            for(int j=0;j<g.get(i).row.size();j++){
                int x1=525-75*g.get(i).row.size()+150*j+x;
                int y1=130*i+y;
                g2.draw(new Rectangle2D.Double(x1,y1,100,50));
                g2.drawString(g.get(i).row.get(j).content, x1+15, y1+35);
                for(int n=0;n<g.get(i).row.get(j).next.size();n++){
                    int x2=575-75*g.get(g.get(i).row.get(j).next.get(n).row-1).row.size()+150*(g.get(i).row.get(j).next.get(n).col-1)+x;
                    int y2=130*(g.get(i).row.get(j).next.get(n).row-1)+y;
                    g2.drawLine(x1+50,y1+50,x2,y2);
                    g2.drawString(g.get(i).row.get(j).choices.get(n), (x1+50+x2)/2, (y1+50+y2)/2);
                }
            }
        }
        
        
    }
}
