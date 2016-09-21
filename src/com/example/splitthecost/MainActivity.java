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
				 // �ݒ���擾
				SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
				String frac = pref.getString(SettingPrefActivity.PREF_KEY_FRACTION, "500");
				Boolean roundup = pref.getBoolean(SettingPrefActivity.PREF_KEY_ROUNDUP, false);
				int fracVal = Integer.parseInt(frac);
			          
				// �I�u�W�F�N�g���擾
				EditText etxtNum = (EditText) findViewById(R.id.eTxtNum);
				EditText etxtMoney = (EditText) findViewById(R.id.eTxtMoney);
				TextView txtResult = (TextView) findViewById(R.id.txtResult);

				// ���͓��e���擾
				String strNum = etxtNum.getText().toString();
				String strMoney = etxtMoney.getText().toString();

				// ���l�ɕϊ�
				int num = Integer.parseInt(strNum);
				int money = Integer.parseInt(strMoney);

				// ���芨�v�Z
				int result = money / num;

				// �؂�グ
				if (roundup && result % fracVal != 0) {
					result += fracVal;
				}

				// �[������
				result = result / fracVal * fracVal;
				
				// ���ʕ\���p�e�L�X�g�ɐݒ�
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
			// �ݒ�{�^����������
			Intent intent = new Intent(MainActivity.this, SettingPrefActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
