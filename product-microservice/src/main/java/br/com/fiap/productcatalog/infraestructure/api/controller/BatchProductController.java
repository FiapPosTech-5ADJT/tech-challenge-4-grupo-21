package br.com.fiap.productcatalog.infraestructure.api.controller;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;
import br.com.fiap.productcatalog.application.usecases.BatchProductImportUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/batch")
@AllArgsConstructor
public class BatchProductController {

    private BatchProductImportUseCase batchProductImportUseCase;

    @PostMapping("/import")
    public ResponseEntity<Void> importBatch(@RequestParam("file") MultipartFile file, @RequestParam("milliseconds") long milliseconds) throws IOException {
        BatchProductRequest request = new BatchProductRequest(file.getBytes(), milliseconds);
        batchProductImportUseCase.execute(request);
        return ResponseEntity.ok().build();
    }
}
