package itvo.acuacultura;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import itvo.acuacultura.View.Otro.Fragments.AcercaFragment;
import itvo.acuacultura.View.Otro.Fragments.ContactoFragment;
import itvo.acuacultura.View.Otro.Fragments.DialogoListaMenu;
import itvo.acuacultura.View.Otro.Fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    String sel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sel = getIntent().getStringExtra("inicio");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Inicio");

        try {
            switch (sel) {
                case "inicio":
                    //Toast.makeText(this, sel, Toast.LENGTH_LONG).show();
                    MainFragment mainFragment = new MainFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.rlMain, mainFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null).commit();
                    break;
            }

        } catch (Exception e) {
            AcercaFragment acercaFragment = new AcercaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.rlMain, acercaFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null).commit();
        }


        // showToolbar(getResources().getString(R.string.toolbar_title),false);
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


  /*  public void showPopup(){
        View menuItemView = findViewById(R.id.popup_menu);
        PopupMenu popup = new PopupMenu(this, menuItemView);
        MenuInflater inflate = popup.getMenuInflater();
        inflate.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();

    }*/


    public boolean onNavigationItemSelected(MenuItem item) {
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
