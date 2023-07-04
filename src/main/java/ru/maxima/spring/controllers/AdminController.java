package ru.maxima.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.spring.dao.PersonDAO;
import ru.maxima.spring.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getForm(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("allPeople", personDAO.getAllPeople());
        return "people/adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        Person founded = personDAO.getPersonById(person.getId());
        System.out.println(founded.getName());
        System.out.println(founded.getAge());
        System.out.println(founded.getEmail());
        return "redirect:/people";
    }

}
