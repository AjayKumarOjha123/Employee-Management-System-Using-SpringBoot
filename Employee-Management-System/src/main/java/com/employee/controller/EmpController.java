package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepo;
@Controller
public class EmpController {

	@Autowired
	private EmployeeRepo employeerepo;

	@GetMapping("/")
	public String listEmployee(Model model) {
		List<Employee> emp = employeerepo.findAll();
		model.addAttribute("Employee", emp);
		return "index";
	}

	@GetMapping("/addEmployee")
	public String addNewEmployeePage(Model model) {
		model.addAttribute("Employee", new Employee());
		return "addnewemployee";
	}
	
	@PostMapping("/addEmployee")
	public String addNewEmployee(@ModelAttribute("Employee") Employee e) {
		employeerepo.save(e);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/deleteEmployee")
	public String deleteEmployee(@PathVariable int id) {
		employeerepo.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/editEmployee")
	public String editEmployee(@PathVariable int id, Model model) {
		Employee emp = employeerepo.findById(id).get();
		model.addAttribute("Employee", emp);
		return "addnewemployee";
	}
}
