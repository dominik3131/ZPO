package watki;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */


public class Kolejka {
    private List<String> q;
    private MessageDigest messageDigest;
    private String lastCheckSum;
    public Kolejka(){
        q = new LinkedList<>();
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Kolejka.class.getName()).log(Level.SEVERE, null, ex);
        }
        lastCheckSum=getCheckSum();
        
    }
    
    public void add(String s){
        if(q.size()==3) q.remove(0);
        q.add(s);
    }
    public String getCheckSum(){
        for(String s:q){
            messageDigest.update(s.getBytes());
        }
        byte[] hashInBytes = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public boolean checkIfUpdated(){
        String checkSum=getCheckSum();
        if(lastCheckSum.equals(checkSum)){
            
            return false;
        }
        else{
            lastCheckSum=checkSum;
            return true;
        }
    }
    public List<String> getList(){
        return q;
    }
    
}
