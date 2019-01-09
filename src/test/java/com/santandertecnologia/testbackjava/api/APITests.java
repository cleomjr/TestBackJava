package com.santandertecnologia.testbackjava.api;

class APITests {

    static final String AUTHORIZATION_KEY = "Authorization";
    static final String AUTHORIZATION_VALUE = "Basic YWRtaW46YWRtaW4=";
    private static final String API_URL = "http://localhost:8090/api";
    static final String EXPENSE_ENDPOINT = API_URL + "/expense";
    static final String EXPENSE_ID_ENDPOINT = API_URL + "/expense/{id}";
    static final String CATEGORY_ENDPOINT = API_URL + "/expense/category";
    static final String CONTENT_TYPE_VALUE = "application/json";
    static final String CONTENT_TYPE_KEY = "Content-type";

    String descricaoTest = "Teste gasto";
    String dataTest = "2019-01-08T12:32:58.596Z";

    String gastoTest = "{\n" +
            "  \"categoria\": \"Teste\",\n" +
            "  \"codigousuario\": 1,\n" +
            "  \"data\": \"" + dataTest + "\",\n" +
            "  \"descricao\": \"" + descricaoTest + "\",\n" +
            "  \"valor\": 22.0\n" +
            "}";

    String gastoTest2 = "{\n" +
            "  \"categoria\": \"Teste\",\n" +
            "  \"codigousuario\": 1,\n" +
            "  \"data\": \"2019-01-08T12:32:58.596Z\",\n" +
            "  \"descricao\": \"Teste gasto 1234\",\n" +
            "  \"valor\": 33.0\n" +
            "}";

}
