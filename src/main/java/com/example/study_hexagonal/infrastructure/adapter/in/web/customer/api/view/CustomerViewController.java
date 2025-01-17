package com.example.study_hexagonal.infrastructure.adapter.in.web.customer.api.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerViewController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerViewController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @GetMapping("/new")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customerInfo", new CustomerInfo());
        return "customer/create";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute CustomerInfo customerInfo) {
        createCustomerUseCase.createCustomer(customerInfo);
        return "redirect:/customers";
    }

    @GetMapping("/{customerId}")
    public String showCustomerDetails(@PathVariable String customerId, Model model) {
        Customer customer = getCustomerUseCase.getCustomer(customerId);
        model.addAttribute("customer", customer);
        return "customer/details";
    }
}