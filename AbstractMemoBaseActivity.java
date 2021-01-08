package jp.co.futureantiques.gorillamemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import jp.co.futureantiques.gorillamemo.DataBase.DBManager;
import jp.co.futureantiques.gorillamemo.R;

public class AbstractMemoBaseActivity extends AppCompatActivity {
    protected String mId;
    protected DBManager mDBManager;

    protected EditText mHead;
    protected EditText mBody;
    protected String head;
    protected String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_memo_base);

        mHead = findViewById(R.id.head_text);
        mBody = findViewById(R.id.body_text);
        mDBManager = new DBManager(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        //タイトルの文字色指定
        getSupportActionBar().setTitle(Html.fromHtml("<font color = black>" + getString(R.string.app_name) + "</font>"));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home_icon_button:
                Log.i("home_icon_buttonP", "ホームiconが選択されました。");
                Intent intent0 = new Intent(AbstractMemoBaseActivity.this, MainActivity.class);
                startActivity(intent0);
                return true;
            case R.id.add_icon_button:
                Intent intent = new Intent(AbstractMemoBaseActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}