package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CaminhaoModel;

import static io.restassured.RestAssured.given;

public class CadastroCaminhaoService {

    final CaminhaoModel caminhaoModel = new CaminhaoModel();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    private String baseUrl = "https://fiap-safecoleta-app-dev-argfcjc5gqbzfbdm.eastus2-01.azurewebsites.net";

    public void setCamposCaminhao(String field, String value) {
        switch (field) {
            case "placa" -> caminhaoModel.setPlaca(value);
            case "motorista" -> caminhaoModel.setMotorista(value);
            case "status" -> caminhaoModel.setStatus(value);
            case "ultimaAtualizacao" -> caminhaoModel.setUltimaAtualizacao(value);
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }

    public void createCaminhao(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(caminhaoModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
        System.out.println("Resposta JSON: " + response.asString());
    }

    public Long getCaminhaoId() {
        if (response.statusCode() != 201) {
            System.out.println("Erro na criação do caminhão: " + response.asString());
            return null;
        }
        return response.jsonPath().getLong("id");
    }

}

