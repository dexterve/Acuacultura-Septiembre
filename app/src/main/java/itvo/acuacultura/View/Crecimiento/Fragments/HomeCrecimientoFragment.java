package itvo.acuacultura.View.Crecimiento.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.R;
import itvo.acuacultura.View.Crecimiento.TasaAlimentacionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCrecimientoFragment extends Fragment {

    EditText peso, longitud;
    TextInputLayout tilPeso, tilLongitud;
    TextView num;
    int cont=0;
    int n=1;
    int numRegistros;
    float registro[][];
    int almacenar=0;
    ArrayList valores = new ArrayList();
    String re;
    Button agregar;
    View view;

    public HomeCrecimientoFragment() {
        // Required empty public constructorz
    }

    public void Re(String re, String nume){
        this.re=re;
        numRegistros= Integer.parseInt(nume);
        registro = new float[2][numRegistros];
       // Toast.makeText(getContext(), numRegistros+"", Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home_crecimiento, container, false);
        vistas(view);
        peso.requestFocus();
        num.setText("Registro: 1");
        //showToolbar("Crecimiento", true, view);
        peso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                peso.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        longitud.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                longitud.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
       // Toast.makeText(getContext(), re, Toast.LENGTH_LONG).show();
        return view;
    }


    /*public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }*/

public void vistas(View view){
        peso        =(EditText) view.findViewById(R.id.txtPeso);
        longitud    =(EditText) view.findViewById(R.id.txtLongitud);
        num         =(TextView) view.findViewById(R.id.lblNumTru);
        tilPeso     =(TextInputLayout) view.findViewById(R.id.tilPeso);
        tilLongitud =(TextInputLayout) view.findViewById(R.id.tilLongitud);
        agregar     =(Button)view.findViewById(R.id.btnAgregar);
    }
    private void validarDatos() {
        String p = tilPeso.getEditText().getText().toString();
        String l = tilLongitud.getEditText().getText().toString();

        boolean a = esPeso(p);
        boolean b = esLongitud(l);

        if (a && b) {
            calcular(p, l);
            peso.setText("");
            longitud.setText("");
            peso.requestFocus();
        }

    }
    public void calcular(String p, String l) {
        switch (re){
            case "Tilapia":
                Tilapia(p, l);
                break;
            case "Trucha":
                Trucha(p, l);
                break;
        }
    }
    private void Trucha(String p, String l) {
        if (cont < numRegistros) {
            if (cont < numRegistros - 1) {
                num.setText(String.valueOf("Registro: " + (n + 1)));
            }
            registro[0][cont] = Float.parseFloat(p);
            registro[1][cont] = Float.parseFloat(l);
            cont++;
            n++;

        }
        if (cont == numRegistros) {

            float SPeso = 0;
            float SLongitud = 0;
            int i = 0;
            Toast.makeText(getContext(), "Registro Completo", Toast.LENGTH_SHORT).show();

            //            Toast.makeText(this,"peso: "+last.get(0)+"longitud: "+last.get(1),Toast.LENGTH_LONG).show();

            if (almacenar == 0) {

                while (i < numRegistros) {
                    SPeso += registro[0][i];
                    SLongitud += registro[1][i];
                    i++;
                }
                ArrayList last;
                AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);

                SPeso = SPeso / numRegistros;
                SLongitud = SLongitud / numRegistros;
                float crePeso=0;
                float creLon=0;

                float crePeso1;
                float creLon1;

                try{
                    last = bd.ComparacionTrucha();
                    crePeso1 = (float) last.get(0);
                    creLon1 = (float) last.get(1);
                }catch (Exception e){
                    crePeso1=0;
                    creLon1=0;
                }

                crePeso=SPeso-crePeso1;
                creLon=SLongitud-creLon1;

                float CRP = (crePeso / crePeso1);
                float CRL = (creLon / creLon1);

                int CRP1 = (int) (CRP * 100);
                int CRL1 = (int) (CRL * 100);
                almacenar = 1;

                Toast.makeText(getContext(), CRP + "..." + CRL, Toast.LENGTH_SHORT).show();
                /*AdminBD db = new AdminBD(getContext(), "AcuaCultura", null, 1);
                db.AltaCreTrucha(SPeso, SLongitud, crePeso, creLon, (int)CRP, (int) CRL);

                Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();*/


                valores.add(SPeso);
                valores.add(SLongitud);
                valores.add(crePeso);
                valores.add(creLon);
                valores.add(CRP1);
                valores.add(CRL1);


                Intent intent = new Intent(getContext(), TasaAlimentacionActivity.class);
                intent.putStringArrayListExtra("valor", valores);
                intent.putExtra("re",re);
                intent.putExtra("num",String.valueOf(numRegistros));
                startActivity(intent);
            }

        }
    }
    public void Tilapia(String p, String l){
            if (cont < numRegistros) {
                if (cont < numRegistros - 1) {
                    num.setText(String.valueOf("Registro: " + (n + 1)));
                }
                registro[0][cont] = Float.parseFloat(p);
                registro[1][cont] = Float.parseFloat(l);
                cont++;
                n++;
            }
        if (cont == numRegistros) {
            float SPeso = 0;
            float SLongitud = 0;
            int i = 0;
            Toast.makeText(getContext(), "Registro Completo", Toast.LENGTH_SHORT).show();

            if (almacenar == 0) {
                while (i < numRegistros) {
                    SPeso += registro[0][i];
                    SLongitud += registro[1][i];
                    i++;
                }
                ArrayList last;
                AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);

                SPeso = SPeso / numRegistros;
                SLongitud = SLongitud / numRegistros;
                float crePeso=0;
                float creLon=0;

                float crePeso1;
                float creLon1;

                try{
                    last = bd.ComparacionTilapia();
                    crePeso1 = (float) last.get(0);
                    creLon1 = (float) last.get(1);
                }catch (Exception e){
                    crePeso1=0;
                    creLon1=0;
                }
                crePeso=SPeso-crePeso1;
                creLon=SLongitud-creLon1;

                float CRP = (crePeso / crePeso1);
                float CRL = (creLon / creLon1);

                int CRP1 = (int) (CRP * 100);
                int CRL1 = (int) (CRL * 100);
                almacenar = 1;
                /*AdminBD db = new AdminBD(getContext(), "AcuaCultura", null, 1);
                db.AltaCreTilapia(SPeso, SLongitud, crePeso, creLon, (int)CRP, (int)CRL);

                Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();*/

                valores.add(SPeso);
                valores.add(SLongitud);
                valores.add(crePeso);
                valores.add(creLon);
                valores.add(CRP1);
                valores.add(CRL1);

                Intent intent = new Intent(getActivity(), TasaAlimentacionActivity.class);
                intent.putStringArrayListExtra("valor", valores);
                intent.putExtra("re",re);
                intent.putExtra("num",String.valueOf(numRegistros));
                startActivity(intent);
            }
        }
    }

    private boolean esPeso(String peso) {
        if (peso.matches("")) {
            tilPeso.setError("Campo Vacio");
            return false;
        } else {
            tilPeso.setError(null);
        }
        return true;
    }

    private boolean esLongitud(String longitud) {
        if (longitud.matches("")) {
            tilLongitud.setError("Campo Vacio");
            return false;
        } else {
            tilLongitud.setError(null);
        }

        return true;
    }

}