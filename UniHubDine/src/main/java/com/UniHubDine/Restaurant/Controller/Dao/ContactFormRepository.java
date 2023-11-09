package com.UniHubDine.Restaurant.Controller.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UniHubDine.Restaurant.Controller.bean.ContactForm;

public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {

}
