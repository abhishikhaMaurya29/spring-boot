package com.abhishikha.initialData;

import com.abhishikha.constants.Status;
import com.abhishikha.model.Order;
import com.abhishikha.repository.EmployeeRepository;
import com.abhishikha.model.Employee;
import com.abhishikha.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            employeeRepository.save(new Employee("Namita", "Singh", "burglar"));
            employeeRepository.save(new Employee("Lait", "Jaywalk", "admin"));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded {}", employee));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> log.info("Preloaded {}", order));
        };
    }
}
