package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.MoradorModel;

import static io.restassured.RestAssured.given;

public class CadastroMoradorService {

    final MoradorModel moradorModel = new MoradorModel();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    private String baseUrl = "https://fiap-safecoleta-app-dev-argfcjc5gqbzfbdm.eastus2-01.azurewebsites.net";

    public void setCamposMorador(String field, String value) {
        switch (field) {
            case "nome" -> moradorModel.setNome(value);
            case "email" -> moradorModel.setEmail(value);
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }

    public void createMorador(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(moradorModel);
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

    public Long getMoradorId() {
        if (response.statusCode() != 201) {
            System.out.println("Erro na criação do morador: " + response.asString());
            return null;
        }
        return response.jsonPath().getLong("id");
    }
}
