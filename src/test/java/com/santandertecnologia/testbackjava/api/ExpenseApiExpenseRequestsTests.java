package com.santandertecnologia.testbackjava.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ExpenseApiExpenseRequestsTests extends APITests {

    private static final String DESCRIPTION_PARAM = "description";
    private static final String FROM_DATE_PARAM = "fromDate";
    private static final String TO_DATE_PARAM = "toDate";
    private static final String ID_PATH_PARAM = "id";

    @Test
    public void api_createsExpense_whenWithSpecificJsonBody() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                .body(gastoTest)
                .asJson();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void api_throwsBadRequest_whenWithoutBody() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                .asJson();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void api_listsExpenses() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .asJson();

        Assert.assertFalse(response.getBody().toString().isEmpty());
    }

    @Test
    public void api_listsExpenses_whenFilteredByDescription() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .queryString(DESCRIPTION_PARAM, descricaoTest)
                .asJson();

        Assert.assertFalse(response.getBody().toString().isEmpty());
    }

    @Test
    public void api_listsExpenses_whenFilteredByDatePeriod() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .queryString(FROM_DATE_PARAM, dataTest)
                .queryString(TO_DATE_PARAM, dataTest)
                .asJson();

        Assert.assertFalse(response.getBody().toString().isEmpty());
    }

    @Test
    public void api_editsExpense_whenWithSpecificJsonBody() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.put(EXPENSE_ID_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .routeParam(ID_PATH_PARAM, "0")
                .body(gastoTest2)
                .asJson();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
