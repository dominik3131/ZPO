package lab5_1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileRead {

    public static void main(String[] args) {
        Path path = Paths.get("F:\\Studia\\Semestr V\\ZPO\\lab5\\lab5_1\\dane.txt");
        FileRead fr = new FileRead();
        fr.worstSalary(path);
        fr.bestSalary(path);
        fr.countryCount(path);
    }

    public void worstSalary(Path path) {
        try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())) {
            int twoMinSalarys = lines
                    .filter(country -> country.contains("PL"))
                    .mapToInt(salary -> getSalary(salary))
                    .sorted()
                    .limit(2)
                    .peek(System.out::println)
                    .sum();
            System.out.println("Sum of two worsts salary:  " + twoMinSalarys);
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        System.out.println();
    }

    public void bestSalary(Path path) {
        try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())) {
            int twoMaxSalarys = lines
                    .filter(country -> country.contains("PL"))
                    .map(salary -> getSalary(salary))
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .mapToInt(i -> i)
                    .peek(System.out::println)
                    .sum();
            System.out.println("Sum of two bests salary:  " + twoMaxSalarys);
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void countryCount(Path path) {
        try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())) {
            Map<Object, Long> countryCount = lines
                    .collect(Collectors
                    .groupingBy(country -> getCountry(country), LinkedHashMap::new, Collectors.counting()));
            System.out.println(countryCount);
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public static Integer getSalary(String str) {
        String[] temp = str.split(" ");
        return Integer.parseInt(temp[temp.length - 1]);
    }

    public static String getCountry(String str) {
        String[] temp = str.split(" ");
        return temp[2];
    }

}
