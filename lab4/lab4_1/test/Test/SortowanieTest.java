package Test;

import junit.framework.TestCase;

import java.text.Collator;
import java.util.Locale;
import java.text.*;
import lab4_1.Sortowanie;

public class SortowanieTest extends TestCase {
    Collator plCollator = Collator.getInstance(new Locale("pl", "PL"));
    String[] namesTrue = {"Agnieszka", "Darek", "Lucyna", "Łukasz","Marek","Órszula","Stefania","Ścibor","Świętopełk","Zyta"};

    public void testSortStrings() {
        String[] names0 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        Sortowanie.sortStrings(plCollator,names0);
        for (int i=0; i<names0.length; ++i)
        {
            assertEquals(namesTrue[i],names0[i]);
            System.out.println(names0[i]);
        }
    }

    public void testFastSortStrings() {
        String[] names1 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        Sortowanie.fastSortStrings(plCollator,names1);
        for (int i=0; i<names1.length; ++i)
        {
            assertEquals(namesTrue[i],names1[i]);
            System.out.println(names1[i]);
        }
    }

    public void testFastSortStrings2() {
        String[] names2 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        Sortowanie.fastSortStrings2(plCollator,names2);
        for (int i=0; i<names2.length; ++i)
        {
            assertEquals(namesTrue[i],names2[i]);
            System.out.println(names2[i]);
        }
    }

    public void testAll(){
        String[] names0 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        String[] names1 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        String[] names2= {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        Sortowanie.sortStrings(plCollator,names0);
        Sortowanie.fastSortStrings(plCollator,names1);
        Sortowanie.fastSortStrings2(plCollator,names2);
        for (int i=0; i<names0.length; ++i)
        {
            assertEquals(names1[i],names2[i]);
            assertEquals(names0[i],names2[i]);
            assertEquals(names1[i],names0[i]);
        }
    }

    public void testTime() {
    long start0=System.nanoTime();
    for(int i=0;i<100000;i++)
    {
        String[] names0 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
        Sortowanie.sortStrings(plCollator,names0);
    }
    long stop0=System.nanoTime();

        long start1=System.nanoTime();
        for(int i=0;i<100000;i++)
        {
            String[] names1 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
            Sortowanie.fastSortStrings(plCollator,names1);
        }
        long stop1=System.nanoTime();

        long start2=System.nanoTime();
        for(int i=0;i<100000;i++)
        {
            String[] names2 = {"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka", "Zyta", "Órszula", "Świętopełk", "Marek", "Lucyna"};
            Sortowanie.fastSortStrings(plCollator,names2) ;
        }
        long stop2=System.nanoTime();

        System.out.println("sortStrings -> "+new DecimalFormat("0.######E0").format(stop0-start0)+" ns");
        System.out.println("fastSortStrings -> "+new DecimalFormat("0.######E0").format(stop1-start1)+" ns");
        System.out.println("fastSortStrings2 -> "+new DecimalFormat("0.######E0").format(stop2-start2)+" ns");
    }
}