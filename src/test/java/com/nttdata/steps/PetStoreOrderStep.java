package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class PetStoreOrderStep {

    Response response;
    private String URL_BASE;

    public void obtenerURL(String url) {
        URL_BASE = url;
    }

    public void envioDatos(int id, int petId, int quantity, String shipDate, String status, String complete) {
        String Body = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"petId\": "+petId+",\n" +
                "  \"quantity\": "+quantity+",\n" +
                "  \"shipDate\": \""+shipDate+"\",\n" +
                "  \"status\": \""+status+"\",\n" +
                "  \"complete\": "+complete+"\n" +
                "}";

        response = RestAssured
                .given()
                .baseUri(URL_BASE)
                .header("Content-Type","application/json")
                .body(Body)
                .log().all()
                .post("/store/order")
                .then()
                .extract().response();
    }

    public void verificoCodigoRespuesta(int statuscode) {
        Assert.assertEquals("Validacion de Respuesta", statuscode, response.statusCode());
    }


    public void mostrarBody() {
        String responseBody = response.asString();
        System.out.println("Respuesta completa del servidor: ");
        System.out.println(responseBody);
    }

    public void envioOrderId(String orderId) {
        response = RestAssured
                .given()
                //.relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .log().all()
                .get("/store/order/"+orderId)
                .then()
                .log().all()
                .extract().response();
    }

    public void verificarDatosRespuesta(int petId, int quantity, String shipDate, String status, String complete) {
        //Capturar los datos actuales del json
        int response_petId = response.jsonPath().getInt("petId");
        int response_quantity = response.jsonPath().getInt("quantity");
        String response_shipdate = response.jsonPath().getString("shipDate");
        String response_status = response.jsonPath().getString("status");
        String response_complete = response.jsonPath().getString("complete");

        //verificacion de datos
        Assert.assertEquals("Error, el parametro petId no coincide", petId, response_petId);
        Assert.assertEquals("Error, el parametro quantity no coincide", quantity, response_quantity);
        Assert.assertEquals("Error, el parametro shipdate no coincide", shipDate, response_shipdate);
        Assert.assertEquals("Error, el parametro status no coincide", status, response_status);
        Assert.assertEquals("Error, el parametro complete no coincide", complete, response_complete);

        System.out.println("Verificacion de datos exitosa");

        String responseBody = response.asString();
        System.out.println("Respuesta completa del servidor: ");
        System.out.println(responseBody);

    }
}
