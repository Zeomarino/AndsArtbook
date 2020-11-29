package com.example.artbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> nameArray;        //Açıklaması "VerileriÇekmekNot.java -  Dizileri Oluşturma Zamanı 1 : 1'de"
    ArrayList<Integer> idArray;             //Açıklaması " VerileriÇekmekNot.java - Dizileri Oluşturma Zamanı 1 : 2'de"
    ArrayAdapter arrayAdapter;      //Açıklaması "VerileriÇekmekNot.java - While Döngüsü İçerik Doldurma Zamanı 1 - 6'da"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        nameArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();     //Açıklaması "VerileriÇekmekNot.java - Dizileri Oluşturma Zamanı 1 : 4'de"

        arrayAdapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1,nameArray);     //Açıklaması "VerileriçekmekNot.java - While Döngüsü İçerik Doldurma Zamanı 1 - 3'de"
        listView.setAdapter(arrayAdapter);
        //Aşağıdaki setOnItemClickListenerin "Açıklaması  - VeriAktarımıNot.java - Kod Zamanı 1 - 1'de"
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (MainActivity.this,MainActivity2.class);     //Açıklaması "VeriAktarımıNot.java - Kod Zamani 1 - 2'de"
                intent.putExtra("artId",idArray.get(position));     //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 4'de"
                intent.putExtra("info","old");  //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 5'de"

                startActivity(intent);

            }       //OnItemClick Süslüsü bitiş.
        });                 //OnItemClickListener Süslüsü parantezi ve noktalı virgülü bitiş.

        getData();
    }
    public void getData() {     //getData  methodu süslüsü başlangıç.
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);  //Açıklaması " VerileriÇekmekNot.java - Method içerik doldurma zamanı 1 - 1'de"
            Cursor cursor = database.rawQuery("SELECT * FROM arts", null); // Açıklaması "VerileriÇekmekNot.java - Method içerik doldurma zamanı 1 - 2'de"
            int nameIx = cursor.getColumnIndex("artname");          //Açıklaması - "VerileriçekmekNot.java - Method içerik doldurma zamanı 1 - 3'de"
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {       //Açıklaması "VerileriKaydetmekNot.java - Method içerik doldurma zamanı 1 - 5'de"
                nameArray.add(cursor.getString(nameIx));        //Açıklaması "VerileriKaydetmekNot.java - While Döngüsü İçerik Doldurma Zamanı 1-1'de"
                idArray.add(cursor.getInt(idIx));


            }           //While süslüsü bitiş.
            arrayAdapter.notifyDataSetChanged();    //Açıklaması "VerileriKaydetmekNot.java - While Döngüsü İçerik Doldurma Zamanı 1 - 7'de"
            cursor.close();;    //Açıklaması "VerileriKaydetmekNot.java - Method içerik doldurma zamanı 1 - 6'da"


        }   catch (Exception e) {
            e.printStackTrace(); //Bir sıkıntı varsa bunu görelim
        }   //Catch süslüsü bitiş.


    }       //Getdaha methodu süslüsü bitiş.

    //Açıklaması MenuEklemekNot Kod Zamanı 1'den.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alttakini açıklaması MenuEklemekNot Kod Zamanı 2 / 1'de.
        MenuInflater menuInflater = getMenuInflater(); //Inflater
        menuInflater.inflate(R.menu.add_art,menu);  //MenuEklemekNot Kod Zamanı 2 / 2'de açıklaması.
        //

        return super .onCreateOptionsMenu(menu);
    }   //MenuEklemekNotKod zamanı 1 Süslüsü bitiş.

    //Açıklaması MenuEklemekNot Kod Zamanı 2'den.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_art_item) { //Açıklaması : MenuEklemekNot Kod Zamanı 2 / 3'de.
            Intent intent = new Intent (MainActivity.this,MainActivity2.class); //Açıklaması : MenuEklemekNot Kod Zamanı 2 / 4'de.
            intent.putExtra("info","new");
            startActivity(intent);  //Açıklaması : MenuEklemekNot Kod Zamanı 2 /5'de.
        }   // İf süslüsü bitiş.

        return super.onOptionsItemSelected(item);

    }   //MenuEklemekNot Kod zamanı 2 Süslüsü bitiş.


}