package com.gs.common;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Model<T> implements Serializable {
    @Id
    String id;

    T model;

    public Model(T model) {
        this.model = model;
    }
}
