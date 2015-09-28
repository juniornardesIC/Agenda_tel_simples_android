package find.adm.br.agendacontato.database;


import android.content.Context;
import android.database.sqlite.*;

import find.adm.br.agendacontato.database.ScripSQL;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context)
    {
        super(context,"agenda", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScripSQL.getCreateContato() );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
