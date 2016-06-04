package edm.kassimentz.exemplofragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CliqueLista{

    private static String timeBck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void cliclou(String time) {

        //eventBus --> lib para mandar msgs de uma activy para outra


        //faz tudo com fragments, inclusive tentar encontrar fragments na sua tela
        FragmentManager fragmentManager = getFragmentManager();
        DetalheFrag detalheFrag = (DetalheFrag) fragmentManager.findFragmentById(R.id.detalheFrag);

        if(detalheFrag != null && detalheFrag.isInLayout()){
            // mudar o texto do frag da direita
            detalheFrag.setNome(time);
        }else{

            //chamar outra tela
            timeBck = time;
            Intent intent = new Intent(this, DetalheActivity.class);
            intent.putExtra("time", time);
            startActivityForResult(intent, 1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_CANCELED){
            cliclou(timeBck);
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
