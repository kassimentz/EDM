package edm.kassimentz.exemplofragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 630910144 on 04/06/16.
 */
public class DetalheFrag extends Fragment{

    // para nao precisar ficar declarando e convertendo os textviews, só usar annotations
    // usar as libs com anotacoes
    // ButterKnife ****
    // RoboGuice
    // Annotations

    private TextView txtNomeTime;

    //como o fragment vai ter uma view, preciso sobrescrever este metodo.
    //reutilizar o ultimo recurso criado na classe

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detalhe, null);
        txtNomeTime = (TextView) view.findViewById(R.id.textView);
        return view;
    }

    public void setNome(String nome){
        txtNomeTime.setText(nome);
    }
}
