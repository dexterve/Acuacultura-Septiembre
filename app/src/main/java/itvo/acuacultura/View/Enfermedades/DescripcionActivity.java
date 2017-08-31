package itvo.acuacultura.View.Enfermedades;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import itvo.acuacultura.Model.Comunicador;
import itvo.acuacultura.Model.TextosEnfermedades;
import itvo.acuacultura.Model.drawImage;
import itvo.acuacultura.R;

public class DescripcionActivity extends AppCompatActivity {
    //@BindView(R.id.titleEnfermedad)
    ImageView enfermedad;
    TextView descripcion, causas, tratamiento, signos, prevencion, title, preve;
    String re, nom;
    TextosEnfermedades objTexto;
    Object obj;
    drawImage objIm;
    Drawable imgEnf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        enfermedad = (ImageView)findViewById(R.id.imageHeader);
        title= (TextView)findViewById(R.id.titleEnfermedad);

        descripcion  = (TextView)findViewById(R.id.lblDescripcion);
        causas       = (TextView)findViewById(R.id.lblCausas);
        tratamiento  = (TextView)findViewById(R.id.lblTratamiento);
        signos       = (TextView)findViewById(R.id.lblSignos);
        prevencion   = (TextView)findViewById(R.id.lblPrevencion);
        preve = (TextView) findViewById(R.id.Prevencion);

        objTexto= new TextosEnfermedades();
        nom=getIntent().getStringExtra("nom");
        re=getIntent().getStringExtra("re");
        title.setText(nom);
        showToolbar(nom, true);
        obj = Comunicador.getObj();
        objIm = (drawImage) obj;
        imgEnf = objIm.getEnfermedad();
        //Toast.makeText(this, objTexto.getPuntoBlanco(),Toast.LENGTH_SHORT).show();
        enfermedad.setImageDrawable(imgEnf);

        menu();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Fade());

        }
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsinToolbar);
    }
    public void menu(){
        switch (re){
            case "Trucha":
                switch (nom){
                    case "Ichtofoniasis (Punto Blanco)":
                        descripcion.setText(objTexto.getDescripcionPuntoBlanco());
                        causas.setText(objTexto.getCausasPuntoBlanco());
                        signos.setText(objTexto.getSignosPuntoBlanco());
                        prevencion.setText(objTexto.getPrevencionPuntoBlanco());
                        tratamiento.setText(objTexto.getTratamientoPuntoBlanco());
                        break;
                    case "Columnaris":
                        descripcion.setText(objTexto.getDescripcionColumnaris());
                        causas.setText(objTexto.getCausasColumnaris());
                        signos.setText(objTexto.getSignosColumnaris());
                        prevencion.setText(objTexto.getPrevencionColumnaris());
                        tratamiento.setText(objTexto.getTratamientoColumnaris());
                        break;
                    case "Vibriosis":
                        descripcion.setText(objTexto.getDescripcionVibriosis());
                        causas.setText(objTexto.getCausasVibriosis());
                        signos.setText(objTexto.getSignosVibriosis());
                        prevencion.setText(objTexto.getPrevencionVibriosis());
                        tratamiento.setText(objTexto.getTratamientoVibriosis());
                        preve.setText("");
                        break;
                    case "Ataque Bacteriano Aeromonas":
                        descripcion.setText(objTexto.getDescripcionAtaqueBacteriano());
                        causas.setText(objTexto.getCausasAtaqueBacteriano());
                        signos.setText(objTexto.getSignosAtaqueBacteriano());
                        prevencion.setText(objTexto.getPrevencionAtaqueBacteriano());
                        tratamiento.setText(objTexto.getTratamientoAtaqueBacteriano());
                        break;
                    case "Saprolegnia":
                        descripcion.setText(objTexto.getDescripcionSaprolegnia());
                        causas.setText(objTexto.getCausasSaprolegnia());
                        signos.setText(objTexto.getSignosSaprolegnia());
                        prevencion.setText(objTexto.getPrevencionSaprolegnia());
                        tratamiento.setText(objTexto.getTratamientoSaprolegnia());
                        preve.setText("");
                        break;
                    case "Furunculosis":
                        descripcion.setText(objTexto.getDescripcionFurunculosis());
                        causas.setText(objTexto.getCausasFurunculosis());
                        signos.setText(objTexto.getSignosFurunculosis());
                        prevencion.setText(objTexto.getPrevencionFurunculosis());
                        tratamiento.setText(objTexto.getTratamientoFurunculosis());
                        preve.setText("");
                        break;
                }

                break;
            case "Tilapia":
                switch (nom){
                    case "Ichtofoniasis (Punto Blanco)":
                        descripcion.setText(objTexto.getDescripcionPuntoBlanco());
                        causas.setText(objTexto.getCausasPuntoBlanco());
                        signos.setText(objTexto.getSignosPuntoBlanco());
                        prevencion.setText(objTexto.getPrevencionPuntoBlanco());
                        tratamiento.setText(objTexto.getTratamientoPuntoBlanco());
                        break;
                    case "Columnaris":
                        descripcion.setText(objTexto.getDescripcionColumnaris());
                        causas.setText(objTexto.getCausasColumnaris());
                        signos.setText(objTexto.getSignosColumnaris());
                        prevencion.setText(objTexto.getPrevencionColumnaris());
                        tratamiento.setText(objTexto.getTratamientoColumnaris());
                        break;
                    case "Vibriosis":
                        descripcion.setText(objTexto.getDescripcionVibriosis());
                        causas.setText(objTexto.getCausasVibriosis());
                        signos.setText(objTexto.getSignosVibriosis());
                        prevencion.setText(objTexto.getPrevencionVibriosis());
                        tratamiento.setText(objTexto.getTratamientoVibriosis());
                        preve.setText("");
                        break;
                    case "Ataque Bacteriano Aeromonas":
                        descripcion.setText(objTexto.getDescripcionAtaqueBacteriano());
                        causas.setText(objTexto.getCausasAtaqueBacteriano());
                        signos.setText(objTexto.getSignosAtaqueBacteriano());
                        prevencion.setText(objTexto.getPrevencionAtaqueBacteriano());
                        tratamiento.setText(objTexto.getTratamientoAtaqueBacteriano());
                        break;
                    case "Saprolegnia":
                        descripcion.setText(objTexto.getDescripcionSaprolegnia());
                        causas.setText(objTexto.getCausasSaprolegnia());
                        signos.setText(objTexto.getSignosSaprolegnia());
                        prevencion.setText(objTexto.getPrevencionSaprolegnia());
                        tratamiento.setText(objTexto.getTratamientoSaprolegnia());
                        preve.setText("");
                        break;
                    case "Furunculosis":
                        descripcion.setText(objTexto.getDescripcionFurunculosis());
                        causas.setText(objTexto.getCausasFurunculosis());
                        signos.setText(objTexto.getSignosFurunculosis());
                        prevencion.setText(objTexto.getPrevencionFurunculosis());
                        tratamiento.setText(objTexto.getTratamientoFurunculosis());
                        preve.setText("");
                        break;
                }
                break;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, EnfermedadesActivity.class);
                intent.putExtra("re", re);
                startActivity(intent);
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();onBackPressed();
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(this, EnfermedadesActivity.class);
            intent.putExtra("re", re);
            startActivity(intent);
        }
        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    public void imagen(View v){
        Intent i = new Intent(this, ImagenEnfermedadActivity.class);
        drawImage objImage = new drawImage(imgEnf);
        i.putExtra("nom", nom);
        i.putExtra("re", re);
        Comunicador.setObj(objImage);
        startActivity(i);
    }

}
