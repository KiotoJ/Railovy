package com.example.jon.railovy;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listHira;
    ListView listFeony;
    TabHost onglets;
    TextView affichHira;
    Button btnRetour;

    static final int READ_BLOCK_SIZE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listHira = (ListView) findViewById(R.id.list_hira);
        listFeony = (ListView) findViewById(R.id.list_feony);
        affichHira = (TextView) findViewById(R.id.affichHira);
        btnRetour = (Button) findViewById(R.id.btn_retour);

       affichHira.setMovementMethod(new ScrollingMovementMethod());

        btnRetour.setVisibility(View.INVISIBLE);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listHira.setVisibility(View.VISIBLE);
                affichHira.setVisibility(View.INVISIBLE);
                btnRetour.setVisibility(View.INVISIBLE);
            }
        });

        affichHira.setVisibility(View.INVISIBLE);

        listFeony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String test = "selection est ici";
                Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
            }
        });

        listHira.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listHira.setVisibility(View.INVISIBLE);
                affichHira.setVisibility(View.VISIBLE);
                btnRetour.setVisibility(View.VISIBLE);
                affichHira.setText(mamakyTononkira());
            }
        });

        onglets = (TabHost) findViewById(R.id.onglets);

        onglets.setup();
        TabHost.TabSpec tononkir = onglets.newTabSpec("Tononkira");
        tononkir.setContent(R.id.tononkira);
        tononkir.setIndicator("TONONKIRA");
        onglets.addTab(tononkir);



        TabHost.TabSpec feon = onglets.newTabSpec("Feony");
        feon.setContent(R.id.feony);
        feon.setIndicator("FEONY");
        onglets.addTab(feon);

        List<String> lohateny_feony = new ArrayList<>();
        List<String> lohateny_hira = new ArrayList<>();
        lohateny_hira.add("Titre hira 1");
        lohateny_hira.add("Titre hira 2");
        lohateny_hira.add("Titre hira 3");

        lohateny_feony.add("Feony hira 1");
        lohateny_feony.add("Feony hira 2");
        lohateny_feony.add("Feony hira 3");

        ArrayAdapter<String> adapterFeony = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lohateny_feony);
        ArrayAdapter<String> adapterHira = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lohateny_hira);

        listHira.setAdapter(adapterHira);
        listFeony.setAdapter(adapterFeony);
    }

    public String  mamakyTononkira(){

        String s="";
        try {
            //FileInputStream fileIn = openFileInput(getFilesDir().getAbsolutePath()+"/upW.txt");

            AssetManager am =getAssets();
            InputStream is = am.open("upW.txt");

            InputStreamReader InputRead= new InputStreamReader(is);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
           // Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            s=" "+getFilesDir();
        }
        return s;
    }

    public String makaLohateninkira(){
        return  "";
    }

    public String makalohatenyMP3(){
        return "";
    }
}
