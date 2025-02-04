package br.com.fiap.productcatalog.infraestructure.api.controller;

import br.com.fiap.productcatalog.application.dto.AddProductStockRequest;
import br.com.fiap.productcatalog.application.dto.RemoveProductStockRequest;
import br.com.fiap.productcatalog.application.usecases.AddProductStockUseCase;
import br.com.fiap.productcatalog.application.usecases.GetProductStockUseCase;
import br.com.fiap.productcatalog.application.usecases.GetProductsUseCase;
import br.com.fiap.productcatalog.application.usecases.RemoveProductStockUseCase;
import br.com.fiap.productcatalog.domain.entity.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final AddProductStockUseCase addProductStockUseCase;
    private final GetProductStockUseCase getProductStockUseCase;
    private final GetProductsUseCase getProductsUseCase;
    private final RemoveProductStockUseCase removeProductStockUseCase;

    @PostMapping("/addStock")
    public ResponseEntity<Void> addProductStock(@RequestBody @Valid AddProductStockRequest request) {
        addProductStockUseCase.addProductStock(request.productId(),
                request.quantity());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/removeStock")
    public ResponseEntity<Void> removeProductStock(@RequestBody @Valid RemoveProductStockRequest request) {
        removeProductStockUseCase.removeProductStock(request.productId(),
                request.quantity());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}/stock")
    public ResponseEntity<BigDecimal> getProductStock(@PathVariable Long productId) {
        return ResponseEntity.ok(getProductStockUseCase.getProductStock(productId));
    }


    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(getProductsUseCase.getProducts());
    }
}
