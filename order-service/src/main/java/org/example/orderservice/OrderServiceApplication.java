package org.example.orderservice;

import org.example.orderservice.entities.Order;
import org.example.orderservice.entities.OrderState;
import org.example.orderservice.entities.ProductItem;
import org.example.orderservice.repsoitories.OrderRepository;
import org.example.orderservice.repsoitories.ProductItemRepository;
import org.example.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            InventoryRestClient inventoryRestClient
    ){
        return args -> {
            //List<Product> allProducts = inventoryRestClient.getAllProducts();
            List<String> productsIds = List.of("P01","P02","P03");
            for (int i = 0; i < 5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder = orderRepository.save(order);
                productsIds.forEach(pId->{
                    ProductItem productItem = ProductItem.builder()
                            .productId(pId)
                            .quantity(new Random().nextInt(20))
                            .price(Math.random()*6000+100)
                            .order(savedOrder)
                            .build();
                    productItemRepository.save(productItem);
                });

            }
        };
    }

}
