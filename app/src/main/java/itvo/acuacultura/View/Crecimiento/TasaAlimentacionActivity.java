package itvo.acuacultura.View.Crecimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.R;

public class TasaAlimentacionActivity extends AppCompatActivity {

    ArrayList porcentaje= new ArrayList();
    String porcentajeAlimentacion[][]= new String[2][10];
    String SeleccionSpinner1="", re="", num;
    TextView resultado, datos, medidas, por;
    EditText numOr;
    ArrayList valores = new ArrayList();
    Button calcular;
    TextInputLayout tilNumOrg;
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasa_alimentacion);

        re = getIntent().getStringExtra("re");
        num = getIntent().getStringExtra("num");
        // Toast.makeText(this, num+re, Toast.LENGTH_LONG).show();

        resultado = (TextView) findViewById(R.id.lblResultado);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        datos=(TextView)findViewById(R.id.lblDatos);
        medidas=(TextView)findViewById(R.id.lblMEdidas);
        por=(TextView)findViewById(R.id.lblPorce);
        numOr=(EditText)findViewById(R.id.txtNumOrganismos);
        tilNumOrg=(TextInputLayout)findViewById(R.id.tilNumOrganismos);
        calcular=(Button)findViewById(R.id.btnCalcularr) ;
        agregarPorcentaje();

        valores = getIntent().getStringArrayListExtra("valor");

        datos.setText("Promedios"+"\nPeso Promedio: "+valores.get(0)+"\nLongitud Promedio: "+valores.get(1));
        medidas.setText("Crecimiento"+"\nCrecimiento en Peso: "+valores.get(2)+"\nLongitud de Crecimiento: "+valores.get(3));
        por.setText("Porcentajes de crecimiento" + "\nPorcentaje de Peso: " + valores.get(4) + "%" + "\nPorcentaje de longitud: " + valores.get(5) + "%");
        //Toast.makeText(this,c,Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TasaAlimentacionActivity.this, android.R.layout.simple_list_item_1, porcentaje);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SeleccionSpinner1 = String.valueOf(spinner1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        numOr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                numOr.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = tilNumOrg.getEditText().getText().toString();

                boolean a = esZValido(p);

                if (a) {
                    float resul=0;
                    float org=(Float.parseFloat(p));
                    float prom= (float) valores.get(0);
                    float sele= 0;
                    for(int i=0; i<porcentaje.size();i++){
                        if (SeleccionSpinner1==porcentajeAlimentacion[0][i]){
                            sele= Float.parseFloat(porcentajeAlimentacion[1][i]);
                            break;
                        }
                    }
                    resul=prom*org*sele;
                    resul = resul / 1000;
                    resultado.setText("Cantidad de Alimento por dia: " + resul + " Kilogramos");

                    AdminBD db = new AdminBD(getApplicationContext(), "AcuaCultura", null, 1);

                    switch (re) {
                        case "Tilapia":
                            db.AltaCreTilapia((float) valores.get(0), (float) valores.get(1), (float) valores.get(2),
                                    (float) valores.get(3), (int) valores.get(4), (int) valores.get(5), resul, 0);
                            Toast.makeText(getApplicationContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            break;
                        case "Trucha":
                            db.AltaCreTrucha((float) valores.get(0), (float) valores.get(1), (float) valores.get(2),
                                    (float) valores.get(3), (int) valores.get(4), (int) valores.get(5), resul, 0);
                            Toast.makeText(getApplicationContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });


        showToolbar("Tasa de Alimentacion Diaria", true);
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public void agregarPorcentaje(){
        porcentaje.add("1 %");
        porcentaje.add("2 %");
        porcentaje.add("3 %");
        porcentaje.add("4 %");
        porcentaje.add("5 %");
        porcentaje.add("6 %");
        porcentaje.add("7 %");
        porcentaje.add("8 %");
        porcentaje.add("9 %");
        porcentaje.add("10 %");

        for(int i=0; i<porcentaje.size(); i++){
            porcentajeAlimentacion[0][i]=porcentaje.get(i).toString();
        }

        porcentajeAlimentacion[1][0]="0.01";
        porcentajeAlimentacion[1][1]="0.02";
        porcentajeAlimentacion[1][2]="0.03";
        porcentajeAlimentacion[1][3]="0.04";
        porcentajeAlimentacion[1][4]="0.05";
        porcentajeAlimentacion[1][5]="0.06";
        porcentajeAlimentacion[1][6]="0.07";
        porcentajeAlimentacion[1][7]="0.08";
        porcentajeAlimentacion[1][8]="0.09";
        porcentajeAlimentacion[1][9]="0.10";

    }
    private boolean esZValido(String num) {
        if (num.matches("")) {
            tilNumOrg.setError("Campo Vacio");
            return false;
        } else {
            tilNumOrg.setError(null);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), CrecimientoActivity.class);
                intent.putExtra("re", re);
                intent.putExtra("num",num);
                startActivity(intent);
                /*DialogoListaCrecimiento lista = new DialogoListaCrecimiento();
                lista.Re(re);
                lista.show(getSupportFragmentManager(), "Lista");*/
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();onBackPressed();
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(getApplicationContext(), CrecimientoActivity.class);
            intent.putExtra("re", re);
            intent.putExtra("num",num);
            startActivity(intent);
        }

        /*DialogoListaCrecimiento lista = new DialogoListaCrecimiento();
        lista.Re(re);
        lista.show(getSupportFragmentManager(), "Lista");*/
        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

}
