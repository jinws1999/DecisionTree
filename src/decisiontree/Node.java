package decisiontree;
import java.util.*;
public class Node {
    String content="";
    ArrayList<Node>next=new ArrayList();
    ArrayList<String>choices=new ArrayList();
    int row,col;
    public Node(Data data){
        if(data.items.isEmpty()){return;}
        content=endresult(data);
        if(content.equals("")){
            int choice=choosevar(data);
            content=data.var.get(choice)+"?";
            choices=aofvars(data,choice);
            for(int i=0;i<choices.size();i++){
                next.add(new Node(subdata(data,choice,choices.get(i))));
            }
            
        }
    }
    public class Int{
        int value;
        public Int(int v){value=v;}
    }
    public String endresult(Data data){
        //judge whether its ready to form leaf node
        Map<String,Int>map=mapofresults(data);
        if(map.size()==1){return data.items.get(0).result;}
        
        if(data.var.isEmpty()){
            String re="";int v=0;
            Set<String>set=map.keySet();
            Iterator<String>it=set.iterator();
            while(it.hasNext()){
                String nexts=it.next();
                if(map.get(nexts).value>v){
                    v=map.get(nexts).value;
                    re=nexts;
                }
                
            }
            return re;
        }
        
        return "";
        
    }
    public Map<String,Int> mapofresults(Data data){
        Map<String,Int>map=new TreeMap();
        for(int i=0;i<data.items.size();i++){
            if(map.containsKey(data.items.get(i).result)){
                map.get(data.items.get(i).result).value++;
            }
            else map.put(data.items.get(i).result,new Int(1));
        }
        return map;
    }
    public ArrayList<String> aofvars(Data data,int index){
        Set<String> set=new TreeSet();
        for(int i=0;i<data.items.size();i++){
            set.add(data.items.get(i).var.get(index));
        }
        ArrayList<String>a=new ArrayList();
        Iterator<String>it=set.iterator();
        while(it.hasNext()){
            a.add(it.next());
        }
        return a;
    }
    public Data subdata(Data ori,int vindex,String vchoice){
        Data n=new Data();
        n.dataname=ori.dataname;
        for(int i=0;i<ori.var.size();i++){
            if(i!=vindex){n.var.add(ori.var.get(i));}
        }
        for(int i=0;i<ori.items.size();i++){
            if(ori.items.get(i).var.get(vindex).equals(vchoice)){
                Item ni=new Item(ori.items.get(i).result);
                for(int j=0;j<ori.var.size();j++){
                    if(j!=vindex){ni.var.add(ori.items.get(i).var.get(j));}
                }
                n.items.add(ni);
            }
        }
        return n;
    }
    
    public double Entropy(Data data){
        //calculate Entropy of D
        Map<String,Int> map=mapofresults(data);
        Set<String>set=map.keySet();
        Iterator<String>it=set.iterator();
        ArrayList<Int>Dk=new ArrayList();
        while(it.hasNext()){
            Dk.add(map.get(it.next()));
        }
        double Ent=0;
        for(int i=0;i<Dk.size();i++){
            double pk=(double)(Dk.get(i).value)/data.items.size();
            Ent-=(pk*Math.log10(pk)/Math.log10(2));
        }
        return Ent;
    }
    
    
    
    public int choosevar(Data data){
        //scores are gain ratios
        double scores[]=new double[data.var.size()];
        for(int v=0;v<data.var.size();v++){
            ArrayList<String>a=aofvars(data,v);
            //calculate Dv, IV(v)
            int Dv[]=new int[a.size()];
            for(int i=0;i<a.size();i++){
                for(int j=0;j<data.items.size();j++){
                    if(data.items.get(j).var.get(v).equals(a.get(i))){Dv[i]++;}
                }
            }
            double IV=0,Gain=0;
            for(int i=0;i<a.size();i++){
                double DvdD=(double)Dv[i]/(double)data.items.size();
                IV-=(DvdD*Math.log10(DvdD)/Math.log10(2));
            }
            //calculate Entropy of D,Dv,Gain
            Gain=Entropy(data);
            double pEntropy;
            for(int i=0;i<a.size();i++){
                Data ndata=subdata(data,v,a.get(i));
                pEntropy=Entropy(ndata);
                Gain-=(ndata.items.size()*pEntropy/data.items.size());
            }
            
            
            if(IV==0)IV=0.001;
            scores[v]=Gain/IV;
        }
        double score=scores[0];int c=0;
        for(int i=1;i<scores.length;i++){
            if(scores[i]>score)c=i;
        }
        return c;
    }
}
