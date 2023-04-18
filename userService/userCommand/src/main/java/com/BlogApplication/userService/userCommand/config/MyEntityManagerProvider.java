package com.BlogApplication.userService.userCommand.config;

import org.axonframework.common.legacyjpa.EntityManagerProvider;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
public class MyEntityManagerProvider implements EntityManagerProvider {

    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
