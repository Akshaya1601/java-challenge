package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeModel;
import com.challenge.api.request.CreateEmployeeRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * Service responsible for employee retrieval and creation.
 *
 * Employees are stored in memory because a persistence layer is outside the scope of this challenge.
 */
@Service
public class EmployeeService {

    // Store employees in memory using their UUID as the key.
    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    public EmployeeService() {
        // Create mock employee data for the in-memory store.
        Employee employee = new EmployeeModel();

        employee.setUuid(UUID.randomUUID());
        employee.setFirstName("Test");
        employee.setLastName("Employee");
        employee.setFullName("Test Employee");
        employee.setSalary(100000);
        employee.setAge(27);
        employee.setJobTitle("ReliaQuest SWE Intern");
        employee.setEmail("testemployee@example.com");

        employees.put(employee.getUuid(), employee);
    }

    /**
     * Returns all employees currently stored by the service.
     *
     * @return list of employees
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    /**
     * Retrieves an employee by UUID.
     *
     * @param uuid unique identifier of the employee
     * @return employee associated with the UUID
     * @throws NoSuchElementException if no employee exists with the provided UUID
     */
    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employees.get(uuid);

        if (employee == null) {
            throw new NoSuchElementException("Employee with UUID " + uuid + " not found");
        }

        return employee;
    }

    /**
     * Creates and stores a new employee from the supplied request.
     *
     * @param request employee creation data
     * @return newly created employee
     */
    public Employee createEmployee(CreateEmployeeRequest request) {
        Employee employee = new EmployeeModel();

        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(request.getFirstName() + " " + request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(request.getContractHireDate());

        employees.put(employee.getUuid(), employee);

        return employee;
    }
}
