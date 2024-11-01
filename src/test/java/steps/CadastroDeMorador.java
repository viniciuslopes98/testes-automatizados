package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroMoradorService;

import java.util.List;
import java.util.Map;

public class CadastroDeMorador {

    CadastroMoradorService cadastroMoradorService = new CadastroMoradorService();

    public static Long moradorId;

    @Dado("que eu tenha os seguintes dados do morador:")
    public void queEuTenhaOsSeguintesDadosDoMorador(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroMoradorService.setCamposMorador(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de morador")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeMorador(String endPoint) {
        cadastroMoradorService.createMorador(endPoint);
        if (cadastroMoradorService.response.statusCode() == 201) {
            moradorId = cadastroMoradorService.getMoradorId();
        } else {
            moradorId = null;
        }
    }

    @Então("o status code da resposta vinda deve ser {int}")
    public void oStatusCodeDaRespostaVindaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroMoradorService.response.statusCode());
    }

    @E("o corpo de resposta de erro vinda da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroVindaDaApiDeveRetornarAMensagem(String message) {
        String errorMessage = cadastroMoradorService.response.jsonPath().getString("email");
        Assert.assertEquals(message, errorMessage);
    }
}
