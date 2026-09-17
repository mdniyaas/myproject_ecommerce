package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Product;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("products",
                productRepository.findAll());

        return "index";
    }

@GetMapping("/products")
public String products(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String sort,
        Model model) {

    List<Product> products;

    // SEARCH
    if (keyword != null && !keyword.isEmpty()) {
        products = productRepository.findByNameContaining(keyword);
    } else {
        products = productRepository.findAll();
    }

    // SORT FEATURE
    if ("low".equals(sort)) {
        products.sort(Comparator.comparing(Product::getPrice));
    } else if ("high".equals(sort)) {
        products.sort(Comparator.comparing(Product::getPrice).reversed());
    } else if ("name".equals(sort)) {
        products.sort(Comparator.comparing(Product::getName));
    }

    model.addAttribute("products", products);
    return "products";
}

    @GetMapping("/employees")
    public String employees(Model model) {

        model.addAttribute("employees",
                employeeRepository.findAll());

        return "employees";
    }


    @GetMapping("/add-product")
    public String addProductPage(Model model) {

        model.addAttribute("product",
                new Product());

        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(
            @ModelAttribute Product product) {

        productRepository.save(product);

        return "redirect:/products";
    }

    @GetMapping("/add-employee")
    public String addEmployeePage(Model model) {

        model.addAttribute("employee",
                new Employee());

        return "add-employee";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(
            @ModelAttribute Employee employee) {

        employeeRepository.save(employee);

        return "redirect:/employees";
    }

    // @GetMapping("/cart")
    // public String cart() {
    //     return "cart";
    // }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productRepository.deleteById(id);

        return "redirect:/products";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(
            @PathVariable Long id,
            Model model) {

        Product product = productRepository.findById(id).orElse(null);

        model.addAttribute("product", product);

        return "edit-product";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword,
            Model model) {

        model.addAttribute("products",
                productRepository
                        .findByNameContaining(keyword));

        return "products";
    }

    @GetMapping("/category/{name}")
    public String categoryProducts(@PathVariable String name,
            Model model) {

        model.addAttribute("products",
                productRepository.findByCategory(name));

        return "products";
    }

    // @GetMapping("/dashboard")
    // public String dashboard(Model model) {

    //     model.addAttribute("productCount",
    //             productRepository.count());

    //     model.addAttribute("employeeCount",
    //             employeeRepository.count());

    //     return "dashboard";
    // }

    // @GetMapping("/user-dashboard")
    // public String userDashboard() {

    //     return "user-dashboard";

    // }
@GetMapping("/edit-employee/{id}")
public String editEmployee(@PathVariable Long id, Model model) {

    Employee employee = employeeRepository.findById(id).orElse(null);

    model.addAttribute("employee", employee);

    return "edit-employee";
}
@GetMapping("/delete-employee/{id}")
public String deleteEmployee(@PathVariable Long id){

    employeeRepository.deleteById(id);

    return "redirect:/employees";
}
@PostMapping("/update-employee/{id}")
public String updateEmployee(@PathVariable Long id,
                             @ModelAttribute Employee employee) {

    Employee existingEmployee =
            employeeRepository.findById(id)
            .orElseThrow();

    existingEmployee.setName(employee.getName());
    existingEmployee.setRole(employee.getRole());
    existingEmployee.setSalary(employee.getSalary());
    existingEmployee.setAttendance(employee.getAttendance());

    employeeRepository.save(existingEmployee);

    return "redirect:/employees";
}
    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }

    // @GetMapping("/checkout")
    // public String checkout() {
    //     return "checkout";
    // }

    @PostMapping("/update-product")
    public String updateProduct(
            @ModelAttribute Product product) {

        productRepository.save(product);

        return "redirect:/products";
    }

}
