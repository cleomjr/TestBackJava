package com.santandertecnologia.testbackjava.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;

public class ExpenseApiCategoryRequestsTests extends APITests {

    private static final String CATEGORY_PARAM = "category";
    private static final String EMPTY_BODY = "[]";
    private static final String CATEGORY_PARAM_VALUE = "Tes";

    @Test
    public void api_listsNoCategories_whenNoCategoryParameter() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(CATEGORY_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .asJson();

        Assert.assertEquals(EMPTY_BODY, response.getBody().toString());
    }

    @Test
    public void api_listsNoCategories_whenCategoryParameterLessThanMinimum() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(CATEGORY_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .queryString(CATEGORY_PARAM, "")
                .asJson();

        Assert.assertEquals(EMPTY_BODY, response.getBody().toString());
    }

    @Test
    public void api_listsCategories_whenCategoryParameterEqualOrMoreThanMinimum() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(CATEGORY_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .queryString(CATEGORY_PARAM, CATEGORY_PARAM_VALUE)
                .asJson();

        Assert.assertNotEquals(EMPTY_BODY, response.getBody().toString());
    }
}
