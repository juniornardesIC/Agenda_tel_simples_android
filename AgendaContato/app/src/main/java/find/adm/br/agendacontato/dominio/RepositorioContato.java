package find.adm.br.agendacontato.dominio;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

import find.adm.br.agendacontato.dominio.entidades.Contato;

public class RepositorioContato {

    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn)
    {
      this.conn = conn;
    }

    private ContentValues preecheContentValues(Contato contato)
    {
        ContentValues values = new ContentValues();

        values.put("NOME"  , contato.getNome());
        values.put("TELEFONE"  , contato.getTelefone());
        values.put("TIPOTELEFONE"  , contato.getTipoTel());
        values.put("EMAIL"  , contato.getEmail());
        values.put("TIPOEMAIL"  , contato.getTipoEmail());
        values.put("ENDERECO"  , contato.getEndereco());
        values.put("TIPOENDERECO"  , contato.getTipoendereco());
        values.put("DATASESPECIAIS"  , contato.getDataEsp().getTime());
        values.put("TIPODATASESPECIAIS"  , contato.getTipoDataEsp());
        values.put("GRUPOS"  , contato.getGrupos());

        return values;
    }

    public void excluir (long id)
    {
        conn.delete("CONTATO", "_id = ?", new String[]{ String.valueOf(id)});
    }

    public void alterar(Contato contato)
    {

        ContentValues values = preecheContentValues(contato);
        conn.update("CONTATO", values, "_id = ?", new String[] {String.valueOf(contato.getId())});

    }

    public void inserir(Contato contato)
    {
        ContentValues values = preecheContentValues(contato);
        conn.insertOrThrow("CONTATO", null, values);
    }

    public ArrayAdapter<Contato> buscaContatos(Context context)
    {
        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {

                Contato contato = new Contato();
                contato.setId(cursor.getLong(cursor.getColumnIndex(Contato.ID)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Contato.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Contato.TELEFONE)));
                contato.setTipoTel(cursor.getString(cursor.getColumnIndex(Contato.TIPOTEL)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(Contato.EMAIL)));
                contato.setTipoEmail(cursor.getString(cursor.getColumnIndex(Contato.TIPOEMAIL)));
                contato.setEndereco(cursor.getString(cursor.getColumnIndex(Contato.ENDERECO)));
                contato.setTipoendereco(cursor.getString(cursor.getColumnIndex(Contato.TIPOENDERECO)));
                contato.setDataEsp(new Date(cursor.getLong(cursor.getColumnIndex(Contato.DATAESP))));
                contato.setTipoDataEsp(cursor.getString(cursor.getColumnIndex(Contato.TIPODATAESPECIAIS)));
                contato.setGrupos(cursor.getString(cursor.getColumnIndex(Contato.GRUPOS)));


                adpContatos.add(contato);
            }while (cursor.moveToNext());
        }
        return adpContatos;
    }
}
