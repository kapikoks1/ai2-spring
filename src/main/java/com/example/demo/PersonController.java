package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "people"; 
    }

    @GetMapping("/people/{index}")
    public String getPerson(@PathVariable int index, Model model) {
        model.addAttribute("person", personService.getPerson(index));
        model.addAttribute("index", index);
        return "person"; 
    }

    @GetMapping("/people/add")
public String addPersonForm(Model model) {
    model.addAttribute("person", new Person());
    return "add-person"; 
}

@PostMapping("/people/add")
public String addPerson(Person person) {
    personService.addPerson(person);
    return "redirect:/people"; 
}

}
