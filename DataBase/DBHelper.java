package jp.co.futureantiques.gorillamemo.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //データベースの名前
    private static final String DBNAME = "GORILLA_MEMO";
    //データベースのバージョン
    private static final int VERSION = 1;

    //コンストラクタ
    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブルを作成する
        db.execSQL("CREATE TABLE MASTER_TABLE("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "head TEXT,"
                + "body TEXT,"
                + "del_flag INTEGER,"
                + "add_time TIMESTAMP DEFAULT (DATETIME(CURRENT_TIMESTAMP,'LOCALTIME')))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            try {
                db.execSQL("create table my_table (" +
                        "id INTEGER primary key autoincrement not null, "
                        + "head TEXT,"
                        + "body TEXT,"
                        + "del_flag INTEGER,"
                        + "add_time TIMESTAMP DEFAULT (DATETIME(CURRENT_TIMESTAMP,'LOCALTIME')))");
            } catch (SQLiteException err) {
                err.printStackTrace();
            }
        }
    }

    //データベースが開かれた際に実行
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
