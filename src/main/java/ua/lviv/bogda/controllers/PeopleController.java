package ua.lviv.bogda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.bogda.dao.CitiesDAO;
import ua.lviv.bogda.dao.PersonDAO;
import ua.lviv.bogda.models.City;
import ua.lviv.bogda.models.Person;
import ua.lviv.bogda.util.PersonValidator;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final CitiesDAO citiesDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, CitiesDAO citiesDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.citiesDAO = citiesDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("city") City city) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));

        Optional<City> location = personDAO.getLocation(id);

        if (location.isPresent())
            model.addAttribute("location", location.get());
        else
            model.addAttribute("cities", citiesDAO.index());

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        personDAO.release(id);
        return "redirect:/people/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String release(@PathVariable("id") int id, @ModelAttribute("city") City selectedCity) {
        personDAO.assign(id, selectedCity);
        return "redirect:/people/" + id;
    }
}
