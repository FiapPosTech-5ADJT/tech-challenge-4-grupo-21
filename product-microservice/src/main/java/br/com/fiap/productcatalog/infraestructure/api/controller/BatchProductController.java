package br.com.fiap.productcatalog.infraestructure.api.controller;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;
import br.com.fiap.productcatalog.application.usecases.BatchProductImportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;

import java.io.IOException;

@RestController
@RequestMapping("/batch")
@AllArgsConstructor
@Tag(name = "Batch Product Import", description = "Importação de produtos em lote")
public class BatchProductController {

    private final BatchProductImportUseCase batchProductImportUseCase;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> importBatch(
            @RequestPart("file") MultipartFile file, // 🔄 Voltando para @RequestPart
            @RequestParam(value = "milliseconds", required = false) Long milliseconds
    ) throws IOException {
        BatchProductRequest request = new BatchProductRequest(file.getBytes(), milliseconds != null ? milliseconds : 0);
        batchProductImportUseCase.execute(request);
        return ResponseEntity.ok().build();
    }
}
