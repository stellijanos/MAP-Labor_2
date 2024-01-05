package com.online_shop.MAP_Labor_2_Spring;

import com.online_shop.MAP_Labor_2_Spring.controllers.CategoryController;
import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void test_createCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.createCategory(category)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(category));

        ResponseEntity<Category> response = categoryController.createCategory(category);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());

        Category createdCategory = response.getBody();
        assertNotNull(createdCategory);
        assertEquals(1L, createdCategory.getId());
        assertEquals("Electronics", createdCategory.getName());

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

    @Test
    void test_getCategory() {
        long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Electronics");

        when(categoryService.getCategory(categoryId)).thenReturn(ResponseEntity.ok(category));

        ResponseEntity<Category> response = categoryController.getCategory(categoryId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        Category fetchedCategory = response.getBody();
        assertNotNull(fetchedCategory);
        assertEquals(1L, fetchedCategory.getId());
        assertEquals("Electronics", fetchedCategory.getName());
    }

    @Test
    void test_updateCategory() {
        long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Electronics");

        when(categoryService.updateCategory(category)).thenReturn(ResponseEntity.ok(category));

        ResponseEntity<Category> response = categoryController.updateCategory(category);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        Category updatedCategory = response.getBody();
        assertNotNull(updatedCategory);
        assertEquals(1L, updatedCategory.getId());
        assertEquals("Electronics", updatedCategory.getName());
    }

    @Test
    void test_deleteCategory() {
        long categoryId = 1L;

        ResponseEntity<String> response = categoryController.deleteCategory(categoryId);
        assertNull(response);
    }
}
