package find.adm.br.agendacontato.database;


public class ScripSQL {

    public static String getCreateContato()
    {

        StringBuilder sqlBuider = new StringBuilder();
        sqlBuider.append("CREATE TABLE IF NOT EXISTS CONTATO ( ");
        sqlBuider.append("_id                INTEGER       NOT NULL ");
        sqlBuider.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuider.append("NOME               VARCHAR (200), ");
        sqlBuider.append("TELEFONE           VARCHAR (14), ");
        sqlBuider.append("TIPOTELEFONE       VARCHAR (1), ");
        sqlBuider.append("EMAIL              VARCHAR (255), ");
        sqlBuider.append("TIPOEMAIL          VARCHAR (1), ");
        sqlBuider.append("ENDERECO           VARCHAR (255), ");
        sqlBuider.append("TIPOENDERECO       VARCHAR (1), ");
        sqlBuider.append("DATASESPECIAIS     DATE, ");
        sqlBuider.append("TIPODATASESPECIAIS VARCHAR (1), ");
        sqlBuider.append("GRUPOS             VARCHAR (255) ");
        sqlBuider.append(");");

        return sqlBuider.toString();
    }
}
