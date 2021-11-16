package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public boolean validateLogin(Usuario user) {
        boolean isValid = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + NOMBRE_USUARIO + ", " + PASSWORD + " FROM " + TABLE_USUARIO + " WHERE " + NOMBRE_USUARIO + " = '" + user.getNombre() + "' AND " + PASSWORD + " = '" + user.getPassword() + "'", null);

        if (c.getCount() > 0) isValid = true;

        c.close();
        db.close();

        return isValid;
    }

    // Actualiza la clave del usuario logeado
    public boolean updatePassword(Usuario u) {
        boolean res = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, u.getPassword());

        if (db.update(TABLE_USUARIO, values, NOMBRE_USUARIO + " = ?", new String[]{u.getNombre()}) != -1)
            res = true;

        return res;
    }

    // Devuelve un ArrayList<Tarea> con las tareas completadas del usuario logeado
    public ArrayList<Tarea> selectAllCompletedTasksByUser(String user) {
        ArrayList<Tarea> tasks = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + ID + ", " + NOMBRE_TAREA + ", " + DESCRIPCION + ", " + FECHA + ", " + COSTE + ", " + PRIORIDAD + ", " + ESTADO + " FROM " + TABLE_TAREA + " WHERE " + USUARIO + " = '" + user + "' AND " + ESTADO + " = 1", null);

        while (c.moveToNext()) {
            Tarea task = new Tarea();

            task.setId(c.getInt(0));
            task.setNombre(c.getString(1));
            task.setDescripcion(c.getString(2));
            task.setFecha(c.getString(3));
            task.setCoste(Double.parseDouble(c.getString(4)));
            task.setPrioridad(c.getString(5));
            task.setEstado(Integer.parseInt(c.getString(6)));

            tasks.add(task);
        }

        c.close();
        db.close();

        return tasks;
    }

    // Devuelve un ArrayList<Tarea> con las tareas incompletas del usuario logeado
    public ArrayList<Tarea> selectAllRemainingTasksByUser(String user) {
        ArrayList<Tarea> tasks = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + ID + ", " + NOMBRE_TAREA + ", " + DESCRIPCION + ", " + FECHA + ", " + COSTE + ", " + PRIORIDAD + ", " + ESTADO + " FROM " + TABLE_TAREA + " WHERE " + USUARIO + " = '" + user + "' AND " + ESTADO + " = 0", null);

        while (c.moveToNext()) {
            Tarea task = new Tarea();

            task.setId(c.getInt(0));
            task.setNombre(c.getString(1));
            task.setDescripcion(c.getString(2));
            task.setFecha(c.getString(3));
            task.setCoste(Double.parseDouble(c.getString(4)));
            task.setPrioridad(c.getString(5));
            task.setEstado(Integer.parseInt(c.getString(6)));

            tasks.add(task);
        }

        c.close();
        db.close();

        return tasks;
    }

    // Inserta una nueva tarea al usuario logeado y nos devuelve un booleano indicando si la inserción se completó correctamente
    public boolean insertTarea(Tarea task) {
        boolean res = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE_TAREA, task.getNombre());
        values.put(DESCRIPCION, task.getDescripcion());
        values.put(FECHA, task.getFecha());
        values.put(COSTE, task.getCoste());
        values.put(PRIORIDAD, task.getPrioridad());
        values.put(ESTADO, task.getEstado());
        values.put(USUARIO, task.getUsuario().getNombre());

        if (db.insert(TABLE_TAREA, null, values) != -1)
            res = true;

        return res;
    }

    // Actualiza una tarea y nos devuelve un booleano indicando si la inserción se completó correctamente,
    // en este caso como el usuario solo puede ver sus tareas no hay necesidad de añadir un filtro de WHERE Usuario = 'Admin'
    public boolean updateTask(Tarea task) {
        boolean res = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE_TAREA, task.getNombre());
        values.put(DESCRIPCION, task.getDescripcion());
        values.put(FECHA, task.getFecha());
        values.put(COSTE, task.getCoste());
        values.put(PRIORIDAD, task.getPrioridad());
        values.put(ESTADO, task.getEstado());

        if (db.update(TABLE_TAREA, values, ID + " = ?", new String[] {String.valueOf(task.getId())}) != -1)
            res = true;

        return res;
    }

    // Elimina una tarea en abse a su ID,
    // en este caso como el usuario solo puede ver sus tareas no hay necesidad de añadir un filtro de WHERE Usuario = 'Admin'
    public boolean deleteTaskById(int id) {
        boolean res = false;

        SQLiteDatabase db = this.getWritableDatabase();
        if (db.delete(TABLE_TAREA, ID + " = ?", new String[]{String.valueOf(id)}) != -1)
            res = true;

        return res;
    }
}
