package Students;

import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private Map<String, Integer> grades;

    public Student(String name, String group, int course) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new HashMap<>();
    }

    public void addGrades(String subject, int grade) {
        grades.put(subject, grade);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int grade : grades.values()) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public void promoteToNextCourse() {
        if (course < 5) {
            course++;
        }
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public Map<String, Integer> getGrades() {
        return new HashMap<>(grades);
    }

    @Override
    public String toString() {
        return String.format("Студенты: %s, Группа: %s, Курс: %d, Средний балл: %.2f", name, group, course, getAverageGrade());
    }
}
