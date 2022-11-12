package jp.co.futureantiques.gorillamemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jp.co.futureantiques.gorillamemo.DataBase.DBManager;
import jp.co.futureantiques.gorillamemo.DataBase.MemoData;

public class EditActivity extends AbstractMemoBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Log.i("LogMoveOnEditActivity", "更新画面へ遷移しました");

        //MemoData memoData = new MemoData();
        Intent intent = this.getIntent();

        //EditActivity内のEditViewとの紐付け
        mHead = findViewById(R.id.para_title);
        mBody = findViewById(R.id.para_body);

        //intentを取得し、更新データのkeyに設定する
        mId = intent.getStringExtra("key");

        //DataBaseManagerのselectPartメソッドを使用する(引数＝mId)
        DBManager dbManager = new DBManager(EditActivity.this);
        MemoData memoData = dbManager.select(mId);

        //MemoDataからデータを取り出す
        head = memoData.getHead();
        body = memoData.getBody();

        //更新元データ表示
        mHead.setText(head, EditText.BufferType.NORMAL);
        mBody.setText(body, EditText.BufferType.NORMAL);
        Log.i("LogSelectPartData", "選択したデータを表示しています");


        //更新処理
        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DBManager dataBaseManager = new DBManager(EditActivity.this);
                String mHeadStr = mHead.getText().toString();
                String mBodyStr = mBody.getText().toString();
                dataBaseManager.update(mId, mHeadStr, mBodyStr);

                //登録完了後MainActivityへ遷移する
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
                Intent intent0 = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent0);
                return true;
            case R.id.add_icon_button:
                Intent intent = new Intent(EditActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}