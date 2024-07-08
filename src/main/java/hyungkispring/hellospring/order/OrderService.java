package hyungkispring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> createOrders(List<OrderReq> reqs);

    Order createOrder(String no, BigDecimal total);

}
