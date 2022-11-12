
package jp.co.futureantiques.gorillamemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jp.co.futureantiques.gorillamemo.DataBase.DBManager;

public class AddActivity extends AbstractMemoBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LogMoveOnAddActivity", "追加画面へ遷移しました");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //EditViewとの紐付け
        mHead = findViewById(R.id.para_title);
        mBody = findViewById(R.id.para_body);


        //登録処理
        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LogPushButton", "登録処理ボタンが押されました");
                DBManager dataBaseManager = new DBManager(AddActivity.this);

                //EditTextに入力された値を変数へ格納する
                head = mHead.getText().toString();
                body = mBody.getText().toString();

                //DataBaseManagerクラスのinsertメソッドを使用してDBへ保存
                dataBaseManager.insert(head, body);

                //AddActivityを閉じる
                finish();
            }
        });

        //MainActivityへ遷移
        Button sendButton = findViewById(R.id.menu_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home_icon_button:
                Log.i("home_icon_buttonP", "ホームiconが選択されました。");
                Intent intent0 = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent0);
                return true;
            case R.id.add_icon_button:
                Intent intent = new Intent(AddActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}