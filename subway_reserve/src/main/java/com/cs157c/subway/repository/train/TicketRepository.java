package com.cs157c.subway.repository.train;

import com.cs157c.subway.model.train.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TicketRepository extends MongoRepository<Ticket, String> {
}
