package br.imd.ufrn.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CheckListDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_SENHA = "senha";
    private static final String COLUMN_EMAIL = "email";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_LOGIN + " TEXT,"
            + COLUMN_SENHA + " TEXT,"
            + COLUMN_EMAIL + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long inserirUsuario(Usuario usuario) {
        long id = -1;

        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_LOGIN, usuario.getLogin());
            values.put(COLUMN_SENHA, usuario.getSenha());
            values.put(COLUMN_EMAIL, usuario.getEmail());

            id = db.insert(TABLE_USERS, null, values);

            Log.d("DatabaseHelper", "Inserindo usuário: " + usuario.getLogin() + ", ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseHelper", "Erro ao inserir usuário: " + e.getMessage());
        }

        return id;
    }

    public String verificarDuplicatas(String usuario, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorLogin = verificarCampoDuplicado(db, COLUMN_LOGIN, usuario);
        Cursor cursorEmail = verificarCampoDuplicado(db, COLUMN_EMAIL, email);

        boolean loginDuplicado = cursorLogin.getCount() > 0;
        boolean emailDuplicado = cursorEmail.getCount() > 0;

        cursorLogin.close();
        cursorEmail.close();
        db.close();

        if (loginDuplicado && emailDuplicado) {
            return "Usuário e Email";
        } else if (loginDuplicado) {
            return "Usuário";
        } else if (emailDuplicado) {
            return "Email";
        }

        return "";
    }

    private Cursor verificarCampoDuplicado(SQLiteDatabase db, String coluna, String valor) {
        return db.query(
                TABLE_USERS,
                new String[]{COLUMN_ID},
                coluna + "=?",
                new String[]{valor},
                null,
                null,
                null
        );
    }

    public boolean verificarCredenciais(String login, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_LOGIN + "=? AND " + COLUMN_SENHA + "=?",
                new String[]{login, senha},
                null,
                null,
                null
        );

        boolean credenciaisCorretas = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return credenciaisCorretas;
    }
}
