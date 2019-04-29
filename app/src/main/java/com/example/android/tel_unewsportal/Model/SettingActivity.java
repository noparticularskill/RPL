package com.example.android.tel_unewsportal.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.android.tel_unewsportal.MainActivity;
import com.example.android.tel_unewsportal.R;

public class SettingActivity extends AppCompatActivity {

    Button btnSave;
    Switch swNight, swFontSize;
    SharedPreferences spFont, spNight;

    final String PREF_NIGHT_MODE = "NightMode";
    final String PREF_FONT_SIZE = "BigSize";

    SharedPreferences.Editor editNight, editFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        spNight = getSharedPreferences(PREF_NIGHT_MODE , Context.MODE_PRIVATE);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);

            if(spNight.getBoolean(PREF_NIGHT_MODE,false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        btnSave = findViewById(R.id.button);
        swNight = findViewById(R.id.switch1);

        spFont = getSharedPreferences(PREF_FONT_SIZE, Context.MODE_PRIVATE);

        if (spFont.getBoolean(PREF_FONT_SIZE, false)){
            swFontSize.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfig();
            }
        });

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            swNight.setChecked(true);
        }
    }

    private void saveConfig() {
        if (swNight.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editNight = spNight.edit();
            editNight.putBoolean(PREF_NIGHT_MODE, true);
            editNight.apply();
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editNight = spNight.edit();
            editNight.putBoolean(PREF_NIGHT_MODE, false);
            editNight.apply();
        }

        if (swFontSize.isChecked()){
            editFont = spFont.edit();
            editFont.putBoolean(PREF_FONT_SIZE, true);
            editFont.apply();
        }else{
            editFont = spFont.edit();
            editFont.putBoolean(PREF_FONT_SIZE, false);
            editFont.apply();
        }

        Intent i = new Intent(SettingActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
