package br.com.fcoviana.apilojalivrosvirtual.autor.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CriarAutorRequest {
    @Schema(description = "Nome do autor", example = "Paulo Ricardo")
    @NotEmpty(message = "O nome é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @Schema(description = "E-mail do usuario", example = "paulo.ricardo@mail.com")
    @UniqueValue(domainClass = Autor.class, fieldName = "email")
    @NotEmpty(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @Schema(description = "Descriçāo do autor", example = "Foi escritor; poeta, dramaturgo, linguista, tradutor e crítico. Formou-se em literatura inglesa pela Universidade de Manchester e morreu em 1993, aos 76 anos, deixando em seu legado uma grande e reconhecida obra, entre romances, biografias, peças de teatro, estudos literários, roteiros de cinema e TV.")
    @NotEmpty(message = "A descriçāo é obrigatória")
    @Size(min = 1, max = 400, message = "A descriçāo deve ter entre 1 e 400 caracteres")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }
}
