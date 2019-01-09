package com.santandertecnologia.testbackjava.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.format.DateTimeFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;

@RedisHash("Expense")
public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("valor")
    private Double value;

    @JsonProperty("codigousuario")
    private int userCode;

    @JsonProperty("data")
    @Temporal(TemporalType.TIMESTAMP)
    private Long date;

    @JsonProperty("categoria")
    private String category;

    public Expense() {}

    public Expense(long id, String description, Double value, int userCode, String date, String category) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.userCode = userCode;
        this.date = convertStringToMillis(date);
        this.category = category;
    }

    public Expense(long id, String description, Double value, int userCode, Long date, String category) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.userCode = userCode;
        this.date = date;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = convertStringToMillis(date);
    }

    public static Long convertStringToMillis(String date) {
        return DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parseDateTime(date).getMillis();
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