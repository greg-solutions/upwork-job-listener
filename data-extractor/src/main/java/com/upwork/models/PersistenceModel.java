package com.upwork.models;

import org.springframework.data.annotation.Id;

public abstract class PersistenceModel {
    @Id
    public String id;
}
