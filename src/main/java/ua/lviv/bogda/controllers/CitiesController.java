package ua.lviv.bogda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lviv.bogda.dao.CitiesDAO;
import ua.lviv.bogda.models.City;

import javax.validation.Valid;


@Controller
@RequestMapping("/cities")
public class CitiesController {

    private final CitiesDAO citiesDAO;


    @Autowired
    public CitiesController(CitiesDAO citiesDAO) {
        this.citiesDAO = citiesDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cities", citiesDAO.index());
        return "cities/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("city", citiesDAO.show(id));
        model.addAttribute("people", citiesDAO.getCitizens(id));
        return "cities/show";
    }

    @GetMapping("/new")
    public String newCity(@ModelAttribute("city") City city) {
        return "cities/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("city") @Valid City city,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "cities/new";

        citiesDAO.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("city", citiesDAO.show(id));
        return "cities/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid City city, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "cities/edit";

        citiesDAO.update(id, city);
        return "redirect:/cities";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        citiesDAO.delete(id);
        return "redirect:/cities";
    }
}
