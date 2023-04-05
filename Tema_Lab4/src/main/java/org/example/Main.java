package org.example;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

public class Main {

    private static List<Project> generatePreferences(List<Project> projects) {
        Random rand = new Random();
        int upperbound=4;
        int int_random=rand.nextInt(upperbound);
        List<Project> prefs = projects.stream().collect(Collectors.toList());
        Collections.shuffle(prefs);
        return prefs.subList(0,int_random);

    }
    public static void main(String[] args) {


        Faker faker = new Faker();

        List<Project> projects = IntStream.range(0, 5)
                .mapToObj(i -> new Project(faker.company().name()))
                .collect(Collectors.toList());

        List<Student> students = IntStream.range(0, 10)
                .mapToObj(i -> new Student(faker.name().fullName(), generatePreferences(projects)))
                .collect(Collectors.toList());

        students.forEach(s-> System.out.println(s.getPreferences()));


        double avgPrefs = students.stream()
                .mapToInt(s -> s.getPreferences().size())
                .average()
                .orElse(0.0);

        List<Student> lessPrefs = students.stream()
                .filter(s -> s.getPreferences().size() < avgPrefs)
                .collect(Collectors.toList());

        System.out.println("Preferintele sub-medie sunt :");
        lessPrefs.forEach(s -> System.out.println(s.getName()));


    }
}