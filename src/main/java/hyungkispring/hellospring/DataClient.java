package hyungkispring.hellospring;

import hyungkispring.hellospring.data.OrderRepository;
import hyungkispring.hellospring.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) throws InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        orderRepository.save(order);

        System.out.println("order = " + order);

        Order order1 = new Order("200", BigDecimal.TEN);
        orderRepository.save(order1);

        System.out.println("order = " + order);
    }

}
