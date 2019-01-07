package com.santandertecnologia.testbackjava.resource;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.OffsetDateTime;

@RedisHash("Expense")
public class Expense implements Serializable {

    @Id
    private int id;

    private String description;
    private Double value;
    private int userCode;
    private OffsetDateTime date;
    private String category;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getValue() {
        return value;
    }

    public int getUserCode() {
        return userCode;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}