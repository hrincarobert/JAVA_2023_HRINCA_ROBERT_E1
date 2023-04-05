package org.example;
import java.util.*;
import java.util.stream.Collectors;


public class Student {

    private String name;
    private List<Project> preferences;

    public Student(String name, List<Project> preferences) {
        this.name = name;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public List<Project> getPreferences() {
        return preferences;
    }
}