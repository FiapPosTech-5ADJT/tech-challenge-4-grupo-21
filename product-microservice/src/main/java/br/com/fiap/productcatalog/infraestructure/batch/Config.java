package br.com.fiap.productcatalog.infraestructure.batch;

import br.com.fiap.productcatalog.domain.service.CategoryService;
import br.com.fiap.productcatalog.infraestructure.api.dto.ProductCSVDTO;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.ProductJPAEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Value("${batch.directory}")
    private String directory;
    @Value("${batch.file_name}")
    private String fileName;

    @Bean
    public Job productBatchImportJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("productBatchImport", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step).build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      ItemReader<ProductCSVDTO> itemReader,
                      ItemWriter<ProductJPAEntity> itemWriter,
                      ItemProcessor<ProductCSVDTO, ProductJPAEntity> itemProcessor) {
        return new StepBuilder("step1", jobRepository)
                .<ProductCSVDTO, ProductJPAEntity>chunk(20, transactionManager)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemReader<ProductCSVDTO> itemReader() {
        BeanWrapperFieldSetMapper<ProductCSVDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ProductCSVDTO.class);
        return new FlatFileItemReaderBuilder<ProductCSVDTO>()
                .name("ProductCSVDTOItemReader")
                .resource(new FileSystemResource(directory + "/" + fileName))
                .delimited()
                .names("name", "description", "price", "categoryName", "quantity")
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean
    public ItemWriter<ProductJPAEntity> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ProductJPAEntity>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("INSERT INTO Product (name, description, price, category_id, stock) " +
                        "VALUES (:name, :description, :price, :category.id, :stock)")
                .build();
    }

    @Bean
    public ItemProcessor<ProductCSVDTO, ProductJPAEntity> itemProcessor(CategoryService categoryService, CategoryEntityConverter categoryEntityConverter) {
        return new ProductProcessor(categoryService, categoryEntityConverter);
    }
}
