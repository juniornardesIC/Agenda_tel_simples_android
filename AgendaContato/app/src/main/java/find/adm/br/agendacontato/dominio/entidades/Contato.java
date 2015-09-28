package find.adm.br.agendacontato.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Junior Nardes 30/07/2015.
 */
public class Contato implements Serializable {

    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String TELEFONE = "TELEFONE";
    public static String TIPOTEL = "TIPOTELEFONE";
    public static String EMAIL = "EMAIL";
    public static String TIPOEMAIL = "TIPOEMAIL";
    public static String ENDERECO = "ENDERECO";
    public static String TIPOENDERECO = "TIPOENDERECO";
    public static String DATAESP = "DATASESPECIAIS";
    public static String TIPODATAESPECIAIS = "TIPODATASESPECIAIS";
    public static String GRUPOS = "GRUPOS";

    private long id;
    private String nome;
    private String telefone;
    private String tipoTel;
    private String email;
    private String tipoEmail;
    private String endereco;
    private String tipoendereco;
    private Date DataEsp;
    private String tipoDataEsp;
    private String grupos;

    public Contato()
    {
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTel() {
        return tipoTel;
    }

    public void setTipoTel(String tipoTel) {
        this.tipoTel = tipoTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoendereco() {
        return tipoendereco;
    }

    public void setTipoendereco(String tipoendereco) {
        this.tipoendereco = tipoendereco;
    }

    public Date getDataEsp() {
        return DataEsp;
    }

    public void setDataEsp(Date DataEsp) {
        this.DataEsp = DataEsp;
    }

    public String getTipoDataEsp() {
        return tipoDataEsp;
    }

    public void setTipoDataEsp(String tipoDataEsp) {
        this.tipoDataEsp = tipoDataEsp;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    @Override
    public String toString()
    {
        return nome + " " + telefone;
    }
}
