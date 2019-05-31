package com.gs.common.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public abstract class PersistenceModel implements Serializable {
    @Id
    private String id;
}
