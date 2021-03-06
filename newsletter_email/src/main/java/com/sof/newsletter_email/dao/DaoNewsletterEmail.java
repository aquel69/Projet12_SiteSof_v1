package com.sof.newsletter_email.dao;

import com.sof.newsletter_email.model.NewsletterEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DaoNewsletterEmail extends JpaRepository<NewsletterEmail, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="DELETE FROM email_newsletter WHERE email = ?", nativeQuery = true)
    void supprimerEmailNewsletter(String email);
}
