package com.UniHubDine.Restaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Dao.ContactFormMongoRepository;
import com.UniHubDine.Restaurant.Model.ContactForm;

@Service
public class ContactFormMongoService {

    private final ContactFormMongoRepository mongoRepository;

    @Autowired
    public ContactFormMongoService(ContactFormMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public ContactForm saveContactForm(ContactForm contactForm) {
        return mongoRepository.save(contactForm);
    }

}