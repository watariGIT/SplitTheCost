package com.example.splitthecost;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

public class SettingPrefActivity extends Activity {
    
    static public final String PREF_KEY_FRACTION = "key_fraction";
    static public final String PREF_KEY_ROUNDUP = "key_roundup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // PrefFragmentの呼び出し
        getFragmentManager().beginTransaction().replace(
                android.R.id.content, new PrefFragment()).commit();
    }

    // 設定画面のPrefFragmentクラス    
    public static class PrefFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_pref);
            
            // Summary を設定
            setSummaryFraction();
        }
        
        // 設定値が変更されたときのリスナーを登録
        @Override
        public void onResume() {
            super.onResume();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.registerOnSharedPreferenceChangeListener(listener);
        }
        
        // 設定値が変更されたときのリスナー登録を解除
        @Override
        public void onPause() {
            super.onPause();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.unregisterOnSharedPreferenceChangeListener(listener);
        }
        
        // 設定変更時に、Summaryを更新
        private OnSharedPreferenceChangeListener listener 
                                                = new OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(
                                SharedPreferences sharedPreferences, String key) {
                if (key.equals(PREF_KEY_FRACTION)) {
                    setSummaryFraction();
                }
            }
        };
        
        // Fraction の Summary を設定
        private void setSummaryFraction() {
            ListPreference prefFraction = (ListPreference)findPreference(PREF_KEY_FRACTION);
            prefFraction.setSummary(prefFraction.getEntry());
        }        
    }
}