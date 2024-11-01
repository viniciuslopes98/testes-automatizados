package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroNotificacaoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroNotificacao {

    CadastroNotificacaoService cadastroNotificacaoService = new CadastroNotificacaoService();

    @Dado("que eu tenha os seguintes dados específicos da notificacao:")
    public void queEuTenhaOsSeguintesDadosEspecíficosDeNotificacao(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            cadastroNotificacaoService.setCamposNotificacao(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de notificacao")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeNotificacao(String endPoint) {
        cadastroNotificacaoService.createNotificacao(endPoint);
    }

    @Então("o status code da resposta vinda da api deve ser {int}")
    public void oStatusCodeDaRespostaVindaDaApiDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroNotificacaoService.response.statusCode());
    }

    @E("o corpo de resposta de erro vinda da api de notificao deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroVindaDaApiDeNotificaoDeveRetornarAMensagem(String message) {
        String errorMessage = cadastroNotificacaoService.response.jsonPath().getString("mensagem");
        Assert.assertEquals(message, errorMessage);
    }

    @E("que o arquivo de contrato esperado de notificacao é o {string}")
    public void queOArquivoDeContratoEsperadoDeNotificacaoÉO(String contract) throws IOException {
        cadastroNotificacaoService.setContract(contract);
    }

    @Então("a resposta da requisição tem que estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoTemQueEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroNotificacaoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
