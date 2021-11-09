package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "Agenda.db";
    private static final int DB_VERSION = 3;

    // Tabla USUARIOS
    private static final String TABLE_USUARIO = "Usuario";
    private static final String NOMBRE_USUARIO = "Nombre";
    private static final String PASSWORD = "Password";

    // CREATE TABLE USUARIOS
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO + "(" +
            NOMBRE_USUARIO + " TEXT PRIMARY KEY NOT NULL, " +
            PASSWORD + " TEXT NOT NULL " +
            ")";

    // Tabla TAREAS
    private static final String TABLE_TAREA = "Tarea";
    private static final String ID = "ID";
    private static final String NOMBRE_TAREA = "Nombre";
    private static final String DESCRIPCION = "Descripcion";
    private static final String FECHA = "Fecha";
    private static final String COSTE = "Coste";
    private static final String PRIORIDAD = "Prioridad";
    private static final String ESTADO = "Estado";
    private static final String USUARIO = "Usuario";

    // CREATE TABLE TAREAS
    private static final String CREATE_TABLE_TAREA = "CREATE TABLE " + TABLE_TAREA + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NOMBRE_TAREA + " TEXT NOT NULL, " +
            DESCRIPCION + " TEXT NOT NULL, " +
            FECHA + " TEXT NOT NULL, " +
            COSTE + " NUMERIC NOT NULL, " +
            PRIORIDAD + " TEXT NOT NULL, " +
            ESTADO + " INTEGER NOT NULL, " +
            USUARIO + " TEXT NOT NULL, " +
            "FOREIGN KEY(" + USUARIO + ") REFERENCES " + TABLE_USUARIO + "(" + NOMBRE_USUARIO + ")" +
            ")";

    private final Context context;

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIO);
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIO + " VALUES ('Admin', 'Admin')");
        sqLiteDatabase.execSQL(CREATE_TABLE_TAREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREA);
        onCreate(sqLiteDatabase);
    }

    // Valida el login
    public boolean validateLogin (Usuario user) {
    boolean isValid = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + NOMBRE_USUARIO + ", " + PASSWORD + " FROM " + TABLE_USUARIO + " WHERE " + NOMBRE_USUARIO + " = '" + user.getNombre() + "' AND " + PASSWORD + " = '" + user.getPassword() + "'", null);

        if (c.getCount() > 0) isValid = true;

        c.close();
        db.close();

        return isValid;
    }

    public boolean updatePassword (Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, u.getPassword());

        if (db.update(TABLE_USUARIO, values, NOMBRE_USUARIO + " = ?", new String[]{u.getNombre()}) != -1) return true;
        else return false;
    }

    public boolean insertTarea (Tarea tarea) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE_TAREA, tarea.getNombre());
        values.put(DESCRIPCION, tarea.getDescripcion());
        values.put(FECHA, tarea.getFecha());
        values.put(COSTE, tarea.getCoste());
        values.put(PRIORIDAD, tarea.getPrioridad());
        values.put(ESTADO, tarea.getEstado());
        values.put(USUARIO, tarea.getUsuario().getNombre());

        if (db.insert(TABLE_TAREA, null, values) != -1) return true;
        else return false;
    }

    public ArrayList<Tarea> selectAllCompletedTasksByUser (String user) {
        ArrayList<Tarea> tasks = new ArrayList<Tarea>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + NOMBRE_TAREA + ", " + DESCRIPCION + ", " + FECHA + ", " + COSTE + ", " + PRIORIDAD + ", " + ESTADO + " FROM " + TABLE_TAREA + " WHERE " + USUARIO + " = '" + user + "' AND " + ESTADO + " = 1", null);

        while(c.moveToNext()) {
            Tarea task = new Tarea();

            task.setNombre(c.getString(0));
            task.setDescripcion(c.getString(1));
            task.setFecha(c.getString(2));
            task.setCoste(Double.parseDouble(c.getString(3)));
            task.setPrioridad(c.getString(4));
            task.setEstado(Integer.parseInt(c.getString(5)));

            tasks.add(task);
        }

        c.close();
        db.close();

        return tasks;
    }

    public ArrayList<Tarea> selectAllRemainingTasksByUser (String user) {
        ArrayList<Tarea> tasks = new ArrayList<Tarea>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + NOMBRE_TAREA + ", " + DESCRIPCION + ", " + FECHA + ", " + COSTE + ", " + PRIORIDAD + ", " + ESTADO + " FROM " + TABLE_TAREA + " WHERE " + USUARIO + " = '" + user + "' AND " + ESTADO + " = 0", null);

        while(c.moveToNext()) {
            Tarea task = new Tarea();

            task.setNombre(c.getString(0));
            task.setDescripcion(c.getString(1));
            task.setFecha(c.getString(2));
            task.setCoste(Double.parseDouble(c.getString(3)));
            task.setPrioridad(c.getString(4));
            task.setEstado(Integer.parseInt(c.getString(5)));

            tasks.add(task);
        }

        c.close();
        db.close();

        return tasks;
    }
}
