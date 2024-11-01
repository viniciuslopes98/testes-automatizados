package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class NotificacaoModel {

    @Expose(serialize = false)
    private Long id;

    @Expose
    private Long moradorId;

    @Expose
    private String mensagem;

    @Expose
    private String dataNotificacao;

    @Expose
    private Boolean lida;
}
