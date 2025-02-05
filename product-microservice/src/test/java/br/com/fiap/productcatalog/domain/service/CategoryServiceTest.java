package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito para injetar os mocks
public class CategoryServiceTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setup() {
        category = new Category(1L, "Eletrônicos");
    }

    @Test
    void getCategories_ShouldReturnListOfCategories() {
        List<Category> mockCategories = TestDataFactory.createCategoryList();
        when(categoryGateway.findAll()).thenReturn(mockCategories);

        List<Category> result = categoryService.getCategories();

        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        verify(categoryGateway, times(1)).findAll();
    }

    @Test
    void save_ShouldReturnSavedCategory() {
        when(categoryGateway.save(category)).thenReturn(category);

        Category result = categoryService.save(category);

        assertNotNull(result);
        assertEquals("Eletrônicos", result.getName());
        verify(categoryGateway, times(1)).save(category);
    }

    @Test
    void findByName_ShouldReturnCategory_WhenExists() {
        when(categoryGateway.findByName("Eletrônicos")).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.findByName("Eletrônicos");

        assertTrue(result.isPresent());
        assertEquals("Eletrônicos", result.get().getName());
        verify(categoryGateway, times(1)).findByName("Eletrônicos");
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenNotExists() {
        when(categoryGateway.findByName("Móveis")).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.findByName("Móveis");

        assertFalse(result.isPresent());
        verify(categoryGateway, times(1)).findByName("Móveis");
    }
}
