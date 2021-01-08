package jp.co.futureantiques.gorillamemo;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import jp.co.futureantiques.gorillamemo.DataBase.DBManager;

public class MainActivity extends AbstractMemoBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);

        mDBManager = new DBManager(MainActivity.this);
        Cursor cursor = mDBManager.selectAll();
        cursor.moveToFirst();

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.main_list_layout,
                cursor,
                new String[]{"head", "body", "add_time"},
                new int[]{R.id.head_text, R.id.body_text, R.id.add_time},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        ListView listView = findViewById(R.id.memoList);
        listView.setAdapter(simpleCursorAdapter);

        //リスト項目選択処理(更新処理)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //インテントの作成
                Intent intent = new Intent(MainActivity.this, EditActivity.class);

                //値を引き渡す
                intent.putExtra("key", String.valueOf(id));
                //Activityの起動
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home_icon_button:
                Log.i("home_icon_buttonP", "ホームiconが選択されました。");
                Intent intent0 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent0);
                return true;
            case R.id.add_icon_button:
                Log.i("add_icon_buttonP", "新規追加iconが選択されました。");
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}