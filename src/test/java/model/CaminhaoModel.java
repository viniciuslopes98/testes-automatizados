package model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaminhaoModel {

    @Expose(serialize = false)
    private Long id;

    @Expose
    private String placa;

    @Expose
    private String motorista;

    @Expose
    private String status;

    @Expose
    private String ultimaAtualizacao;
}
