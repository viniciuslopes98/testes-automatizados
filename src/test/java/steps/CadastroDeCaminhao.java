package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroCaminhaoService;

import java.util.List;
import java.util.Map;

public class CadastroDeCaminhao {

    CadastroCaminhaoService cadastroCaminhaoService = new CadastroCaminhaoService();

    public static Long caminhaoId;

    @Dado("que eu tenha os seguintes dados do caminhao:")
    public void queEuTenhaOsSeguintesDadosDoCaminhao(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroCaminhaoService.setCamposCaminhao(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de caminhao")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeCaminhao(String endPoint) {
        cadastroCaminhaoService.createCaminhao(endPoint);
        if (cadastroCaminhaoService.response.statusCode() == 201) {
            caminhaoId = cadastroCaminhaoService.getCaminhaoId();
        } else {
            caminhaoId = null;
        }
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroCaminhaoService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        String errorMessage = cadastroCaminhaoService.response.jsonPath().getString("placa");
        Assert.assertEquals(message, errorMessage);
    }

}

