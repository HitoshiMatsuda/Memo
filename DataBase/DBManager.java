package jp.co.futureantiques.gorillamemo.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper mHelper;
    private SQLiteDatabase db;


    public DBManager(Context context) {
        mHelper = new DBHelper(context);
    }

    //SELECT文全選択（メモ一覧用）
    public Cursor selectAll() {
        db = mHelper.getWritableDatabase();
        return db.rawQuery("SELECT id as _id, head, body, del_flag, add_time FROM MASTER_TABLE", null);
    }

    //SELECT文一覧より選択（編集画面用）
    public MemoData select(String key) {
        db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id, head, body, del_flag, add_time " +
                        "FROM MASTER_TABLE " +
                        "WHERE id = ?",
                new String[]{key});

        //MemoDataへ格納
        cursor.moveToFirst();
        MemoData memo = new MemoData();
        String head = cursor.getString(cursor.getColumnIndex("head"));
        String body = cursor.getString(cursor.getColumnIndex("body"));

        memo.setHead(head);
        memo.setBody(body);

        return memo;
    }

    //更新処理
    //EditActivity
    public void update(String key, String head, String body) {
        db = mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("head", head);
        contentValues.put("body", body);
        db.update("MASTER_TABLE"
                , contentValues
                , "id = ?"
                , new String[]{key}
        );
        db.close();
    }

    //追加処理
    //AddActivity
    public void insert(String head, String body) {
        db = mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("head", head);
        contentValues.put("body", body);
        db.insert("MASTER_TABLE", null, contentValues);
        db.close();
    }
}
