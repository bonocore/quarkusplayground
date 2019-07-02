package it.redhat.orm;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Regione extends PanacheEntity {

    public String NOME;

    public Regione(){}
    
    

}