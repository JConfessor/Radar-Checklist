package br.imd.ufrn.checklist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "checklist.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VEICULOS = "veiculos";
    private static final String COLUMN_VEICULO_ID = "id";
    private static final String COLUMN_VEICULO_PLACA = "placa";
    private static final String COLUMN_VEICULO_FROTA = "frota";
    private static final String COLUMN_VEICULO_MODELO = "modelo";
    private static final String SQL_CREATE_VEICULOS =
            "CREATE TABLE " + TABLE_VEICULOS + " (" +
                    COLUMN_VEICULO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_VEICULO_PLACA + " TEXT," +
                    COLUMN_VEICULO_FROTA + " TEXT," +
                    COLUMN_VEICULO_MODELO + " TEXT)";

    VeiculoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_VEICULOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso haja uma atualização do banco de dados
    }

    public long addVeiculo(Veiculo veiculo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEICULO_PLACA, veiculo.getPlaca());
        values.put(COLUMN_VEICULO_FROTA, veiculo.getFrota());
        values.put(COLUMN_VEICULO_MODELO, veiculo.getModelo());
        long id = db.insert(TABLE_VEICULOS, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Veiculo> getAllVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VEICULOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Veiculo veiculo = new Veiculo(
                        cursor.getString(cursor.getColumnIndex(COLUMN_VEICULO_PLACA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_VEICULO_FROTA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_VEICULO_MODELO))
                );
                veiculo.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_VEICULO_ID)));
                veiculos.add(veiculo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return veiculos;
    }

    public void deleteVeiculo(long veiculoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEICULOS, COLUMN_VEICULO_ID + " = ?",
                new String[]{String.valueOf(veiculoId)});
        db.close();
    }
}