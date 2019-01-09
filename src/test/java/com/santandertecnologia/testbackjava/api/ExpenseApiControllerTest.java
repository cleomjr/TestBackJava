package com.santandertecnologia.testbackjava.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ExpenseApiControllerTest {
    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final String AUTHORIZATION_VALUE = "Basic YWRtaW46YWRtaW4=";
    public static final String API_URL = "http://localhost:8090/api";
    public static final String EXPENSE_ENDPOINT = API_URL + "/expense";
    public static final String CATEGORY_ENDPOINT = API_URL + "/expense/category";
    public static final String CONTENT_TYPE_VALUE = "application/json";
    public static final String CONTENT_TYPE_KEY = "Content-type";

    private String gastoTest = "{\n" +
            "  \"categoria\": \"Teste\",\n" +
            "  \"codigousuario\": 1,\n" +
            "  \"data\": \"2019-01-08T12:32:58.596Z\",\n" +
            "  \"descricao\": \"Teste gasto\",\n" +
            "  \"valor\": 22.0\n" +
            "}";

    @Test
    public void auth_apiRejectsNonAuthenticatedRequests() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(EXPENSE_ENDPOINT)
                .asJson();

        Assert.assertTrue(response.getStatus() == HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void auth_apiAcceptsAuthenticatedRequests() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .asJson();

        Assert.assertTrue(response.getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void api_whenWithSpecificJsonBody_createsExpense() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                .body(gastoTest)
                .asJson();

        Assert.assertTrue(response.getStatus() == HttpStatus.OK.value());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void api_whenWithoutBody_throwsBadRequest() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(EXPENSE_ENDPOINT)
                .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
                .header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                .asJson();

        Assert.assertTrue(response.getStatus() == HttpStatus.BAD_REQUEST.value());
    }
}
