package com.UniHubDine.Restaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Dao.ContactFormRepository;
import com.UniHubDine.Restaurant.Model.ContactForm;


@Service
public class ContactFormService {

    private final ContactFormRepository repository;

    @Autowired
    public ContactFormService(ContactFormRepository repository) {
        this.repository = repository;
    }

    public ContactForm saveContactForm(ContactForm contactForm) {
        return repository.save(contactForm);
    }

}