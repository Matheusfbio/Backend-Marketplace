package com.mfdesenvolvimento.marketplace.employee;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeControler {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@GetMapping("/")
	public ResponseEntity<Object> getAllEmployee() {
		var employee = this.employeeRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PostMapping("/")
	public ResponseEntity<Object> createUser(@RequestBody EmployeeModel employeeModel) {
		var user = this.employeeRepository.findByUsername(employeeModel.getUsername());
		if (user != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exists");
		}

		var passwordHashered = BCrypt.withDefaults().hashToString(12, employeeModel.getPassword().toCharArray());

		employeeModel.setPassword(passwordHashered);

		var userCreated = this.employeeRepository.save(employeeModel);
		return ResponseEntity.status(HttpStatus.OK).body(userCreated);
	}

	@PutMapping("/{id}")
	public EmployeeModel update(@RequestBody EmployeeModel employeeModel, @PathVariable UUID id,
			HttpServletRequest request) {
		var employee = this.employeeRepository.findById(id).get();
		employee.setUsername(employeeModel.getUsername());
		employee.setName(employeeModel.getName());
		employee.setPassword(employeeModel.getPassword());
		return employeeRepository.save(employee);
	}

	@DeleteMapping("/{id}")
	public String delete(@RequestBody EmployeeModel employeeModel, @PathVariable UUID id, HttpServletRequest request) {
		try {
			employeeRepository.deleteById(id);
			return "employee deleted successfully";
		} catch (Exception e) {
			return "employee not found";
		}
	}
}