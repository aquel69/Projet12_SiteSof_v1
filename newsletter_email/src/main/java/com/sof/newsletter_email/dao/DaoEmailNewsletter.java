package com.sof.newsletter_email.dao;

import com.sof.newsletter_email.model.NewsletterEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoEmailNewsletter extends JpaRepository<NewsletterEmail, Integer> {


}
