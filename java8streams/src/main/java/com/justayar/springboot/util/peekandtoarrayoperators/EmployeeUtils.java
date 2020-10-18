package com.justayar.springboot.util.peekandtoarrayoperators;

import java.util.List;

public class EmployeeUtils {

    public void filterEmployeesAboveThirtyWithStream(List<Employee> employeeList){

        long startTime = System.currentTimeMillis();

        Employee[] employees = employeeList.stream().filter(employee -> employee.getAge() > 30).
                peek(e -> System.out.print("Age: " + e.getAge())).
                toArray(Employee[]::new);

        long endTime = System.currentTimeMillis();
        System.out.println("\n Total time(With Stream Peek And ToArray): "+(endTime-startTime)+" miliseconds\n");
    }

    public List<Employee> mockEmployeeList(){

        Employee employee1 = Employee.builder().id(1).age(20).salary(2000).build();
        Employee employee2 = Employee.builder().id(2).age(33).salary(5000).build();
        Employee employee3 = Employee.builder().id(3).age(25).salary(2500).build();
        Employee employee4 = Employee.builder().id(4).age(40).salary(7000).build();
        Employee employee5 = Employee.builder().id(5).age(19).salary(2000).build();
        Employee employee6 = Employee.builder().id(6).age(30).salary(3500).build();

        return List.of(employee1,employee2,employee3,employee4,employee5,employee6);
    }
}
