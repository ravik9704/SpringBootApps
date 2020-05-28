package com.example.Sample;

import com.data.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
@RestController
public class SampleApplication extends SpringBootServletInitializer {

	private static Map<String,Employee> employeeList = new HashMap<String, Employee>();

	static int empid = 123456;
int count=1;

	static int instanceCounter = 0;

	static int counter = 0;
	private static String getEmployeNumber(){
		instanceCounter++;

		counter = counter + 1;
		String number =  Integer.toString(counter);
		return number;
	}
	static {

		Employee employee1 = new Employee();
		employee1.setName("Ravikumar Lakidi");
		employee1.setEmpId(getEmployeNumber());
		employee1.setLocation("Manchester");
		employee1.setPostCode("M6 7QD");
		employee1.setMobileNumber("9704999854");

		Employee employee2 = new Employee();
		employee2.setName("Mahammod Ghouse");
		employee2.setEmpId(getEmployeNumber());
		employee2.setLocation("Banglore");
		employee2.setPostCode("411033");
		employee2.setMobileNumber("9701389360");

		Employee employee3 = new Employee();
		employee3.setName("Krishna Mohan");
		employee3.setEmpId(getEmployeNumber());
		employee3.setLocation("Hyderabad");
		employee3.setPostCode("500082");
		employee3.setMobileNumber("9762515919");

		Employee employee4 = new Employee();
		employee4.setName("Babu C");
		employee4.setEmpId(getEmployeNumber());
		employee4.setLocation("New york");
		employee4.setPostCode("BA 789");
		employee4.setMobileNumber("9562570890");
		employeeList.put(employee1.getEmpId(),employee1);
		employeeList.put(employee2.getEmpId(),employee2);
		employeeList.put(employee3.getEmpId(), employee3);
		employeeList.put(employee4.getEmpId(),employee4);

	}
	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SampleApplication.class);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getEmployeData/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployeData(@PathVariable("id") String id) {
		Employee e = null;
		if(null != employeeList && null != id){
			e = employeeList.get(id);
		}
		ResponseEntity responseEntity = new ResponseEntity(e, HttpStatus.OK);
		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAllEmployeData", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllEmployeeData() {

		List<Employee> data = new ArrayList<Employee>();

		for (Map.Entry<String, Employee>  entry: employeeList.entrySet()){
			data.add(entry.getValue());
		}

		ResponseEntity responseEntity = new ResponseEntity(data, HttpStatus.OK);
		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/createEmploy", method = RequestMethod.POST)
	public ResponseEntity<Object> createEmploye(@RequestBody Employee employee) {
		String updateStatus = "Not Updated";
		if(null != employeeList && null !=employee){
			employee.setEmpId(getEmployeNumber());
			employeeList.put(getEmployeNumber(), employee);
			updateStatus = "Employee is created successsfully";
		}
		return new ResponseEntity<>(updateStatus, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/UpdateEmploye", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateEmployeData(@RequestBody Employee employee) {
		String updateStatus = "Not Updated";
		if(null != employeeList && null !=employee){
			employeeList.put(employee.getEmpId(), employee);
			updateStatus = "Employee is updated successsfully";
		}
		return new ResponseEntity<>(updateStatus, HttpStatus.NO_CONTENT);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteEmployeData(@PathVariable("id") String id) {
		String updateStatus = "Not Updated";
		if(null != employeeList && null !=id){
			employeeList.remove(id);
			Employee e= employeeList.get(id);
			if(e==null){
				updateStatus = "Employee is Deleted successsfully";
			}
		}
		return new ResponseEntity<>(updateStatus, HttpStatus.NO_CONTENT);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/singleEmployee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSingleEmployeData(@PathVariable("id") String id) {
		Employee e = null;
		if(null != employeeList && null !=id){
			 e = employeeList.get(id);
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

}
