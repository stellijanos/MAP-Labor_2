package com.online_shop.MAP_Labor_2_Spring;

import com.online_shop.MAP_Labor_2_Spring.controllers.CategoryController;
import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAllClients() {
        List<Category> users = new ArrayList<>();

        Category c1 = new Category();
        c1.setId(1l);
        c1.setName("Electronics");

        Category c2 = new Category();
        c1.setId(2l);
        c1.setName("Toys");


        users.add(c1);
        users.add(c2);

        when(categoryRepository.findAll()).thenReturn(users);

        List<Category> result = categoryController.getAllCategories();

        assert result != null;
        assertEquals(2, result.size());

        assertEquals(c1.getId(), users.get(0).getId());
        assertEquals(c1.getName(), users.get(0).getName());

        assertEquals(c2.getId(), users.get(1).getId());
        assertEquals(c2.getName(), users.get(1).getName());

    }
}


