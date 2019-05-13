// l.sort(java.util.Comparator.comparingInt(String::length));

/*
Dane s¹ nast. zbiory:
studenci = {"AB", "GT", "BS", "UF", "KO", "MM", "PD"}
ang = {"GT", "BS", "UF", "KO", "PD"}
niem = {"BS", "PD"}
hiszp = {"AB", "PD"}
Pierwszy to inicja³y wszystkich studentów w grupie.  Nastêpne to podzbiory powy¿szych studentów znaj¹cych odpowiednio j. angielski, niemiecki i hiszpañski.  Napisz (mo¿liwie zwiêz³y) kod wypisuj¹cy:
a) studentów znaj¹cych angielski i niemiecki, ale nie znaj¹cych hiszpañskiego,
b) studentów nie znaj¹cych ¿adnego z powy¿szych jêzyków.
*/

/* Ad b)
     studenci = populateSet(Arrays.asList("AB", "GT", "BS", "UF", "KO", "MM", "PD"));
     studenci.removeAll(ang);
     studenci.removeAll(niem);
     studenci.removeAll(hiszp);
     studenci.forEach(x -> System.out.print(x + " "));    
*/


import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Jezyki
{
  private static Set<String> populateSet(List<String> li)
  {
    return new HashSet<>(li);
  }

  public static void main(String ... args)
  {
     Set<String> studenci = populateSet(Arrays.asList("AB", "GT", "BS", "UF", "KO", "MM", "PD"));
     Set<String> ang = populateSet(Arrays.asList("GT", "BS", "UF", "KO", "PD"));
     Set<String> niem = populateSet(Arrays.asList("BS", "PD"));
     Set<String> hiszp = populateSet(Arrays.asList("AB", "PD"));
     
     // #1
     studenci.retainAll(ang);
     studenci.retainAll(niem);
     studenci.removeAll(hiszp);
     studenci.forEach(System.out::println);

     // #2
     studenci = populateSet(Arrays.asList("AB", "GT", "BS", "UF", "KO", "MM", "PD"));
     
     List<Set<String>> langsPositive = Arrays.asList(ang, niem);
     Function<Set<String>, Predicate<String>> fun1 = x -> (y -> x.contains(y));
     //List<Predicate<String>> preds = langsPositive.stream().map(fun1).collect(Collectors.toList());

     List<Set<String>> langsNegative = Arrays.asList(hiszp);
     Function<Set<String>, Predicate<String>> fun2 = x -> (y -> !x.contains(y));
     //preds.addAll( langsNegative.stream().map(fun2).collect(Collectors.toList()) );
     
     List<Predicate<String>> preds = 
       Stream.concat(langsPositive.stream().map(fun1), langsNegative.stream().map(fun2)).collect(Collectors.toList());
     
     Stream<String> tempStream = studenci.stream();
     for(Predicate<String> p: preds)
       tempStream = tempStream.filter(p);
     tempStream.forEach(x -> System.out.print(x + " "));
     System.out.println();
     
     // #3
     studenci = populateSet(Arrays.asList("AB", "GT", "BS", "UF", "KO", "MM", "PD"));
     Predicate<String> predAng = x -> ang.contains(x);
     Predicate<String> predNiem = x -> niem.contains(x);
     Predicate<String> predHiszp = x -> hiszp.contains(x);
     //Predicate<String> predHiszpNegate = predHiszp.negate();

     studenci.stream().filter(predAng.and(predNiem).and(predHiszp.negate())).
       forEach(x -> System.out.print(x + " "));
     System.out.println();
  }
}
