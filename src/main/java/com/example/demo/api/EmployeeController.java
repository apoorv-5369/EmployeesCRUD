package com.example.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Employee;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class EmployeeController {

    // Create a Map to store employee details
    private Map<Integer, Employee> employeeMap = new HashMap<>();

    // GET method to retrieve all employees
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeMap.values());
    }

    // GET method to retrieve employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeMap.get(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employee);
        }
    }

    // POST method to add new employee
    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            return ResponseEntity.badRequest().body("Employee with ID " + employee.getId() + " already exists");
        }
        employeeMap.put(employee.getId(), employee);
        return ResponseEntity.ok("Employee added successfully");
    }

    // PUT method to update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        if (!employeeMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id);
        employeeMap.put(id, employee);
        return ResponseEntity.ok("Employee updated successfully");
    }

    // DELETE method to delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        if (!employeeMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        employeeMap.remove(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
    // GET method to retrieve Employee ID by name
    @GetMapping("/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name){
        for(Employee employee:employeeMap.values()){
            String existingEmployee=employee.getName();
            if(existingEmployee.equals(name)){
                return ResponseEntity.ok(employee);
            }
        }
        return ResponseEntity.notFound().build();
    }


}
