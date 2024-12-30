package com.mongodb.repository;

import com.mongodb.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveMongoRepository<Address, Integer> {
}
