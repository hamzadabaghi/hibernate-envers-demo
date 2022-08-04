package com.dev.entity.envers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REVINFO")
@RevisionEntity(CustomRevisionEntityListener.class)
public class CustomRevisionEntity extends DefaultRevisionEntity {
    private static final long serialVersionUID = 1L;
    private String username;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }




}
