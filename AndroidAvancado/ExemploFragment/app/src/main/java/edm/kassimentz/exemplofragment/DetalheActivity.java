package edm.kassimentz.exemplofragment;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setResult(RESULT_CANCELED);
            finish();
        }
        setContentView(R.layout.activity_detalhe);

        //fazer isso só se tiver certeza que sempre sera chamada pela mesma activity e sempre recebera esse extra
        String time = getIntent().getStringExtra("time");
        FragmentManager fragmentManager = getFragmentManager();
        DetalheFrag detalheFrag = (DetalheFrag) fragmentManager.findFragmentById(R.id.detalheFrag);

        detalheFrag.setNome(time);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
