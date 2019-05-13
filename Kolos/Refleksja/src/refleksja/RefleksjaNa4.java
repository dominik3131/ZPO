package refleksja;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RefleksjaNa4 {

    public static void main(String[] args) throws IOException {
        PrintWriter out = null;
        Method[] methodArr = LinkedList.class.getDeclaredMethods();
        List<Method> simpleMethodList = new ArrayList<>();
        try {
            File file = new File("kolos.txt");
            file.createNewFile();
            out = new PrintWriter("kolos.txt");
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        
        for (Method m : methodArr) {
            if (!m.isAnnotationPresent(Override.class)) {
                if (m.getDeclaringClass().equals(LinkedList.class)) {
                    if (m.getReturnType().isPrimitive()) {
                        if (!m.getReturnType().getName().equals("void")) {
                            simpleMethodList.add(m);
                        }

                    }
                }
            }
        }

        Comparator<Method> comTypes = (Method a, Method b) -> a.getReturnType().getName().compareTo(b.getReturnType().getName());
        Comparator<Method> comParamsCount = (Method a, Method b) -> a.getParameterCount() - b.getParameterCount();
        simpleMethodList.sort(comTypes.thenComparing(comParamsCount));
        
        String[] names = new String[simpleMethodList.size()];
        int i = 0;
        for (Method m : simpleMethodList) {
            StringBuffer sb = new StringBuffer();
            sb.append(m.getReturnType() + " " + m.getName() + " (");

            //for (Parameter p : m.getParameters()) {
            int j = 0;
            for (Class p : m.getParameterTypes()) {
                sb.append(p.getSimpleName() + " arg" + j + ",");
                j++;
            }
            if (j > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");

            names[i] = sb.toString();
            i++;

        }
        int max = 0;
        for (String m : names) {
            if (m.length() > max) {
                max = m.length();
            }
        }
        for (String m : names) {
            System.out.println(m);
            StringBuffer spaces = new StringBuffer();
            for (i = 0; i < max - m.length(); i++) {
                spaces.append(" ");
            }
            out.println(m + spaces.toString());

        }
        out.flush();
        out.close();
    }

}
