package org.rsta80.demo.repository;

import org.rsta80.demo.dto.ExchangeValue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExchangeValueRepository extends MongoRepository<ExchangeValue, Object> {

    Optional<ExchangeValue> findByFromAndTo(String from, String to);

}
