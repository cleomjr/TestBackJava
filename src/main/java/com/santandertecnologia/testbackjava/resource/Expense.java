package com.santandertecnologia.testbackjava.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("Expense")
public class Expense implements Serializable {

    @Id
    private int id;

    private String description;
    private Double value;
    private int userCode;
    private LocalDateTime date;
    private String category;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("categoria")
    public String getCategory() {
        return category;
    }

    @JsonProperty("categoria")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("valor")
    public Double getValue() {
        return value;
    }

    @JsonProperty("codigousuario")
    public int getUserCode() {
        return userCode;
    }

    @JsonProperty("data")
    public LocalDateTime getDate() {
        return date;
    }

    @JsonProperty("descricao")
    public String getDescription() {
        return description;
    }

    @JsonProperty("data")
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonProperty("descricao")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("codigousuario")
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @JsonProperty("valor")
    public void setValue(Double value) {
        this.value = value;
    }
}