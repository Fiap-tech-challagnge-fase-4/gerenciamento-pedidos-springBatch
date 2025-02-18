package br.com.fiap.springBatch.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.fiap.springBatch.model.Produto;

@Configuration
public class SpringBatchConfiguration {

        @Bean
        public Job processarProdutos(JobRepository jobRepository, Step stepProdutos) {
                return new JobBuilder("processarProdutos", jobRepository)
                                .incrementer(new RunIdIncrementer())
                                .start(stepProdutos)
                                .build();
        }

        @Bean
        public Step stepProdutos(JobRepository jobRepository,
                        PlatformTransactionManager platformTransactionManager,
                        ItemReader<Produto> itemReaderProdutos,
                        ItemWriter<Produto> itemWriterProdutos
        ) {
                return new StepBuilder("stepProdutos", jobRepository)
                                .<Produto, Produto>chunk(24, platformTransactionManager)
                                .reader(itemReaderProdutos)
                                .writer(itemWriterProdutos)
                                .build();
        }

        @Bean
        public ItemReader<Produto> itemReaderProdutos() throws Exception {
                BeanWrapperFieldSetMapper<Produto> fieldSetMapper = new BeanWrapperFieldSetMapper<Produto>();
                fieldSetMapper.setTargetType(Produto.class);
                fieldSetMapper.afterPropertiesSet();

                return new FlatFileItemReaderBuilder<Produto>()
                                .name("itemReaderProdutos")
                                .resource(new ClassPathResource("produtos.csv"))
                                .linesToSkip(1)
                                .delimited()
                                .delimiter(",")
                                .names("nome", "descricao", "preco", "quantidadeestoque", "categoria", "imagemurl",
                                                "codigobarras", "status")
                                .fieldSetMapper(fieldSetMapper)
                                .build();
        }

        @Bean
        public ItemWriter<Produto> itemWriterProdutos(DataSource datasource) {
                return new JdbcBatchItemWriterBuilder<Produto>()
                                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                                .dataSource(datasource)
                                .sql("INSERT INTO Produto (nome, descricao, preco, quantidade_estoque, categoria, imagem_url, codigo_barras, status)"
                                                + " VALUES (:nome, :descricao, :preco, :quantidadeestoque, :categoria, :imagemurl, :codigobarras, :status)")
                                .build();
        }

        @Bean 
        public ItemWriter<Produto> loggingItemWriter() { 
                return items -> items.forEach(item -> System.out.println("Writing item: " + item)); 
        }
}
