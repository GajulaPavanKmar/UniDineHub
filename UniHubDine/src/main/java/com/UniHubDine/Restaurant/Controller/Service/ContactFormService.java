package com.UniHubDine.Restaurant.Controller.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Controller.Dao.ContactFormRepository;
import com.UniHubDine.Restaurant.Controller.bean.ContactForm;


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