package senac.edm.pos.exemplobutterknife;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Cassio on 06/08/16.
 */
public class FragmentInicial extends Fragment {

    @BindViews({R.id.edtCartaoCredito, R.id.edtValidade})
    List<EditText> viewsCompra;

    @BindViews({R.id.edtAgencia, R.id.edtContaBancaria})
    List<EditText> viewsVenda;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_inicial, container, false);
        unbinder = ButterKnife.bind(this, view);

        ButterKnife.apply(viewsCompra, DISABLE);
        ButterKnife.apply(viewsVenda, ENABLE, false);

        return view;
    }

    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>(){
        @Override public void apply(View view, int index){
            view.setEnabled(false);
        }
    };

    static final ButterKnife.Setter<View, Boolean> ENABLE = new ButterKnife.Setter<View, Boolean>(){
        @Override public void set(View view, Boolean value, int index){
            view.setEnabled(true);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnComprar)
    public void comprar(){
        ButterKnife.apply(viewsCompra, ENABLE, true);
        ButterKnife.apply(viewsVenda, ENABLE, false);
    }

    @OnClick(R.id.btnVender)
    public void vender(){
        ButterKnife.apply(viewsCompra, ENABLE, false);
        ButterKnife.apply(viewsVenda, ENABLE, true);
    }
}
