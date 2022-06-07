package com.mpirv.actionmonitor.repo;

import com.mpirv.actionmonitor.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Integer> {
}