package itvo.acuacultura.View.Crecimiento;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import itvo.acuacultura.MainActivity;
import itvo.acuacultura.R;
import itvo.acuacultura.View.Crecimiento.Fragments.AyudaCrecimientoFragment;
import itvo.acuacultura.View.Crecimiento.Fragments.GraficaCrecimientoFragment;
import itvo.acuacultura.View.Crecimiento.Fragments.HomeCrecimientoFragment;
import itvo.acuacultura.View.Crecimiento.Fragments.RegistrosCrecimientoFragment;
import itvo.acuacultura.View.Otro.Fragments.AcercaFragment;
import itvo.acuacultura.View.Otro.Fragments.ContactoFragment;
import itvo.acuacultura.View.Otro.Fragments.DialogoListaMenu;

public class CrecimientoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String re="";
    String num="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crecimiento);
        re  = getIntent().getStringExtra("re");
        num =  getIntent().getStringExtra("num");

        //Toast.makeText(this, num, Toast.LENGTH_LONG).show();

        BottomBar buttombar=(BottomBar)findViewById(R.id.BottomBarCrecimiento);
        buttombar.setDefaultTab(R.id.calcular);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Crecimiento");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        buttombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.calcular:
                        HomeCrecimientoFragment homeCrecimientoFragment = new HomeCrecimientoFragment();
                        homeCrecimientoFragment.Re(re, num);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, homeCrecimientoFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.registro:
                        RegistrosCrecimientoFragment regFragment = new RegistrosCrecimientoFragment();
                        regFragment.Re(re, num);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, regFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.helpc:

                        AyudaCrecimientoFragment helpCAFragment = new AyudaCrecimientoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, helpCAFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();

                        break;
                    case R.id.graficar:
                        GraficaCrecimientoFragment graf = new GraficaCrecimientoFragment();
                        graf.Re(re);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, graf)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });
    }

    public void showToolbar(String title, boolean upButton) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("re", re);
                startActivity(intent);
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();onBackPressed();
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        String re = "";
        DialogoListaMenu lista;
        switch (id) {
            case R.id.Inicio:
                Intent searchIntent = new Intent(this, MainActivity.class);
                searchIntent.putExtra("inicio", "inicio");
                startActivity(searchIntent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                break;
            case R.id.Tilapia:
                re = "Tilapia";
                lista = new DialogoListaMenu();
                lista.Re(re);
                lista.show(getSupportFragmentManager(), "Lista");
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                break;
            case R.id.Trucha:
                re = "Trucha";
                lista = new DialogoListaMenu();
                lista.Re(re);
                lista.show(getSupportFragmentManager(), "Lista");
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                break;
            case R.id.acerca:
                AcercaFragment acercaFragment = new AcercaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.rlMain, acercaFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null).commit();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                break;
            case R.id.contacto:
                ContactoFragment contactoFragment = new ContactoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.rlMain, contactoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null).commit();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
