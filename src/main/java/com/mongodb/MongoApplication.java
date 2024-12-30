package com.mongodb;

import com.mongodb.model.Address;
import com.mongodb.model.Customer;
import com.mongodb.repository.AddressRepository;
import com.mongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableReactiveMongoRepositories(basePackageClasses = {CustomerRepository.class, AddressRepository.class})
public class MongoApplication {

    @Autowired
    CustomerRepository custRepo;

    @Autowired
    AddressRepository addressRepo;


    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }


    @PostConstruct
    public void init() {
        System.out.println("Executed");
        addressRepo.save(new Address(7, "street1", "city1", "111111")).subscribe(result -> System.out.println("Entity has been saved: "+result));
        addressRepo.save(new Address(8, "street2", "city2", "222222")).subscribe(result -> System.out.println("Entity has been saved: "+result));
        addressRepo.save(new Address(9, "street3", "city3", "333333")).subscribe(result -> System.out.println("Entity has been saved: "+result));
        addressRepo.save(new Address(10, "street4", "city4", "444444")).subscribe(result -> System.out.println("Entity has been saved: "+result));
        addressRepo.save(new Address(11, "street5", "city5", "555555")).subscribe(result -> System.out.println("Entity has been saved: "+result));
        addressRepo.save(new Address(12, "street6", "city6", "666666")).subscribe(result -> System.out.println("Entity has been saved: "+result));

        custRepo.save(new Customer(1, "praneeth", true, new Address(7, "street1", "city1", "111111"))).subscribe(result -> System.out.println("Entity has been saved: "+result));
        custRepo.save(new Customer(2, "wvkbe", false, new Address(8, "street2", "city2", "222222"))).subscribe(result -> System.out.println("Entity has been saved: "+result));
        custRepo.save(new Customer(3, "rbe", true, new Address(9, "street3", "city3", "333333"))).subscribe(result -> System.out.println("Entity has been saved: "+result));
        custRepo.save(new Customer(4, "btebygn", false, new Address(10, "street4", "city4", "444444"))).subscribe(result -> System.out.println("Entity has been saved: "+result));
        custRepo.save(new Customer(5, "tyteb", true, new Address(11, "street5", "city5", "555555"))).subscribe(result -> System.out.println("Entity has been saved: "+result));
        custRepo.save(new Customer(6, "vfdet", false, new Address(12, "street6", "city6", "666666"))).subscribe(result -> System.out.println("Entity has been saved: "+result));
        System.out.println("Ended");
    }

    @PreDestroy
    public void cleanUp() {
        addressRepo.deleteAll().subscribe(result -> System.out.println("Entity has been deleted: "+result));
        custRepo.deleteAll().subscribe(result -> System.out.println("Entity has been deleted: "+result));
    }
}
