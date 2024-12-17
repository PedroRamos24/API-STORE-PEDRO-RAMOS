package com.nttdata.glue;

import com.nttdata.steps.PetStoreOrderStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStoreOrderStepDefs {

    @Steps
    PetStoreOrderStep orderPetStore;

    //PRIMER SCENARIO

    @Given("siendo la URL {string}")
    public void siendoLaURL(String url) {
        orderPetStore.obtenerURL(url);
    }

    @When("envio los datos id {}, petId {}, quantity {}, shipDate {string}, status {string}, complete {string}")
    public void envioLosDatosIdPetIdQuantityShipDateStatusComplete(int id, int petId, int quantity, String shipDate, String status, String complete) {
        orderPetStore.envioDatos(id,petId,quantity,shipDate,status,complete);
    }

    @Then("verifico que el codigo de respuesta sea {int}")
    public void verificoQueElCodigoDeRespuestaSea(int statusCode) {
        orderPetStore.verificoCodigoRespuesta(statusCode);
    }

    @And("muestro los datos para verificarlos")
    public void muestroLosDatosParaVerificarlos() {
        orderPetStore.mostrarBody();
    }

    //SEGUNDO SCENARIO

    @When("envio el dato orderId {}")
    public void envioElDatoOrderId(String orderId) {
        orderPetStore.envioOrderId(orderId);
    }

    @And("verifico los siguientes datos petId {int}, quantity {int}, shipDate {string}, status {string}, complete {string}")
    public void verificoLosSiguientesDatosPetIdQuantityShipDateStatusComplete(int petId, int quantity, String shipDate, String status, String complete) {
        orderPetStore.verificarDatosRespuesta(petId,quantity,shipDate,status,complete);
    }



}
