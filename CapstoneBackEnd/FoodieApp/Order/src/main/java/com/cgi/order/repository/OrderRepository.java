package com.cgi.order.repository;

import com.cgi.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

        public List<Order> findByOrderIdStartsWith(String orderId);

}
