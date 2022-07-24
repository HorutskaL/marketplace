package com.intellistart.marketplace.controller;

import com.intellistart.marketplace.model.Product;
import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.service.ProductService;
import com.intellistart.marketplace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final UserService userService;
    private final ProductService productService;

    public ProductController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("product", productService.findOne(id));

        User productOwner = productService.findOne(id);

        if (productOwner != null)
            model.addAttribute("owner", productOwner);
        else
            model.addAttribute("users", productService.findAll());
        return "products/show";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new")
    public String newUser(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String create(@ModelAttribute("user") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new";

        productService.save(product);
        return "redirect:/products";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productService.findOne(id));
        return "products/edit";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("products") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "products/edit";
        productService.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/users";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/buy")
    public String buy(@PathVariable("id") int id, @ModelAttribute("user") User buyer, @ModelAttribute("product") Product product) {
        productService.buy(id, buyer, product);
        return "redirect/products/" + id;
    }
}
