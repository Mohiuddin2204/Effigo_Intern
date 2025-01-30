package com.springboot.webapptodo.todo;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping("/list")
	public String listOfTodos(ModelMap model) {
		List<Todo> todos=todoService.findByUsername("Mohiuddin");
		model.addAttribute("todos",todos);
		return "listTodos";
	}
	
	@RequestMapping(value= "/addtodo",method=RequestMethod.GET)
	public String addTodos(ModelMap model) {
		String username=(String)model.get("name");
		Todo todo=new Todo(0,username,"",				
				LocalDate.now().plusWeeks(4), false);
		model.put("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value= "/addtodo",method=RequestMethod.POST)
	public String newTodos(ModelMap model,@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "addTodos";
		}
		String username=(String)model.get("name");
		todoService.addTodo(username, todo.getDescription(),
				todo.getTargetDate(), false);
		return "redirect:/list";
	}
	
	@RequestMapping("/deletetodo")
	public String deleteTodos(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/updatetodo",method=RequestMethod.GET)
	public String updateTodos(@RequestParam int id,ModelMap model) {
		Todo todo=todoService.findById(id);
		model.put("todo", todo);
		return "addTodos";
	}
	
	@RequestMapping(value= "/updatetodo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "addTodos";
		}
		String username=(String)model.get("name");
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:/list";
	}
}
