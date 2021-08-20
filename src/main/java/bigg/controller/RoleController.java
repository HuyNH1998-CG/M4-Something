package bigg.controller;

import bigg.model.Role;
import bigg.services.IRoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {
    @Autowired
    private IRoleServices roleServices;

    @GetMapping("/role")
    public ModelAndView show() {
        Iterable<Role> roles = roleServices.findAll();
        ModelAndView modelAndView = new ModelAndView("/role/list");
        modelAndView.addObject("roles",roles);
        return modelAndView;
    }

    @GetMapping("/role/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/role/create");
        modelAndView.addObject("role",new Role());
        return modelAndView;
    }

    @PostMapping("/role/create")
    public ModelAndView create(@ModelAttribute Role role) {
        roleServices.save(role);
        return new ModelAndView("redirect:/role");
    }

    @GetMapping("/role/edit/{id}")
    public ModelAndView showEdit(@PathVariable long id){
        Role role = roleServices.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/role/edit");
        modelAndView.addObject("role",role);
        return modelAndView;
    }

    @PostMapping("/role/edit/{id}")
    public ModelAndView edit(@ModelAttribute Role role){
        roleServices.save(role);
        return new ModelAndView("redirect:/role");
    }

    @GetMapping("/role/delete/{id}")
    public ModelAndView delete(@PathVariable long id){
        roleServices.remove(id);
        return new ModelAndView("redirect:/role");
    }
}

