package com.dev.entity.envers;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class AuditConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    AuditReader auditReader() {
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }
}
