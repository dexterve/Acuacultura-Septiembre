package itvo.acuacultura.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdminBD extends SQLiteOpenHelper {

    String sqlRegistroTrucha = "CREATE TABLE RegistroTrucha " +
            "(ID integer primary key autoincrement, Fecha TEXT, " +
            "Temperatura TEXT, PH TEXT, Oxigeno TEXT, Turbidez TEXT);";

    String sqlRegistroTilapia = "CREATE TABLE RegistroTilapia " +
            "(ID integer primary key autoincrement, Fecha TEXT, " +
            "Temperatura TEXT, PH TEXT, Oxigeno TEXT, Turbidez TEXT);";

    String sqlCrecimientoTilapia = "CREATE TABLE CrecimientoTilapia " +
            "(ID integer primary key autoincrement, Fecha TEXT, " +
            "Peso FLOAT, Longitud FLOAT, PesoAumentado Float, LongitudAumentada Float, " +
            "PorcentajePeso Integer, PorcentajeLongitud Integer, TasaAlimentacion Float, AlimentoUsado Float);";

    String sqlCrecimientoTrucha = "CREATE TABLE CrecimientoTrucha " +
            "(ID integer primary key autoincrement, Fecha TEXT, " +
            "Peso Float, Longitud Float, PesoAumentado Float, LongitudAumentada Float, " +
            "PorcentajePeso Integer, PorcentajeLongitud Integer, TasaAlimentacion Float,  AlimentoUsado Float);";

    public AdminBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL(sqlRegistroTrucha);
        db.execSQL(sqlRegistroTilapia);
        db.execSQL(sqlCrecimientoTilapia);
        db.execSQL(sqlCrecimientoTrucha);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AltaRegTrucha(String Temperatura, String PH, String Oxigeno, String Turbidez){

        Date d=new Date();
        SimpleDateFormat fecc=new SimpleDateFormat("d 'de' MMMM' del' yyyy");
        String fechacComplString = fecc.format(d);

        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("Fecha", fechacComplString);
        registro.put("Temperatura", Temperatura);
        registro.put("PH", PH);
        registro.put("Oxigeno", Oxigeno);
        registro.put("Turbidez", Turbidez);
        bd.insert("RegistroTrucha", null, registro);
    }
    public void AltaRegTilapia(String Temperatura, String PH, String Oxigeno, String Turbidez){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registro = new ContentValues();

        Date d=new Date();
        SimpleDateFormat fecc=new SimpleDateFormat("d 'de' MMMM' del' yyyy");
        String fechacComplString = fecc.format(d);

        registro.put("Fecha", fechacComplString);
        registro.put("Temperatura", Temperatura);
        registro.put("PH", PH);
        registro.put("Oxigeno", Oxigeno);
        registro.put("Turbidez", Turbidez);
        bd.insert("RegistroTilapia", null, registro);
    }

    public void AltaCreTilapia(float peso, float longitud, float pesoAu, float lonAu, int porPes, int porLon, float tasa, float AUsado) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registro = new ContentValues();

        Date d=new Date();
        SimpleDateFormat fecc=new SimpleDateFormat("d 'de' MMMM' del' yyyy");
        String fechacComplString = fecc.format(d);

        registro.put("Fecha", fechacComplString);
        registro.put("Peso", peso);
        registro.put("Longitud", longitud);
        registro.put("PesoAumentado", pesoAu);
        registro.put("LongitudAumentada", lonAu);
        registro.put("PorcentajePeso", porPes);
        registro.put("PorcentajeLongitud", porLon);
        registro.put("TasaAlimentacion", tasa);
        registro.put("AlimentoUsado", AUsado);
        bd.insert("CrecimientoTilapia", null, registro);
    }

    public void AltaCreTrucha(float peso, float longitud, float pesoAu, float lonAu, int porPes, int porLon, float tasa, float AUsado) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registro = new ContentValues();

        Date d=new Date();
        SimpleDateFormat fecc=new SimpleDateFormat("d 'de' MMMM' del' yyyy");
        String fechacComplString = fecc.format(d);

        registro.put("Fecha", fechacComplString);
        registro.put("Peso", peso);
        registro.put("Longitud", longitud);
        registro.put("PesoAumentado", pesoAu);
        registro.put("LongitudAumentada", lonAu);
        registro.put("PorcentajePeso", porPes);
        registro.put("PorcentajeLongitud", porLon);
        registro.put("TasaAlimentacion", tasa);
        registro.put("AlimentoUsado", AUsado);
        bd.insert("CrecimientoTrucha", null, registro);
    }

    public ArrayList ComparacionTilapia(){
        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTilapia order by ID desc limit 1", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    float peso= cursor.getFloat(cursor.getColumnIndex("Peso"));
                    float longitud= cursor.getFloat(cursor.getColumnIndex("Longitud"));
                    datos.add(peso);
                    datos.add(longitud);

                }while (cursor.moveToNext());
            }
        }
        return datos;

    }

    public ArrayList ComparacionTrucha(){
        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTrucha order by ID desc limit 1", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    float peso= cursor.getFloat(cursor.getColumnIndex("Peso"));
                    float longitud= cursor.getFloat(cursor.getColumnIndex("Longitud"));
                    datos.add(peso);
                    datos.add(longitud);

                }while (cursor.moveToNext());
            }
        }
        return datos;

    }

    public ArrayList GraficaPesoTilapia() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTilapia", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    float peso= cursor.getFloat(cursor.getColumnIndex("Peso"));

                    datos.add(peso);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public ArrayList GraficaLongitudTilapia() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTilapia", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    float longitud= cursor.getFloat(cursor.getColumnIndex("Longitud"));

                    datos.add(longitud);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public ArrayList GraficaPesoTrucha() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTrucha", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    float peso= cursor.getFloat(cursor.getColumnIndex("Peso"));

                    datos.add(peso);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }
    public ArrayList GraficaLongitudTrucha() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTrucha", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    float longitud= cursor.getFloat(cursor.getColumnIndex("Longitud"));

                    datos.add(longitud);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public ArrayList ListarCrecimientoTrucha() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTrucha", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {

                    int id =cursor.getInt(cursor.getColumnIndex("ID"));
                    String fecha = cursor.getString(cursor.getColumnIndex("Fecha"));
                    float peso= cursor.getFloat(cursor.getColumnIndex("Peso"));
                    float longitud= cursor.getFloat(cursor.getColumnIndex("Longitud"));
                    float pesoAu= cursor.getFloat(cursor.getColumnIndex("PesoAumentado"));
                    float longitudAu= cursor.getFloat(cursor.getColumnIndex("LongitudAumentada"));
                    float pesoPor= cursor.getFloat(cursor.getColumnIndex("PorcentajePeso"));
                    float longitudPor= cursor.getFloat(cursor.getColumnIndex("PorcentajeLongitud"));

                    ArrayList elemento = new ArrayList();
                    elemento.add(id);
                    elemento.add(fecha);
                    elemento.add(peso);
                    elemento.add(longitud);
                    elemento.add(pesoAu);
                    elemento.add(longitudAu);
                    elemento.add(pesoPor);
                    elemento.add(longitudPor);
                    datos.add(elemento);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public ArrayList ListarCrecimientoTilapia() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from CrecimientoTilapia", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    int id =cursor.getInt(cursor.getColumnIndex("ID"));
                    String fecha = cursor.getString(cursor.getColumnIndex("Fecha"));
                    float peso= cursor.getFloat(cursor.getColumnIndex("Peso"));
                    float longitud= cursor.getFloat(cursor.getColumnIndex("Longitud"));
                    float pesoAu= cursor.getFloat(cursor.getColumnIndex("PesoAumentado"));
                    float longitudAu= cursor.getFloat(cursor.getColumnIndex("LongitudAumentada"));
                    float pesoPor= cursor.getFloat(cursor.getColumnIndex("PorcentajePeso"));
                    float longitudPor= cursor.getFloat(cursor.getColumnIndex("PorcentajeLongitud"));

                    ArrayList elemento = new ArrayList();
                    elemento.add(id);
                    elemento.add(fecha);
                    elemento.add(peso);
                    elemento.add(longitud);
                    elemento.add(pesoAu);
                    elemento.add(longitudAu);
                    elemento.add(pesoPor);
                    elemento.add(longitudPor);
                    datos.add(elemento);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public ArrayList ListarRegTrucha() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from RegistroTrucha", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    int id =cursor.getInt(cursor.getColumnIndex("ID"));
                    String fecha = cursor.getString(cursor.getColumnIndex("Fecha"));
                    String temp = cursor.getString(cursor.getColumnIndex("Temperatura"));
                    String ph = cursor.getString(cursor.getColumnIndex("PH"));
                    String oxi = cursor.getString(cursor.getColumnIndex("Oxigeno"));
                    String tur = cursor.getString(cursor.getColumnIndex("Turbidez"));

                    ArrayList elemento = new ArrayList();
                    elemento.add(id);
                    elemento.add(fecha);
                    elemento.add(temp);
                    elemento.add(ph);
                    elemento.add(oxi);
                    elemento.add(tur);
                    datos.add(elemento);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public ArrayList ListarRegTilapia() {

        ArrayList datos = new ArrayList();

        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();
        cursor =  bd.rawQuery("Select * from RegistroTilapia", null );

        if (cursor!= null){

            if (cursor.moveToFirst()){

                do {
                    int id =cursor.getInt(cursor.getColumnIndex("ID"));
                    String fecha = cursor.getString(cursor.getColumnIndex("Fecha"));
                    String temp = cursor.getString(cursor.getColumnIndex("Temperatura"));
                    String ph = cursor.getString(cursor.getColumnIndex("PH"));
                    String oxi = cursor.getString(cursor.getColumnIndex("Oxigeno"));
                    String tur = cursor.getString(cursor.getColumnIndex("Turbidez"));

                    ArrayList elemento = new ArrayList();
                    elemento.add(id);
                    elemento.add(fecha);
                    elemento.add(temp);
                    elemento.add(ph);
                    elemento.add(oxi);
                    elemento.add(tur);
                    datos.add(elemento);

                }while (cursor.moveToNext());
            }
        }
        return datos;
    }

    public int EliminarCATilapia(int n){
        SQLiteDatabase bd = this.getWritableDatabase();
        int resultado = bd.delete("RegistroTilapia","ID = '"+n+"'", null);
        bd.close();
        return resultado;
    }
    public int EliminarCATrucha(int n){
        SQLiteDatabase bd = this.getWritableDatabase();
        int resultado = bd.delete("RegistroTrucha","ID = '"+n+"'", null);
        bd.close();
        return resultado;
    }

    public int EliminarCreTilapia(int n){
        SQLiteDatabase bd = this.getWritableDatabase();
        int resultado = bd.delete("CrecimientoTilapia","ID = '"+n+"'", null);
        bd.close();
        return resultado;
    }

    public int EliminarCreTrucha(int n){
        SQLiteDatabase bd = this.getWritableDatabase();
        int resultado = bd.delete("CrecimientoTrucha","ID = '"+n+"'", null);
        bd.close();
        return resultado;
    }


    public ArrayList GraficaCA(String Seleccion, String re) {

        ArrayList datos = new ArrayList();
        Cursor cursor;
        SQLiteDatabase bd = this.getReadableDatabase();

        switch (re) {
            case "Trucha":
                cursor = bd.rawQuery("Select * from RegistroTrucha", null);
                switch (Seleccion) {
                    case "Temperatura":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("Temperatura"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                    case "Oxigeno":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("Oxigeno"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                    case "PH":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("PH"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                    case "Turbidez":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("Turbidez"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                }

                break;
            case "Tilapia":
                cursor = bd.rawQuery("Select * from RegistroTilapia", null);
                switch (Seleccion) {
                    case "Temperatura":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("Temperatura"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                    case "Oxigeno":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("Oxigeno"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                    case "PH":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("PH"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                    case "Turbidez":
                        if (cursor != null) {
                            if (cursor.moveToFirst()) {
                                do {
                                    float peso = cursor.getFloat(cursor.getColumnIndex("Turbidez"));
                                    datos.add(peso);
                                } while (cursor.moveToNext());
                            }
                        }
                        break;
                }
                break;
        }

        return datos;
    }


    public void ModificarMateria(int id, String Nombre,String Credito) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("ID", id);
        registro.put("Nombre", Nombre);
        registro.put("Creditos", Credito);
        db.update("Materia", registro, "ID='" + id + "'", null);
        db.close();
    }

    public ArrayList buscarCreTilapia(int id){

        ArrayList tilapia=new ArrayList();
        SQLiteDatabase bd=this.getReadableDatabase();
        String sql="SELECT * FROM CrecimientoTilapia WHERE ID='"+id+"';";
        Cursor cursor;
        cursor=bd.rawQuery(sql,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){

                tilapia.add(cursor.getString(cursor.getColumnIndex("ID")));
                tilapia.add(cursor.getString(cursor.getColumnIndex("Fecha")));
                tilapia.add(cursor.getString(cursor.getColumnIndex("Peso")));
                tilapia.add(cursor.getString(cursor.getColumnIndex("Longitud")));
            }
        }
        return tilapia;
    }

    public ArrayList buscarCreTrucha(int id){

        ArrayList tilapia=new ArrayList();
        SQLiteDatabase bd=this.getReadableDatabase();
        String sql="SELECT * FROM CrecimientoTrucha WHERE ID='"+id+"';";
        Cursor cursor;
        cursor=bd.rawQuery(sql,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){

                tilapia.add(cursor.getString(cursor.getColumnIndex("ID")));
                tilapia.add(cursor.getString(cursor.getColumnIndex("Fecha")));
                tilapia.add(cursor.getString(cursor.getColumnIndex("Peso")));
                tilapia.add(cursor.getString(cursor.getColumnIndex("Longitud")));
            }
        }
        return tilapia;
    }


}


