package com.justayar.springboot.util.matchoperators;

import java.util.List;

public class StudentSearch {

    public boolean checkThereIsAnyStudentWhoIsInComputerEngineeringAndGpaIsOverThanThreeWithStream(List<Student> studentList){
        long startTime = System.currentTimeMillis();

        boolean check = studentList.stream().anyMatch(student ->
                student.getMajor().equalsIgnoreCase("Computer Engineering") &&
                        student.getGpa() > 3.0);

        long endTime = System.currentTimeMillis();
        System.out.println("\n Any Match Total time(With Stream Match): "+(endTime-startTime)+" miliseconds\n");

        return check;
    }

    public boolean checkThereIsAnyStudentWhoIsInComputerEngineeringAndGpaIsOverThanThreeWithSForLoop(List<Student> studentList){
        long startTime = System.currentTimeMillis();

        for(Student student:studentList){
            if(student.getMajor().equalsIgnoreCase("Computer Engineering") &&
                    student.getGpa() > 3.0){
                long endTime = System.currentTimeMillis();
                System.out.println("\n Any Match Total time(With For Loop): " + (endTime - startTime) + " miliseconds\n");
                return true;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n Any Match Total time(With For Loop): "+(endTime-startTime)+" miliseconds\n");

        return false;
    }

    public boolean checkAllStudentsHasGpaOverThanThreeWithStream(List<Student> studentList){

        long startTime = System.currentTimeMillis();

        boolean check = studentList.stream().allMatch(student -> student.getGpa() > 3.0);

        long endTime = System.currentTimeMillis();
        System.out.println("\n All Match Total time(With Stream Match): "+(endTime-startTime)+" miliseconds\n");

        return check;

    }

    public boolean checkAllStudentsHasGpaOverThanThreeWithForLoop(List<Student> studentList){

        long startTime = System.currentTimeMillis();

        for(Student student:studentList){
            if(student.getGpa() <= 3.0){
                long endTime = System.currentTimeMillis();
                System.out.println("\n All Match Total time(With For Loop): " + (endTime - startTime) + " miliseconds\n");
                return false;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n All Match Total time(With For Loop): "+(endTime-startTime)+" miliseconds\n");

        return true;

    }

    public boolean checkAllStudentsHasGpaBelowThanTwoWithStream(List<Student> studentList){
        long startTime = System.currentTimeMillis();

        boolean check = studentList.stream().noneMatch(student -> student.getGpa() < 2.0);

        long endTime = System.currentTimeMillis();
        System.out.println("\n None Match Total time(With Stream Match): "+(endTime-startTime)+" miliseconds\n");

        return check;
    }

    public boolean checkAllStudentsHasGpaBelowThanTwoWithForLoop(List<Student> studentList){

        long startTime = System.currentTimeMillis();

        for(Student student:studentList){
            if(student.getGpa() < 2.0){
                long endTime = System.currentTimeMillis();
                System.out.println("\n None Match Total time(With For Loop): " + (endTime - startTime) + " miliseconds\n");
                return false;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n None Match Total time(With For Loop): "+(endTime-startTime)+" miliseconds\n");

        return true;
    }

    public List<Student> mockStudents(){

        Student student1 = Student.builder().id(1).major("Computer Engineering").gpa(3.1).name("John Doe").build();
        Student student2 = Student.builder().id(2).major("Mechanical Engineering").gpa(2.5).name("Taylor Swift").build();
        Student student3 = Student.builder().id(3).major("Computer Engineering").gpa(2.9).name("Kevin Armstrong").build();
        Student student4 = Student.builder().id(4).major("Computer Engineering").gpa(1.8).name("Mike Tyson").build();
        Student student5 = Student.builder().id(5).major("Computer Engineering").gpa(3.5).name("Phill Neville").build();
        Student student6 = Student.builder().id(6).major("Industrial Engineering").gpa(1.7).name("Kylie Jenner").build();

        return List.of(student1,student2,student3,student4,student5,student6);
    }

}
