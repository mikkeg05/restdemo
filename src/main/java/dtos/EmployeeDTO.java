/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class EmployeeDTO {
    private int id;
    private String name;
    private String address;
    private int Salary;

    public EmployeeDTO(Employee emp) {
        this.name = emp.getName();
        this.address = emp.getAddress();
        this.Salary = emp.getSalary();
    }

    public EmployeeDTO(String name, String address, int salary) {
        this.name = name;
        this.address = address;
        Salary = salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getSalary() {
        return Salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
