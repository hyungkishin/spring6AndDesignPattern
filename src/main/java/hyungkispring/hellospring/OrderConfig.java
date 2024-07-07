package hyungkispring.hellospring;

import hyungkispring.hellospring.data.JdbcOrderRepository;
import hyungkispring.hellospring.order.OrderRepository;
import hyungkispring.hellospring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager,
                                     OrderRepository orderRepository) {
        return new OrderService(orderRepository, transactionManager);
    }

}
