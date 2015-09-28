package find.adm.br.agendacontato;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import find.adm.br.agendacontato.app.MessageBox;
import find.adm.br.agendacontato.database.DataBase;
import find.adm.br.agendacontato.dominio.RepositorioContato;
import find.adm.br.agendacontato.dominio.entidades.Contato;


public class ActCadContatos extends Activity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtDataEsp;
    private EditText edtGrupos;

    private Spinner spnTipoTel;
    private Spinner spnTipoEmail;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDataEsp;

    private ArrayAdapter<String> adpTipoTel;
    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDataEsp;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contatos);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtDataEsp = (EditText)findViewById(R.id.edtDataEsp);
        edtGrupos = (EditText)findViewById(R.id.edtGrupos);

        spnTipoTel = (Spinner)findViewById(R.id.spnTipoTel);
        spnTipoEmail = (Spinner)findViewById(R.id.spnTipoEmail);
        spnTipoEndereco = (Spinner)findViewById(R.id.spnTipoEndereco);
        spnTipoDataEsp = (Spinner)findViewById(R.id.spnTipoDataEsp);

        adpTipoTel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoTel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEmail = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoDataEsp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoDataEsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTipoTel.setAdapter(adpTipoTel);
        spnTipoEmail.setAdapter(adpTipoEmail);
        spnTipoEndereco.setAdapter(adpTipoEndereco);
        spnTipoDataEsp.setAdapter(adpTipoDataEsp);

        adpTipoEmail.add("Casa");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");

        adpTipoTel.add("Celular");
        adpTipoTel.add("Trabalho");
        adpTipoTel.add("Casa");
        adpTipoTel.add("Principal");
        adpTipoTel.add("Fax trabalho");
        adpTipoTel.add("Fax casa");
        adpTipoTel.add("Page");
        adpTipoTel.add("Outros");

        adpTipoEndereco.add("Casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");

        adpTipoDataEsp.add("Aniversario");
        adpTipoDataEsp.add("Data comemorativa");
        adpTipoDataEsp.add("Outros");

        ExibeDataListener listener = new ExibeDataListener();

        edtDataEsp.setOnClickListener(listener);
        edtDataEsp.setOnFocusChangeListener(listener);

       Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("CONTATO")))
        {
            contato = (Contato)bundle.getSerializable("CONTATO");
            preencheDados();
        }else
        contato = new Contato();

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);


        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_contatos, menu);

        if (contato.getId() !=0)
        menu.getItem(1).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.mni_acao1:
                salvar();
                finish();
                break;
            case R.id.mni_acao2:
                excluir();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencheDados()
    {
        edtNome.setText(contato.getNome() );
        edtTelefone.setText(contato.getTelefone());
        spnTipoTel.setSelection(Integer.parseInt(contato.getTipoTel()));
        edtEmail.setText(contato.getEmail());
        spnTipoEmail.setSelection(Integer.parseInt(contato.getTipoEmail()));
        edtEndereco.setText(contato.getEndereco());
        spnTipoEndereco.setSelection(Integer.parseInt(contato.getTipoendereco()));

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(contato.getDataEsp());

        edtDataEsp.setText(dt);
        spnTipoDataEsp.setSelection(Integer.parseInt(contato.getTipoDataEsp()));

        edtGrupos.setText(contato.getGrupos());
    }

    public void excluir()
    {
        try {
            repositorioContato.excluir(contato.getId());
        }catch (Exception ex)
        {
            MessageBox.show(this, "Erro", "Erro ao excluir da lista: " + ex.getMessage());
        }
    }

    public void salvar()
    {
        try {

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());
            contato.setGrupos(edtGrupos.getText().toString());

            contato.setTipoTel(String.valueOf(spnTipoTel.getSelectedItemPosition()));
            contato.setTipoEmail(String.valueOf(spnTipoEmail.getSelectedItemPosition()));
            contato.setTipoendereco(String.valueOf(spnTipoEndereco.getSelectedItemPosition()));
            contato.setTipoDataEsp(String.valueOf(spnTipoDataEsp.getSelectedItemPosition()));

            if (contato.getId() == 0)
                repositorioContato.inserir(contato);
            else
                repositorioContato.alterar(contato);

        }catch (Exception ex) {
            MessageBox.show(this, "Erro", "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    private void exibeData()
    {
        Calendar calendar = Calendar.getInstance();

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);
        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener
    {


        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
            exibeData();
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener
    {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            Date data = calendar.getTime();

            DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
            String dt = format.format(data);
            edtDataEsp.setText(dt);

            contato.setDataEsp(data);

        }
    }
}
