package find.adm.br.agendacontato;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import find.adm.br.agendacontato.app.MessageBox;
import find.adm.br.agendacontato.database.DataBase;
import find.adm.br.agendacontato.dominio.RepositorioContato;
import find.adm.br.agendacontato.dominio.entidades.Contato;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;
    private ArrayAdapter<Contato> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = (ImageButton) findViewById(R.id.btnAdicionar);
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);

        btnAdicionar.setOnClickListener(this);
        lstContatos.setOnItemClickListener(this);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            adpContatos = repositorioContato.buscaContatos(this);

            lstContatos.setAdapter(adpContatos);

            FiltraDados filtraDados = new FiltraDados(adpContatos);
            edtPesquisa.addTextChangedListener(filtraDados);


        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActCadContatos.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        adpContatos = repositorioContato.buscaContatos(this);
        lstContatos.setAdapter(adpContatos);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contato contato = adpContatos.getItem(position);
        Intent it = new Intent(this, ActCadContatos.class);
        it.putExtra("CONTATO", contato);

        startActivityForResult(it, 0);
    }

    private class FiltraDados implements TextWatcher
    {
        private ArrayAdapter<Contato> arrayAdapter;

        private FiltraDados(ArrayAdapter<Contato> arrayAdapter)
        {
           this.arrayAdapter = arrayAdapter;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
