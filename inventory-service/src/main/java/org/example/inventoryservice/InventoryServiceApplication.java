package org.example.inventoryservice;

import org.example.inventoryservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.example.inventoryservice.repository.ProductRepository;
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder().id("P01").name("Computer").price(2300).quantity(5)
                    .build());
            productRepository.save(Product.builder().id("P02").name("Printer").price(1200).quantity(10)
                    .build());
            productRepository.save(Product.builder().id("P03").name("Smart Phone").price(4200).quantity(34)
                    .build());
        };
    }

}
