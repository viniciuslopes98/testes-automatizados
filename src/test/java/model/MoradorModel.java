package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class MoradorModel {

    @Expose(serialize = false)
    private Long id;

    @Expose
    private String nome;

    @Expose
    private String email;
}
