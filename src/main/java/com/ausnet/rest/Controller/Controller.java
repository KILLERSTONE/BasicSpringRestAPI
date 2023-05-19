package com.ausnet.rest.Controller;

import com.ausnet.rest.Repo.PersonRepo;
import com.ausnet.rest.Service.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    public Controller(PersonRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/api/demo")
    public String weTrying(){
        return "WorkingTest";
    }

    private final PersonRepo repo;

//Retrieve all the Persons (ListALL)
    @GetMapping("/api/viewall")
    public List<Person> getall(){
        return repo.findAll();
    }

//Retrieve individual person by id (List active)
    @GetMapping("/api/view/{id}")
    public Person getPersonById(@PathVariable long id){
        Optional<Person> found=repo.findById(id);
        return found.orElse(null);
    }

//Create a person using a JSON request(ADD)
    @PostMapping("/api/create")
    public Person createPerson(@RequestBody Person pep){
        return repo.save(pep);
    }

//Editing pre exisiting person detail where they are identified by their unique id and the new updated data is stored in the json body(EDIT)
    @PutMapping("/api/edit/{id}")
    public Person editPerson(@PathVariable Long id,  @RequestBody Person newP ){
        Optional<Person> peps=repo.findById(id);
        if(peps.isPresent()) {
            Person p = peps.get();
            p.setName(newP.getName());
            p.setEmail(newP.getEmail());
            p.setPhno(newP.getPhno());
            return repo.save(p);
        }
        return null;
    }

//The person entry having this unique id is removed from the database along with a confirmation message (DELETE)
    @DeleteMapping("/api/remove/{id}")
    public String deletePerson(@PathVariable long id){
        Optional<Person> peps=repo.findById(id);
        if(peps.isPresent()){
            repo.delete(peps.get());
            return "Person with ID "+id+" is deleted sucessfully";
        }
        return "No person having given id found";
    }

//Activating the person
    @PostMapping("/api/activate/{id}")
    public String activate(@PathVariable long id){
        Optional<Person> peps=repo.findById(id);
        if(peps.isPresent()){
            Person per= (peps.get());
            per.setActive(true);
            return "Person with ID  "+id+ " is set active";
        }
        return "No person having given id found";
    }
    //Deactivating the person
    @PostMapping("/api/deactivate/{id}")
    public String deactivate(@PathVariable long id){
        Optional<Person> peps=repo.findById(id);
        if(peps.isPresent()){
            Person per= (peps.get());
            per.setActive(false);
            return "Person with ID  "+id+ " is set to not active";
        }
        return "No person having given id found";
    }

}
