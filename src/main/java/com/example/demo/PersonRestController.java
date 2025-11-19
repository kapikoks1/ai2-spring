package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

   
    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    
    @GetMapping("/{index}")
    public ResponseEntity<Person> getPerson(@PathVariable int index) {
        Person person = personService.getPerson(index);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(person);
    }

    
    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    
    @PutMapping("/{index}")
    public ResponseEntity<Person> updatePerson(@PathVariable int index, @RequestBody Person updatedPerson) {
        Person existing = personService.getPerson(index);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        personService.setPerson(index, updatedPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    
    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deletePerson(@PathVariable int index) {
        Person existing = personService.getPerson(index);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        personService.removePerson(index);
        return ResponseEntity.noContent().build();
    }
}
