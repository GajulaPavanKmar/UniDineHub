package com.UniHubDine.Restaurant.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.UniHubDine.Restaurant.Model.ContactForm;

public interface ContactFormMongoRepository extends MongoRepository<ContactForm, String> {
    // Custom MongoDB queries (if any)
}
