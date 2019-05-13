package lab4_1;
import java.text.Collator;
import java.util.Locale;
import java.util.Arrays;
import java.util.Comparator;

public class Sortowanie {

    public static void sortStrings(Collator collator, String[] words)
    {
        int n = words.length;
        for (int i=1; i<n; ++i)
        {
            String key = words[i];
            int j = i-1;

            while (j>=0 && collator.compare(words[j],key)>0)
            {
                words[j+1] = words[j];
                j = j-1;
            }
            words[j+1] = key;
        }
    }

    public static void fastSortStrings(Collator collator,String[] words)
    {
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2){
                return collator.compare(s1,s2);
            }
        });
    }

    public static void fastSortStrings2(Collator collator,String[] words)
    {
        Arrays.sort(words, (String s1, String s2) -> collator.compare(s1,s2));
    }

    public static void main(String[] args) {
        String[] names = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        Collator plCollator = Collator.getInstance(new Locale("pl","PL"));
        plCollator.setStrength(Collator.SECONDARY);
        fastSortStrings2(plCollator,names);
        for (int i=0; i<names.length; ++i)
        {
            System.out.println(names[i]);
        }
        
        String[] tab = new String[4];
        Arrays.stream(tab).filter(s-> s!=null).filter(s->!s.isEmpty()).mapToInt(s->s.length());
    }
}
