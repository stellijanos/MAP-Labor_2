package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.repositories.CategoryRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping("/create")
    public @ResponseBody Response create(
            @RequestParam(name = "token") String token,
            @RequestBody Category category
    ) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return Response.INVALID_TOKEN;
        categoryRepository.save(category);
        return Response.CATEGORY_CREATE_SUCCESSFUL;
    }

    @GetMapping("/{id}")
    public @ResponseBody Category read(@RequestParam String token, @PathVariable Integer id) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return new Category();
        return categoryRepository.findById(id).orElse(new Category());
    }

    @GetMapping
    public @ResponseBody Iterable<Category> readAll(@RequestParam String token) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return new ArrayList<>();
        return categoryRepository.findAll();
    }

    @PutMapping("/{id}")
    public @ResponseBody Response update(@RequestParam String token, @RequestBody Category category) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return Response.INVALID_TOKEN;
        Category current = categoryRepository.findById(category.getId()).orElse(new Category());
        if (current.getName() == null) {
            return Response.CATEGORY_NOT_FOUND;
        }
        categoryRepository.save(category);
        return Response.CATEGORY_UPDATE_SUCCESSFUL;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Response delete(@RequestParam String token, @RequestBody Category category) {
        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
            return Response.INVALID_TOKEN;
        categoryRepository.delete(category);
        return Response.CATEGORY_DELETE_SUCCESSFUL;
    }
}
