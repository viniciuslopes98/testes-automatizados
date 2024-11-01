package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AgendamentoColetaModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroAgendamentoColetaService {

    String schemasPath = "src/test/resources/schemas/";

    JSONObject jsonSchema;

    private final ObjectMapper mapper = new ObjectMapper();

    final AgendamentoColetaModel agendamentoColetaModel = new AgendamentoColetaModel();

    public Response response;

    private String baseUrl = "https://fiap-safecoleta-app-dev-argfcjc5gqbzfbdm.eastus2-01.azurewebsites.net";

    public void setCamposAgendamentoColeta(String field, String value) {
            switch (field) {
                case "caminhaoId" -> agendamentoColetaModel.setCaminhaoId(Long.valueOf(value));
                case "tipoResiduos" -> agendamentoColetaModel.setTipoResiduos(value);
                case "dataAgendamento" -> agendamentoColetaModel.setDataAgendamento(value);
                case "horario" -> agendamentoColetaModel.setHorario(value);
                case "endereco" -> agendamentoColetaModel.setEndereco(value);
                case "confirmado" -> agendamentoColetaModel.setConfirmado(Boolean.parseBoolean(value));
                default -> throw new IllegalStateException("Unexpected field: " + field);
            }
    }


    public void createAgendamentoColeta(String endPoint) {
        String url = baseUrl + endPoint;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String bodyToSend = gson.toJson(agendamentoColetaModel);

        System.out.println("Body enviado para o agendamento de coleta: " + bodyToSend);
        System.out.println("Caminhao ID usado no agendamento: " + agendamentoColetaModel.getCaminhaoId());

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de agendamento de coleta" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-agendamentocoleta.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException
    {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}


