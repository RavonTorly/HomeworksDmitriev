import PhoneDirectory.PhoneDirectory;
import Students.Student;
import Students.StudentManager;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //Ex1
        {
            System.out.println("Задание 1:");
            Set<Student> students = new HashSet<>();

            Student student1 = new Student("Олег", "УГ-12", 1);
            student1.addGrades("Математика", 4);
            student1.addGrades("Литература", 3);
            student1.addGrades("Химия", 4);

            Student student2 = new Student("Виктор", "УГ-13", 1);
            student2.addGrades("Математика", 3);
            student2.addGrades("Литература", 3);
            student2.addGrades("Химия", 3);

            Student student3 = new Student("Ксения", "УГ-22", 2);
            student3.addGrades("Математика", 5);
            student3.addGrades("Литература", 5);
            student3.addGrades("Химия", 5);

            Student student4 = new Student("Анна", "УГ-33", 3);
            student4.addGrades("Математика", 4);
            student4.addGrades("Литература", 4);
            student4.addGrades("Химия", 4);

            Student student5 = new Student("Андрей", "УГ-12", 1);
            student5.addGrades("Математика", 2);
            student5.addGrades("Литература", 3);
            student5.addGrades("Химия", 2);

            students.add(student1);
            students.add(student2);
            students.add(student3);
            students.add(student4);
            students.add(student5);

            System.out.println("\nУдаление студентов со средним балом меньше 3:");
            StudentManager.removeUnderperformingStudents(students);

            System.out.println("\nПеревод на следующий курс:");
            StudentManager.promoteSuccessfulStudents(students);

            System.out.println("\nРезультаты:");
            for (Student student : students) {
                System.out.println(student);
            }

            System.out.println("\nСтуденты по курсам:");
            StudentManager.printStudents(students, 1);
            StudentManager.printStudents(students, 2);
            StudentManager.printStudents(students, 3);
            StudentManager.printStudents(students, 4);
        }
        //Ex2
        {
            System.out.println("\nЗадание 2:");
            PhoneDirectory phoneDirectorys = new PhoneDirectory();

            phoneDirectorys.addPhones("Ласточкин", "123-432-434");
            phoneDirectorys.addPhones("Дубков", "123-222-999");
            phoneDirectorys.addPhones("Ласточкин", "123-433-555");
            phoneDirectorys.addPhones("Петрухин", "123-777-777");

            System.out.println("Ласточкин: " + phoneDirectorys.get("Ласточкин"));
            System.out.println("Дубков: " + phoneDirectorys.get("Дубков"));
            System.out.println("Петрухин: " + phoneDirectorys.get("Петрухин"));
            System.out.println("Озерцов: " + phoneDirectorys.get("Озерцов"));
        }
    }
}