package com.santandertecnologia.testbackjava.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ExpenseAPIAuthenticationTest extends APITests {

    @Test
    public void auth_apiGETExpenseRejectsNonAuthenticatedRequests() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(EXPENSE_ENDPOINT)
                .asJson();

        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    public void auth_apiPOSTExpenseRejectsNonAuthenticatedRequests() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(EXPENSE_ENDPOINT)
                .body(gastoTest)
                .asJson();

        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    public void auth_apiGETCategoryRejectsNonAuthenticatedRequests() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(CATEGORY_ENDPOINT)
                .asJson();

        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    public void auth_apiPUTExpenseRejectsNonAuthenticatedRequests() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.put(EXPENSE_ENDPOINT + "/0")
                .body(gastoTest2)
                .asJson();

        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

}
