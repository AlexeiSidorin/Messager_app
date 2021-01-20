package ru.sidorin.sarafan.repository;


import org.springframework.data.repository.CrudRepository;

import ru.sidorin.sarafan.domain.Message;

public interface MessageRepo extends CrudRepository<Message, Long>{

}
