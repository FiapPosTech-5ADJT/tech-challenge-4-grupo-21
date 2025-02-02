package br.com.fiap.productcatalog.infraestructure.controller;

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


public class ProductController {

}
