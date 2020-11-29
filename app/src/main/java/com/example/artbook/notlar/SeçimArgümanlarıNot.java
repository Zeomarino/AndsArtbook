/*
Peki şimdi ne yapıcağız.
Art İdmiz hazır tek yapmamız gereken aslında biraz önce yaptığımız gibi gelip burdaki sqlite veritabanından verileri çekmek ama bu sefer sadece ve sadece tek bir id için çekiceğiz geri kalan bütün girdilerin yok görselini çekiyim falan felan bunlarla uğraşmıyacağız peki nasıl yapıcağız.

main2Activity2.java - İçerisindeki else methodunun içerisine yapıcağım. Ama şunu şöyliyim.
MainActivity.javadaki işlemin aynısını yapıcağız aslında getDatadakinin "public void getDatada"
Databasemizi oluşturucağız cursorumuzu oluşturucağız o yüzden direk bunu kopyalabilirsiniz ama yine baştan yazmak daha olağan olabilir .
O yüzden  "main2Activity2.java - İçerisindeki else methodunun içerisine yapıcağım" button'un altında Try & catch'imi açıyorum ben
    Kod Zamanı 1 -
    1) try { }
    2 catch (Exception e) {}
Ve try catch içerisinde hemen sqlite databesimizi oluşturalım.
    3) try içerisinde "SQLiteDatabase database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
Diyelim ama şöyle birşey olsa daha iyi olabilirdi diye karar alıyorum onCreate içerisinde yapsaydık  neden : çünkü burda aynı databaseyi birdaha açıyoruz
save methodunda gözükülceği üzere aynı db yi bir daha tekrar açmanın manası yok
O yüzden Gelip app compat altına
    Kod Zamanı 2 -
    1) SQLiteDatabase database; -database dedim adına.
Ve Kod zamanı 1 deki 3 kodumuzu tutup onCreate altında yapıcağım button'un altında yapıcağım herşey tanımlandıktan sonrası için ama başından SqliteDatabaseyi silmeyi unutmayalım.
Ve save methodumuzun içindekileride aynı şekilde başındakini siliceğim böylece 2 side aynı şeyleri konuşucak.
Böylece bilmem kaç kere aynı değişkeni farklı farklı yerlerde oluşturup boşu boşuna hafızada yer kaplamaya gerek yok Peki dbyi oluşturdum
Yine "Try içerisine geri dönüceğim."
    2) Cursor cursor =
2.Kodun açıklaması - Database oluşturtukdan sonra haliyle bir cursor yapıcağım ve bu cursorumda "database.rawQuery("")" diyorum  () içerisine ("SELECT * FROM arts")
Dedim . Ama bir filtreleme yapmam lazım ne filtrelemesi " WHERE id = " şimdi idyi biliyormuyuz evet biliyoruz "artId" ama idyi buraya yazamam
Peki bu apk'de daha önce yaptığımız gibi sqliteStatement yapabilirmiyiz hayır.Çünkü şuanda bir rawQuery yazıyorum bir sorgu atıyorum execSQL komutunu kullanmıyorum ozaman nasıl yapıcağım.
İşte ozamaan "WHERE id = ? " yine soru işareti koyucağım ama "?"," virgülden sonra bir selectionArg selection argümanı yazıcağım selection argümanı bizden bir string dizisi içerisinde isteniyor yani bir "new string [] "  new string dizisi yapıp
direk buraya " {""}" istediğim argüman neyse onu koyabilirim bizim için istediğimiz argüman artId "{artId} ama art id bir integer o yüzden uyuşmuyor bunu stringe çevirmem lazım ve bunu şu şekilde
{String.valueOf(artId)} diyip yapabiliriz mesela böylece art idyi stringe çeviricek onu bir filreleme mekanizması olarak ? a vericek selection argümanları sonra görüceğiz demiştik şimdi işte onu görmüş olduk
    3) Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?", new String [] {String.valueOf(artId)}); şeklinde oluyor sonuç olarak.
Şimdi bundan sonra ne alıcağız tabiki bütün collumn'ları alıcağız artık tek tek
    4) int artNameIx = cursor.getColumnIndex("artname"); sonra
    5) int painterNameIx = cursor.getColumnIndex("paintername");
    6) int yearIx = cursor.getColumnIndex("year");
    7) int imageIx  = cursor.getColumnIndex("image");
Şimdi bunları yazdık peki bundan sonra ne yapıcağız.
yine while cursor.movemizi çalıştırıcağız aslında
    8) while(cursor.moveToNext()) { } diyorum ve işlem bittikten sonra.
    9) cursor.close();
Peki whilede ne yapıcağım aslında indexleri yani nameleri vs almak çok kolay tek istediğimiz artnameText'i mesela
    10) artNameText.setText(cursor.getString(artNameIx));
10.Kodun açıklaması yapıp () içerisine artName texti vermem gerek nasıl vericeğim "cursor.getString(artnameIx) şeklinde şimdi bunu diğerleri içinde yapalım.
    11) painterNameText.setText(cursor.getString(painterNameIx));
    12) yearText.setText(cursor.getString(yearText));
Şimdi gelelim görsele görseli böyle alamıyacağız niye çünkü biz görseli byte dizisi olarak kaydetmiştik yine byte dizisi olarak vericek bana o yüzden byte dizisi olarak tanımlıyorum
    13) byte[] bytes = cursor.getBlob(imageIx);
Sonra aslında bunu bitmap'e çeviriceğiz bitmap'e çevirmek daha kolay
    14) Bitmap bitmap = BitmapFactory.decodeByteArray()
14.Kodun açıklaması - Daha önce bitmapFactory.decode farklı şekillerde yapmıştık ama bu sefer bunu yapıcağım array'i decode etmek istediğimden dolayı () içerisinde bize bazı şeyler sorucak mesela datanın kendisini.
Datamız bytes, bir offeset sorucak bir datanın uzunlugunu sorucak offset'e 0 datanın uzunlugunada "bytes." dizisinin "bytres.length"  diyerek verebiliriz.
    14) Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
Bu aslında direk bir byte dizisini görsel haline getiren kod ve bundan sonra
    15) imageView.setImageBitmap(bitmap); diyebilirim
Bunu yaptıktan sonra işlemimiz tamam olucak bir çalıştıralım apk'imizi
Resme tıkladığımızda mona lisaya bakalım id vs doğru geliyormu tabiki doğru geliyor bu aşamada.ismi artisti painteri vs herşeyi düzgün bir şekilde geldi
Şimdi diğer zamanda karşılaştığımız ... tıklayınca eklenmeme sorununa bir çare bulmamız gerekiyor
Onuda bir sonraki aşamada yapıp uygulamamızı bitirelim.
Sıradaki aşama "AktiviteleriKapatmakNot.java"

 */