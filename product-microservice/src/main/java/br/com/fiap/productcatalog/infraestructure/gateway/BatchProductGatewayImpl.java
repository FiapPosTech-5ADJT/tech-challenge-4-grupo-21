package br.com.fiap.productcatalog.infraestructure.gateway;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;
import br.com.fiap.productcatalog.domain.exception.FileSaveException;
import br.com.fiap.productcatalog.domain.exception.JobLauncherException;
import br.com.fiap.productcatalog.domain.gateway.BatchProductGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

@Slf4j
@Component
public class BatchProductGatewayImpl implements BatchProductGateway {

    @Value("${batch.directory}")
    private String directory;
    @Value("${batch.file_name}")
    private String fileName;

    private final JobLauncher jobLauncher;
    private final Job productBatchImportJob;
    private final ThreadPoolTaskScheduler taskScheduler;

    public BatchProductGatewayImpl(JobLauncher jobLauncher,
                                   @Qualifier("productBatchImportJob") Job productBatchImportJob) {
        this.jobLauncher = jobLauncher;
        this.productBatchImportJob = productBatchImportJob;
        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.initialize();
    }

    @Override
    public void execute(BatchProductRequest batchProductRequest) {
        try{
            saveFile(batchProductRequest.binary());
            Runnable jobTask = () -> {
                try {
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                            .toJobParameters();
                    jobLauncher.run(productBatchImportJob, jobParameters);
                    log.info("Job executado com sucesso!");
                } catch (Exception e) {
                    log.error("Erro ao executar job: {}", e.getMessage());
                    throw new JobLauncherException("Erro ao executar job", e.getMessage());
                }
            };

            if (batchProductRequest.miliseconds() > 0) {
                Instant scheduleTime = Instant.now().plusMillis(batchProductRequest.miliseconds());
                taskScheduler.schedule(jobTask, scheduleTime);
                log.info("Job scheduled to be executed in: {}", scheduleTime);
            } else {
                jobTask.run();
            }
        } catch (Exception e) {
            log.error("Error executing job: {}", e.getMessage());
            throw new JobLauncherException("Error executing job", e.getMessage());
        }
    }

    private void saveFile(final byte[] file) {
        try {
            File uploadDir = new File(directory);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            Path filePath = Path.of(directory, fileName);
            try (InputStream inputStream = new ByteArrayInputStream(file)) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            log.debug("Arquivo salvo em: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            log.error("Error saving file: {}", e.getMessage());
            throw new FileSaveException("Error saving file", e.getMessage());
        }



    }
}
