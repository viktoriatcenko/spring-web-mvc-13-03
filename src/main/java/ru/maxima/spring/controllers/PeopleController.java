package ru.maxima.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.spring.dao.PersonDAO;
import ru.maxima.spring.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String getPeople(Model model) {
        model.addAttribute("allPeople", personDAO.getAllPeople());
        return "people/get-all-people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable Long id,  Model model) {
        model.addAttribute("onePersonById", personDAO.getPersonById(id));
        return "people/get-person";
    }

    @GetMapping("/new")
    public String getFormToCreateNewPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "people/new-person";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new-person";
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getFormToUpdateCurrentPerson(@PathVariable("id") Long id ,Model model) {
        model.addAttribute("editPerson", personDAO.getPersonById(id));
        return "people/edit-person";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") Long id , @ModelAttribute("editPerson") @Valid Person person,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/edit-person";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
