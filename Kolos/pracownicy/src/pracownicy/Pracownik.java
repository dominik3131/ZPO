/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pracownicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dominik
 */
public class Pracownik implements Comparable<Pracownik> {

    Pracownik supervisor;
    String name;
    List<Pracownik> subordinates;
   
    public Pracownik(String n) {
        this.supervisor = null;
        name = n;
        subordinates = new ArrayList<>();
    }

    public Pracownik(String n, Pracownik s) {
        name = n;
        supervisor = s;
        subordinates = new ArrayList<>();
    }

    public void setSupervisor(Pracownik s) {
        supervisor = s;
    }

    public Pracownik getSupervisor() {
        return supervisor;
    }

    public String getName() {
        return name;
    }

    public void addSubordinate(Pracownik p) {
        subordinates.add(p);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pracownik other = (Pracownik) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public void print(int level) {
        if (level == 0) {
            System.out.print(this.getName() + "("+getBelow()+","+getAbove()+")\n");
        } else {
            System.out.print("|");
            for (int i = 1; i < level * 2; i++) {
                System.out.print("-");
            }
            System.out.print(this.getName() + "("+getBelow()+","+getAbove()+")\n");
        }

    }

    public void printSubordinates(int level) {
        print(level);
        Collections.sort(subordinates);
        for (Pracownik p : subordinates) {
            p.printSubordinates(level + 1);
        }

    }
    public int getAbove(){
        if(supervisor==null) return 0;
        else return 1+supervisor.getAbove();
    }
    
    public int getBelow(){
        int below=0;
        below+=subordinates.size();
        for(Pracownik p:subordinates){
            below+=p.getBelow();
        }
        return below;
    }

    @Override
    public int compareTo(Pracownik o) {
        return this.getName().compareTo(o.getName());
    }
    
    
}
