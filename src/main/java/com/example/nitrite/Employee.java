package com.example.nitrite;


import org.dizitart.no2.objects.Id;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String empId;
    public String address;

    public Employee(){}

    public Employee(String empId, String address) {
        this.empId = empId;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
