package com.sof.newsletter_email.dao;

import com.sof.newsletter_email.model.NewsletterEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoNewsletterEmail extends JpaRepository<NewsletterEmail, Integer> {


}
