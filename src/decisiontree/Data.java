package decisiontree;
import java.io.*;
import java.util.*;
public class Data {
    String dataname;
    ArrayList<String>var;
    ArrayList<Item>items;
    
    public Data(){
        dataname="";
        var=new ArrayList();
        items=new ArrayList();
    }
    
    public void readData(String f){
        File file=new File(f);
        try{
            String r;
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            
            r=br.readLine();
            dataname=r;
            r=br.readLine();
            while((!"#".equals(r))&&(!"".equals(r))){
                var.add(r);r=br.readLine();
            }
            int vn=var.size();
            while((r=br.readLine())!=null){
                items.add(new Item(r));
                int index=items.size()-1;
                for(int j=0;j<vn;j++){
                    r=br.readLine();
                    items.get(index).var.add(r);
                }
            }
            br.close();
            fr.close();
            for(int i=0;i<items.size();i++){
                for(int j=0;j<items.get(0).var.size();j++){
                    if(items.get(i).var.get(j).equals("")){items.get(i).var.set(j,"other");}
                }
            }
        }catch(Exception e){}
    }
    
    
    
    
}
