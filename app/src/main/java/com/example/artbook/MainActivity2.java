package com.example.artbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    Bitmap selectedImage;       //Açıklama "GörselSeçmekNot.java - Açıklama 3Y Code'de."
    ImageView imageView;      //Açıklama "GörselSeçmekNot.java -Tanımlama 1'de"
    EditText artNameText, painterNameText, yearText;    //Açıklama "GörselSeçmekNot.java Tanımlama 2'de"
    Button button;      //Açıklama "GörselNotSeçmek.java Tanımlama 3'de"
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);
        artNameText = findViewById(R.id.artNameText);
        painterNameText = findViewById(R.id.painterNameText);
        yearText = findViewById(R.id.yearText);
        button = findViewById(R.id.button);

        database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);


        Intent intent = getIntent();        //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 7'de"
        String info = intent.getStringExtra("info");        //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 8'de"

        if (info.matches("new")) {
            artNameText.setText("");
            painterNameText.setText("");
            yearText.setText("");
            button.setVisibility(View.VISIBLE);     //Buttonu Görsün.

            Bitmap selectImage = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.selectimage);   //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 16'da"
            imageView.setImageBitmap(selectImage);

        }   //İf info matches süslüsü bitiş.
        else {
            int artId = intent.getIntExtra("artId",1);  //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 11'de"
            button.setVisibility(View.INVISIBLE);       //Butonu göremesin

            try {
                Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?",new String[] {String.valueOf(artId)});  //Açıklaması "SeçimArgümanlarıNot.java - Kod Zamanı 2 - 3'de"
                //Column
                int artNameIx = cursor.getColumnIndex("artname");
                int painterNameIx = cursor.getColumnIndex("paintername");
                int yearIx = cursor.getColumnIndex("year");
                int imageIx = cursor.getColumnIndex("image");

                while (cursor.moveToNext()) {
                    artNameText.setText(cursor.getString(artNameIx));
                    painterNameText.setText(cursor.getString(painterNameIx));
                    yearText.setText(cursor.getString(yearIx));
                    byte [] bytes = cursor.getBlob(imageIx);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);    //Açıklaması "SeçimArgümanlarıNot.java - Kod Zamanı 2 -14'de"
                    imageView.setImageBitmap(bitmap);


                }
                cursor.close();

            } catch (Exception e) {

            }


        }   //Else süslüsü bitiş.





    } //Oncreate kapanış süslüsü

    public void selectImage (View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){       //Açıklaması Kullanıcı İzinleri Not  : Kod Zamanı 1/1'de
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1); // Açıklaması Kullanıcı izinleri Not : Kod Zamanı 2/2'de.
        }   //İzin verilmediyse süslüsü bitişi.
        else {      //
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);      //Açıklaması İzinVerilirseNot : "Kullanıcı izin Veririse Kod : 2 Kod'da"
            startActivityForResult(intentToGallery,2);                                                  //AÇıklaması İzinVerilirseNot : "Kullanıcı izin Veririse Kod : 3 Kod'da."
        }       //İzin Verildiyse süslüsü bitişi.

    } //SelectImage kapanış süslüsü

    @Override   // Method açıklaması : İzinVerilirseNot Kod "Kullanıcı İzin Verirse Kod 2 : 1 'de"
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1) {      //Açıklaması : "İzinverilirseNot" Kullanıcı İzin Verirse Kod 2 : 2'de

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  //Açıklaması  :"İzinVerilirseNot" "Kullanıcı İzin Verirse Kod 2 : 3 'de."
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);

            }   //ifgrantresult.length süslüsübitiş.
        }   //ifrequestcodeeşitise 1 süslüsübitiş.


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }   //onRequestPermissionResult Süslüsü bitiş.

    //onActivitiyResult Methodunun açıklaması : GörselSeçmekNot.java Methodun Açıklaması sı 1/1'de.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {      // "Kodun açıklaması GörselSeçmekNot.java - Kullanım Kod Zamanı 1. - Kodun açıklaması 1 :'de"
            Uri imageData = data.getData();     //Açıklaması "GörselSeçmekNot.java - Kullanım Kod Zamanı 2 - Kodun açıklaması 2'de."

            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData); //Açıklaması "GörselNotSeçmek.java - Version İçin Kod 1 - 3.Kodun Açıklaması'nda."
                    selectedImage = ImageDecoder.decodeBitmap(source);      //Açıklaması "GörselNotSeçmek.java - Version İçin Kod 1 - 4.Kodun Açıklamasın'da."
                    imageView.setImageBitmap(selectedImage);        //Açıklaması "GörselNotSeçmek.java - Version için kod 1 -5 Kodun açıklamasında."

                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        }   //ifrequestCode - result code süslüsü bitiş.

        super.onActivityResult(requestCode, resultCode, data);
    }       //OnActivityResult Süslüsü Bitiş.

    public void save (View view) {

        String artName = artNameText.getText().toString();
        String painterName = painterNameText.getText().toString();
        String year = yearText.getText().toString();
        Bitmap smallImage = makeSmallerImage(selectedImage,300);    //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 11'de"

        //
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();           //Açıklaması "GörselKüçültmekNot.java - Kod Zamanı 2  2Kodun açıklaması."
        //selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);  //Açıklaması "GörselKüçültmekNot.java - Kod Zamanı 2 - 1'de."
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);  //Açıklaması "GörselKüçültmekNot.java - Method içerik doldurma zamanı - 12'de"
        byte[] byteArray = outputStream.toByteArray();                                  //Açıklaması "GörselKüçültmekNot.java - Kod Zamanı 2  3'de."

        try {   //Açıklaması "VerileriKaydetmekNot.java - Veri Kaydetmek 1 - 1'de"
            SQLiteDatabase database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null); // Açıklaması " VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 3'de"
            database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY , artname VARCHAR , paintername VARCHAR , year VARCHAR , image BLOB)");   //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 4'de"

            String sqlString = "INSERT INTO arts (artname,paintername,year,image) VALUES (?,?,?,?)";    //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 6'da"
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);                 //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 7'de"
            sqLiteStatement.bindString(1,artName);                                                  //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 8'de"
            sqLiteStatement.bindString(2,painterName);                                  //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 -    9'da"
            sqLiteStatement.bindString(3,year);                                         // Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 10'da"
            sqLiteStatement.bindBlob(4,byteArray);                                      // Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 11'de"
            sqLiteStatement.execute();                                                  //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 -  12'de"

            //database.execSQL("INSERT INTO arts (artname, paintername, year, image)VALUES (?,?,?,?)");   //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 5'de"

        }   //Try süslüsü kapanış.
        catch (Exception e) {   //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 2'de"

        }   //Catch süslüsü bitiş.

        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //Açıklaması "AktiviteleriKapatmakNot.java - Kod Zamanı 1 - 3'de"
        startActivity(intent);

        //finish();       //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 13'de."

    }   //Save kapanış süslüsü

    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) { //"Açıklaması - GörselKüçültmekNot.java - Method Method oluşturma Zamanı - 1 'de"
        int width = image.getWidth();       //"Açıklaması - GörselKüçültmekNot.java - Method Method içerik doldurma Zamanı - 1'de."
        int height = image.getHeight();     // Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 2'de".

        float bitmapRatio = (float) width / (float) height;     //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 3'de"

        if (bitmapRatio > 1) {                                  //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 4'de"
            width = maximumSize;                                // Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 6'da"
            height = (int) (width / bitmapRatio);                        //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 8'de"
        }   //İf maximum size kapanış süslüsü .
        else {                                                  //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı  5'de"-
            height = maximumSize;                               //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı -    7'de"
            width = (int) (height * bitmapRatio);                   //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 9'da"
        }   //Else maximum size kapanış süslüsü.

        return Bitmap.createScaledBitmap(image,width,height,true);      //Açıklaması - GörselKüçültmekNot.java - Method içerik doldurma Zamanı - 10'da"

    }   //Public bitmap kapanış süslüsü.
}