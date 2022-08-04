package com.dev.entity.envers;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionEntityListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setUsername("hamza");
    }
}
