package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class AgendamentoColetaModel {

    @Expose(serialize = false)
    private Long id;

    @Expose
    private Long caminhaoId;

    @Expose
    private String tipoResiduos;

    @Expose
    private String dataAgendamento;

    @Expose
    private String horario;

    @Expose
    private String endereco;

    @Expose
    private Boolean confirmado;

}
