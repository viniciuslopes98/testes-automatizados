package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroAgendamentoColetaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroDeAgendamentoColeta {

    CadastroAgendamentoColetaService cadastroAgendamentoColetaService = new CadastroAgendamentoColetaService();

    @Dado("que eu tenha os seguintes dados específicos do agendamento de coleta:")
    public void queEuTenhaOsSeguintesDadosEspecíficosDoAgendamentoDeColeta(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            cadastroAgendamentoColetaService.setCamposAgendamentoColeta(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de agendamento de coleta")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeAgendamentoDeColeta(String endPoint) {
        cadastroAgendamentoColetaService.createAgendamentoColeta(endPoint);
    }

    @Então("o status code da resposta da requisição deve ser {int}")
    public void oStatusCodeDaRespostaDaRequisiçãoDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroAgendamentoColetaService.response.statusCode());
    }

    @E("o corpo de resposta de erro deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDeveRetornarAMensagem(String message) {
        String errorMessage = cadastroAgendamentoColetaService.response.jsonPath().getString("dataAgendamento");
        Assert.assertEquals(message, errorMessage);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroAgendamentoColetaService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroAgendamentoColetaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
