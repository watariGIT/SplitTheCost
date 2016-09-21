package com.example.splitthecost;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn = (Button) findViewById(R.id.btnCalc);

		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 // 設定を取得
				SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
				String frac = pref.getString(SettingPrefActivity.PREF_KEY_FRACTION, "500");
				Boolean roundup = pref.getBoolean(SettingPrefActivity.PREF_KEY_ROUNDUP, false);
				int fracVal = Integer.parseInt(frac);
			          
				// オブジェクトを取得
				EditText etxtNum = (EditText) findViewById(R.id.eTxtNum);
				EditText etxtMoney = (EditText) findViewById(R.id.eTxtMoney);
				TextView txtResult = (TextView) findViewById(R.id.txtResult);

				// 入力内容を取得
				String strNum = etxtNum.getText().toString();
				String strMoney = etxtMoney.getText().toString();

				// 数値に変換
				int num = Integer.parseInt(strNum);
				int money = Integer.parseInt(strMoney);

				// 割り勘計算
				int result = money / num;

				// 切り上げ
				if (roundup && result % fracVal != 0) {
					result += fracVal;
				}

				// 端数処理
				result = result / fracVal * fracVal;
				
				// 結果表示用テキストに設定
				txtResult.setText(Integer.toString(result));

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			// 設定ボタン押下処理
			Intent intent = new Intent(MainActivity.this, SettingPrefActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
