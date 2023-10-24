package ru.kudryashov.springCrudApp.controllers;


import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kudryashov.springCrudApp.DAO.PersonDAO;
import ru.kudryashov.springCrudApp.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
// Model отвечает за отображение на UI, через него идет передача данных из контроллера на представление(views)
    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDAO.getAllPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/personShow";
    }

    @GetMapping("/new")
    public String getFormForCreatePerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/form";
    }
//@ModelAttribute принимает параметры из запроса и записывает по ключу значения в Person
    @PostMapping()
    public String createPerson(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person,
                               @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
