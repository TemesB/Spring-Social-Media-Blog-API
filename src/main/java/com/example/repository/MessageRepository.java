package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List; 

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository <Message, Integer> {
    // Defiene methods that follows query naming convention
    @Query("FROM Message mess where mess.posted_by = :posted_by")
    Optional<Message> findMessageByposted_by(Integer posted_by);
    
    @Query("FROM Message mess where mess.message_id = :message_id")
    Optional<Message> findMessageByMessage_Id(Integer message_id);

}
