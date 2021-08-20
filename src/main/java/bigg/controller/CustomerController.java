package bigg.controller;

import bigg.model.Customer;
import bigg.services.ICustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerServices customerServices;

    @GetMapping("/home")
    public ModelAndView home(@RequestParam Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("/customer/home");
        Iterable<Customer> customers;
        if (search.isPresent()) {
            customers = customerServices.findByName(search.get());
        } else {
            customers = customerServices.findAll();
        }
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute Customer customer) {
        customerServices.save(customer);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable long id) {
        Customer customer = customerServices.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCustomer(@ModelAttribute Customer customer) {
        customerServices.save(customer);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable long id) {
        customerServices.delete(id);
        return new ModelAndView("redirect:/home");
    }

}
