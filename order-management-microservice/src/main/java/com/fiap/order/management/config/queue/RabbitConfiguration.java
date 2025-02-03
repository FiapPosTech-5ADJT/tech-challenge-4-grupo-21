package com.fiap.order.management.config.queue;

	import org.springframework.amqp.core.Binding;
	import org.springframework.amqp.core.BindingBuilder;
	import org.springframework.amqp.core.Queue;
	import org.springframework.amqp.core.TopicExchange;
	import org.springframework.beans.factory.annotation.Qualifier;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;

	@Configuration
	public class RabbitConfiguration {

	    @Value("${queue.atualizar-estoque.name}")
	    private String atualizarEstoqueQueueName;

	    @Value("${queue.atualizar-estoque.exchange.name}")
	    private String atualizarEstoqueExchangeName;

	    @Value("${queue.atualizar-estoque-dlx.key}")
	    private String atualizarEstoqueDlxKey;

	    @Value("${queue.logistica.name}")
	    private String logisticaQueueName;

	    @Value("${queue.logistica.exchange.name}")
	    private String logisticaExchangeName;

	    @Value("${queue.logistica-dlx.key}")
	    private String logisticaDlxKey;

	    @Bean("atualizarEstoqueQueue")
	    Queue atualizarEstoqueQueue() {
	        return new Queue(atualizarEstoqueQueueName, true);
	    }

	    @Bean("atualizarEstoqueDlx")
	    TopicExchange atualizarEstoqueDlx() {
	        return new TopicExchange(atualizarEstoqueExchangeName);
	    }

	    @Bean
	    Binding atualizarEstoqueBinding(
	            @Qualifier("atualizarEstoqueQueue") Queue atualizarEstoqueQueue,
	            @Qualifier("atualizarEstoqueDlx") TopicExchange atualizarEstoqueDlx) {
	        return BindingBuilder.bind(atualizarEstoqueQueue).to(atualizarEstoqueDlx).with(atualizarEstoqueDlxKey);
	    }

	    @Bean("logisticaQueue")
	    Queue logisticaQueue() {
	        return new Queue(logisticaQueueName, true);
	    }

	    @Bean("logisticaDlx")
	    TopicExchange logisticaDlx() {
	        return new TopicExchange(logisticaExchangeName);
	    }

	    @Bean
	    Binding logisticaBinding(
	            @Qualifier("logisticaQueue") Queue logisticaQueue,
	            @Qualifier("logisticaDlx") TopicExchange logisticaDlx) {
	        return BindingBuilder.bind(logisticaQueue).to(logisticaDlx).with(logisticaDlxKey);
	    }
	}
