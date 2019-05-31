package com.gs.telegram.repository;

import com.gs.telegram.model.ChatModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatModel, String> {

}