package itvo.acuacultura.View.Alimentacion;

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
import itvo.acuacultura.View.Alimentacion.Fragments.AlimentacionAyudaFragment;
import itvo.acuacultura.View.Alimentacion.Fragments.AlimentacionFragment;
import itvo.acuacultura.View.Otro.Fragments.AcercaFragment;
import itvo.acuacultura.View.Otro.Fragments.ContactoFragment;
import itvo.acuacultura.View.Otro.Fragments.DialogoListaMenu;

public class AlimentacionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String mensaje;
    String re="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Alimentación");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomBar buttombar=(BottomBar)findViewById(R.id.BottomBarAlimentacion);
        buttombar.setDefaultTab(R.id.calculoA);

        re = getIntent().getStringExtra("re");
        mensaje = getIntent().getStringExtra("num");

        buttombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.calculoA:
                        AlimentacionFragment homeCrecimientoFragment = new AlimentacionFragment();
                        homeCrecimientoFragment.Re(re, mensaje);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerAlimentacion, homeCrecimientoFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.helpA:

                        AlimentacionAyudaFragment helpCAFragment = new AlimentacionAyudaFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerAlimentacion, helpCAFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();

                        break;
                }
            }
        });

        //showToolbar("Alimentación", true);


    }


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


