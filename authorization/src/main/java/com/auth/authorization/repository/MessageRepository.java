package com.auth.authorization.repository;

import com.auth.authorization.domain.Message;
import com.auth.authorization.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m where m.room_id=:room_id")
    Optional<List<Message>> getMessageByRoomId(@Param("room_id") Integer room_id);

}
