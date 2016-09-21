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

        // PrefFragment�̌Ăяo��
        getFragmentManager().beginTransaction().replace(
                android.R.id.content, new PrefFragment()).commit();
    }

    // �ݒ��ʂ�PrefFragment�N���X    
    public static class PrefFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_pref);
            
            // Summary ��ݒ�
            setSummaryFraction();
        }
        
        // �ݒ�l���ύX���ꂽ�Ƃ��̃��X�i�[��o�^
        @Override
        public void onResume() {
            super.onResume();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.registerOnSharedPreferenceChangeListener(listener);
        }
        
        // �ݒ�l���ύX���ꂽ�Ƃ��̃��X�i�[�o�^������
        @Override
        public void onPause() {
            super.onPause();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.unregisterOnSharedPreferenceChangeListener(listener);
        }
        
        // �ݒ�ύX���ɁASummary���X�V
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
        
        // Fraction �� Summary ��ݒ�
        private void setSummaryFraction() {
            ListPreference prefFraction = (ListPreference)findPreference(PREF_KEY_FRACTION);
            prefFraction.setSummary(prefFraction.getEntry());
        }        
    }
}