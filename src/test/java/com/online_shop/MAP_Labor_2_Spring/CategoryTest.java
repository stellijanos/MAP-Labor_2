package com.online_shop.MAP_Labor_2_Spring;

import com.online_shop.MAP_Labor_2_Spring.controllers.CategoryController;
import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.repositories.CategoryRepository;
import com.online_shop.MAP_Labor_2_Spring.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CategoryTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAllCategories() {
        List<Category> categories = new ArrayList<>();

        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Electronics");

        Category c2 = new Category();
        c1.setId(2L);
        c1.setName("Toys");


        categories.add(c1);
        categories.add(c2);

        ResponseEntity<Iterable<Category>> finalCategories = ResponseEntity.ok(categories);

        when(categoryService.getAllCategories()).thenReturn(finalCategories);

        List<Category> result = (List<Category>) categoryController.getAllCategories().getBody();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(c1.getId(), categories.get(0).getId());
        assertEquals(c1.getName(), categories.get(0).getName());

        assertEquals(c2.getId(), categories.get(1).getId());
        assertEquals(c2.getName(), categories.get(1).getName());

    }
}
