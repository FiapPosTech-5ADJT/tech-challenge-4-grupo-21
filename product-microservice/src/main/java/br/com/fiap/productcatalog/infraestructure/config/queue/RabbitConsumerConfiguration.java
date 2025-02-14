package br.com.fiap.productcatalog.infraestructure.config.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConsumerConfiguration {

  @Value("${queue.atualizar-estoque.name}")
  private String atualizarEstoqueQueueName;

  @Value("${queue.atualizar-estoque.exchange.name}")
  private String atualizarEstoqueExchangeName;

  @Value("${queue.atualizar-estoque-dlx.key}")
  private String atualizarEstoqueDlxKey;

  @Bean
  Queue atualizarEstoqueQueue() {
    return new Queue(atualizarEstoqueQueueName, true);
  }

  @Bean
  TopicExchange atualizarEstoqueDlx() {
    return new TopicExchange(atualizarEstoqueExchangeName);
  }

  @Bean
  Binding atualizarEstoqueBinding(Queue atualizarEstoqueQueue, TopicExchange atualizarEstoqueDlx) {
    return BindingBuilder.bind(atualizarEstoqueQueue).to(atualizarEstoqueDlx).with(atualizarEstoqueDlxKey);
  }
}
