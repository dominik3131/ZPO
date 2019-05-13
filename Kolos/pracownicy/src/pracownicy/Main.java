
package pracownicy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public List<Pracownik> allWorkers;
    public Pracownik boss;
    public Main(){
        allWorkers=new ArrayList<>();
        
    }
    public static void main(String[] args) throws IOException{
        Main m=new Main();
        m.loadWorkers();
        
        m.printTree();
       
        
    }
    
    public void loadWorkers() throws IOException{
        Path p=FileSystems.getDefault().getPath("osoby.txt");
        List<String> tempList = Files.readAllLines(p, StandardCharsets.UTF_8);
        for(String line : tempList){
            String[] tmp = line.split(",");
            Pracownik w,s;
            if(tmp[1].trim().equals("null")){
                boss = new Pracownik(tmp[0].trim());
                if(!allWorkers.contains(boss)) allWorkers.add(boss);
            }
            else{
                
                s = new Pracownik(tmp[1].trim());
                
                if(allWorkers.contains(s)){
                    s=allWorkers.get(allWorkers.indexOf(s));
                }
                
                w= new Pracownik(tmp[0].trim());
                if(allWorkers.contains(w)){
                    w=allWorkers.get(allWorkers.indexOf(w));
                }
               
                s.addSubordinate(w);
                w.setSupervisor(s);
                allWorkers.add(w);
                allWorkers.add(s);
            }
            
        }
    }
    public void printTree(){
        boss=allWorkers.get(allWorkers.indexOf(boss));
        boss.printSubordinates(0);
    }
    
    public int h(Pracownik p1,Pracownik p2){
        if(p1.supervisor.equals(p2)) return 1;
        else if(p2.supervisor.equals(p1)) return 2;
        else if (p1.supervisor.equals(p2.supervisor)) return 3;
        else return 4;
    }
    
   
}
