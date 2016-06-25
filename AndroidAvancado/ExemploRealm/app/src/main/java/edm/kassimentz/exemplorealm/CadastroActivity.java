package edm.kassimentz.exemplorealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import io.realm.Realm;

public class CadastroActivity extends AppCompatActivity {
    /*
    * public String nome;
    public String descricao;
    public long termino;
    public String local;
    public boolean iniciada;*/

    TextView txtNome, txtDescricao, txtTermino, txtLocal;
    RadioButton rdIniciada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        txtTermino = (TextView) findViewById(R.id.txtDescricao);
        txtLocal = (TextView) findViewById(R.id.txtLocal);
        rdIniciada = (RadioButton) findViewById(R.id.rdIniciada);
    }

    public void salvar(View v){
        ((CoreApplication)getApplication()).realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Task task = new Task();
                task.nome = txtNome.getText().toString();
                task.descricao = txtDescricao.getText().toString();
                task.termino = System.currentTimeMillis();
                task.iniciada = Boolean.valueOf(rdIniciada.getText().toString());
                bgRealm.copyToRealmOrUpdate(task);
            }
        }, new Realm.Transaction.OnSuccess() {
            public void onSuccess() {
                Log.i("teste","cadastrado com sucesso");
                finish();
            }
        }, new Realm.Transaction.OnError() {
            public void onError(Throwable error) {}
        });
    }
}
