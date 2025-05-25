package br.com.fcoviana.apilojalivrosvirtual.compra.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Estado;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Pais;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.CpfCnpj;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.ExistsId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CriarCompraRequest {
    @Schema(description = "Email do cliente", example = "pedro.silva@gmail.com")
    @Email(message = "Deve ser um email válido.")
    @NotEmpty(message = "O email é obrigatório")
    private String email;

    @Schema(description = "Nome do cliente", example = "Pedro")
    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    @Schema(description = "Sobrenome do cliente", example = "Silva")
    @NotEmpty(message = "O sobrenome é obrigatório")
    private String sobrenome;

    @Schema(description = "Documento do cliente (CPF ou CNPJ)", example = "327.791.320-19")
    @CpfCnpj
    @NotEmpty(message = "O documento é obrigatório")
    private String documento;

    @Schema(description = "Endereço do cliente", example = "Rua Senador Virgílio Távora")
    @NotEmpty(message = "O endereco é obrigatório")
    private String endereco;

    @Schema(description = "Complemento do cliente", example = "Parque Genibaú")
    @NotEmpty(message = "O complemento é obrigatório")
    private String complemento;

    @Schema(description = "Cidade do cliente", example = "Fortaleza")
    @NotEmpty(message = "O cidade é obrigatória")
    private String cidade;

    @Schema(description = "Id do Pais", example = "1")
    @ExistsId(domainClass = Pais.class, fieldName = "id", message = "O pais não existe")
    @NotNull(message = "A paisId é obrigatório")
    private Long paisId;

    @Schema(description = "Id do Estado", example = "1")
    @ExistsId(domainClass = Estado.class, fieldName = "id", message = "O estado não existe")
    private Long estadoId;

    @Schema(description = "Telefone do cliente", example = "(71) 99021-0242")
    @NotEmpty(message = "O telefone é obrigatório")
    private String telefone;

    @Schema(description = "Cep do cliente", example = "60534-260")
    @NotEmpty(message = "O cep é obrigatório")
    private String cep;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    //    public Estado toModel(EntityManager entityManager) {
//        var pais = entityManager.find(Pais.class, paisId);
//        return new Estado(nome, pais);
//    }
}
