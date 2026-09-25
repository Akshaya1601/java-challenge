package com.challenge.api.service

import com.challenge.api.request.CreateEmployeeRequest
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeModel;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {
    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    public EmployeeService() {
        // Mock data
        Employee employee = new EmployeeModel();

        employee.setUuid(UUID.randomUUID());
        employee.setFirstName("Test");
        employee.setLastName("Employee");
        employee.setSalary(100000);
        employee.setAge(27);
        employee.setJobTitle("ReliaQuest SWE Intern");
        employee.setEmail("testemployee@example.com");

        employees.put(employee.getUuid(), employee)
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        if (!employees) {
            throw new NoSuchElementException(
                    "Employee with UUID: " + uuid + " not found!"
                    );
        }
        return employees.keys().stream()
            .filter(id -> id == uuid)
        
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        UUID uuid = UUID.randomUUID();

        EmployeeModel employee = new EmployeeModel();

        employee.setUuid(uuid);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(request.getFirstName() + " " + request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(request.getContractHireDate());

        employees.put(uuid, employee);

        return employee;
    }
}