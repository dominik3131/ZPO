package lab8_1;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int[] tab = {4, 10, 3, 7, 4, 1, 6, 2};
        MaxSearchAlgorithms max = new MaxSearchAlgorithms();
        Method[] methodArr = MaxSearchAlgorithms.class.getDeclaredMethods();
        
        for (Method m : methodArr) {
            String name = m.getName();
            //System.out.println(m.getReturnType());
              System.out.println(m.getDeclaringClass().getSimpleName());

            if (name.contains("Scan") && !name.contains("lambda")) {
                m.setAccessible(true);
                int[] list = (int[]) m.invoke(new MaxSearchAlgorithms(), tab);
                System.out.println(name + ": " + Arrays.toString(list));
                
            }
        }
    }

}
