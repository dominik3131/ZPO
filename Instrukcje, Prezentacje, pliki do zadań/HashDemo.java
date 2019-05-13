import java.util.*;
import static java.lang.System.out;


class Square
{
  private char file;  // a-h
  private byte rank;  // 1-8
  
  Square(char f, byte r)
  {
    file = f;
    rank = r;
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof Square))
      return false;
    Square s = (Square)o;
    return this.file == s.file && this.rank == s.rank;
  }
  
  @Override
  public int hashCode()
  {
    return ((int)file * 10 + rank) % 2;
  }  
}


public class HashDemo
{
  public static void main(String[] args)
  {
    String[] pieces = new String[] { "KW", "QW", "RW", "BW", "NW", "pW", 
                                     "KB", "QB", "RB", "BB", "NB", "pB" };
    HashMap<Square, String> hm = new HashMap<Square, String>();
    
    Square s1 = new Square('a', (byte)5);
    hm.put(s1, "RB");
    
    Square s2 = new Square('a', (byte)7);
    hm.put(s2, "NW");
    
    out.println((String)hm.get(s1));
    
    out.println((String)hm.get(new Square('a', (byte)7)));
    out.println((String)hm.get(new Square('a', (byte)5)));
    out.println((String)hm.get(new Square('a', (byte)3)));
    out.println((String)hm.get(new Square('b', (byte)7)));
  }
}
