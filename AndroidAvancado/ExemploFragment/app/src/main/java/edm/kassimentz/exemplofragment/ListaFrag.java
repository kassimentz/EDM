package edm.kassimentz.exemplofragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 630910144 on 04/06/16.
 */
public class ListaFrag extends ListFragment {

    private String[] times = new String[]{
            "Sport", "Farroupilha", "Grêmio", "Juventude", "Caxias", "Aterro"
    };

    //quando a tela do fragment mudar de ciclo e cair aqui
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(
                new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        times));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((CliqueLista)getActivity()).cliclou(times[position]);
    }
}
