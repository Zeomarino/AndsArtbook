/*
Şimdi bu aşamaya kadar verileri kaydettiğimize göre sqliteye mainActivity içerisinde verileri alıp göstermekten başka birşey kalmadı yapmamız gereken
O yüzden MainActivity.java'ma geri dönüyorum hatta activity_main.xml'de bunu göstermek için birşeyim bile yok bir listView vb herhangi bir listemiz yok
Öncelikle activity_main.xml'imize birtane listView getirelim listView içerisinde bu artların isimlerini getirebiliriz oto constrainlerini veriyorum zaten bütün aktiviteye yayılmış şekilde
id'sinede listView diyorum ve hemen tasarımımız hazır zaten bukadardı o yüzden
MainActivity.java'ma dönüp işlemlerimi yapabilirim unutmadan zaten birtane görünümüz var appcompat altına hemen tanımlıyalım
    Tanımlama zamanı 1 :
    1)ListView listView;
    2 onCreate altına "listView = findViewById(R.id.listView);
 Farklı yerlerden ulaşabilmek için yukarda tanımlıyorum peki burda ne yapıcağız listView'imizde aslında bir sqliteden verileri çekmemiz lazım sqliteden çektikten sonra listView içerisindede göstermemiz lazım peki gelelim hemen
 Verileri çekmek için birtane method yazalım herşeyi onCreate altına koymamak için
    Method Zamanı 1 :
    1 : public void getData() {} yazalım ve bunu gelip onCreate altında çağırabiliriz.
    2 : getData();
 Sadece daha derli toplu olsun diye bunu yapıyorum aslında çokta bir nedeni yok açıkcası.Peki şimdi ne yapıcağım

     : //Method içerik doldurma zamanı :
    1) getdaha methodoumuzun içerisine 1 : SQLiteDatabase database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
 1.Kodun açıklaması : Çok hızlı sqlite databasemizi yine oluşturuyorum çünkü burda openOrCreateDatabase diyiceğim  birebir aynısını yazıcağım daha öncekilerin diğer yerden kopyalabiliriz bile.
 Peki bu neyapıyordu : Bu database yoksa oluşturuyordu ama varsa birşey yapmıyordu açıyordu daha doğrusu  biraz önce oluşturduysak burda açılıyordu peki sonra ne yapıyorduk : Cursor oluşturuyorduk
    2) Cursor cursor = database.rawQuery("SELECT * FROM arts", null);
2.Kodun Açıklaması : Cursor bizim imlecimizdi bu imleci kullanarak  databasemizde bir Query yapabiliyrorduk sorgu yapabiliyorduk ve SELECT * FROM arts diyip selectionArgs'a şimdilik null diyebiliriz. Peki bunu yaptık bundan sonra ?

Bütün verileri almamıza gerek varmı yani ilk aktivitede sadece isimleri göstermek istiyorum bütün verileri almamıza gerek yok ben şunu düşündüm ilk aktivitede isimleri alsak birde idleri alsak yeter neden idyi seçilen adyi bir kişi art'ın üstüne tıkladığında seçtiği idyi diğer tarafa yollarım mainActivity2'ye ve mainActivity2 içerisinde sadece ve sadece o id'ye ait olan bütün isim görsel yıl painter name gibi şeyleri çekebilirim bütün görselleri mainActivity'de çekmeme bütün yılları bilmem neleri çekmeme gerek yok.
Çok daha verimli olur bu şekilde direk idyi alırım diğer tarafa main2Activity.javaya atarım id'den bütün şeyleri çekerim tekrar Ve öyle deniyelim bakalım ne yapıcağım bunun için sadece nameIx bulucağım peki ne yapıyorduk ?
    3. int nameIx = cursor.getColumnIndex("artname");
3.Kodun açıklaması :
Diyip () içerisine "artname" diyip columnNamesi bu arada bu bunun Yanlız dikkat edilmesi gereken husus diğer main2Activity'demki ile birebir aynı olması gererekiyor.
"database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY , artname VARCHAR , paintername VARCHAR , year VARCHAR , image BLOB)");   //Açıklaması "VerileriKaydetmekNot.java - Verileri Kaydetmek 1 - 4'de"" bunla.
Birde idyi alıcağız zaten onun içinde .
    4) int idIx = cursor.getColumnIndex("id");     //Bunada id diyiceğim ve sonra. while loop oluşturucağım ve içerisinde
    5)while (cursor.moveToNext()) {}
 5.kodun açıklaması : cursor.moveToNext yapariken } cursorun süslüsünden sonra.
    6)cursor.close();
Yapıyorduk while döngüsünde ise : Normalde yazdırmıştık şimdi yazdırmıcağım şimdi ne yapıcağım bunları tabiki birer diziye ekliyeceğim şimdi dizilerimizi oluşturalım evvela çünkü bu dizileri listView'de vs'de kullanıcağız
AppCompatActivity altına geliom.

    //Dizileri Oluşturma Zamanı 1 :
    1: ArrayList<String> nameArray;
1.Kodun açıklaması : Bir arraylist oluşturucağım diyorum bu array listimin içerisinde <Stringler olucak> adı "nameArray" olucak mesela ve şimdilik bukadar
    2:ArrayList<Integer> idArray;
2.Kodun açıklaması : Aynısını birde integer ile yapıcağım çünkü idArray olucak oda onun içindede id'leri tutucağım bu ikisi şuan için bana yeterli

OnCreate altında hemen bunları bir eşitliyelim yani
    3: nameArray = new ArrayList<String>();
    4: idArray = new ArrayList<Integer>();

 Bunları yazdım sonra :onCreate altında boş olarak tanımladım  while loop'umun içerisinde gelip direk

    //While Döngüsü İçerik Doldurma Zamanı 1 :
    1) nameArray.add(cursor.getString(nameIx));
1.Kodun açıklaması ne ekliyeceğim : Burda tabiki () ismi ekliyeceğim cursordan alıcağım ismi "cursor.getString" diyorum ve () içerisine "nameIx" diyorum aynı şekilde id içinde yapıcağım.
    2) idArray.add(cursor.getInt(idIx));
 Diyiceğim. Ve tabiki bunlardan sonra birde listeye bağlayıp listede ArrayAdapter'e bak yeni veri geldi demem gerekiyor o yüzden bir ArrayAdepteriminde olması lazım daha onuda yapmadık hemen onu yapalım
 "getData();" öncesinde yapalım onCreate altında öncesinde yapmamın amacı : Tanımlamalar çünkü ozaman olmaz
    3) ArrayAdepter arrayAdapter = new ArrayAdapter()
 3.Kodun açıklaması () içerisinde bize bir aktivite soruyordu aktivitem this, burda sonra hangi layout içerisinde göstericeğim diyordu sadece string göstereceğiz kendi layoutmuzu oluşturmayıda görmüştük ama gerek yok şuanda
 o yüzden "android.R.layout.simple_list_item_1, " yazıcağım hatırlarsanız sadece bir string göstereceğimiz zaman kullanabilceğimiz bir layouttu bu simple list item 1.sonrasındada "nameArray'i" buraya bağlıyacağız  ve listemizde artık nameArray'i göstereceğiz.
 Ve altına gelip.
    4) listView.setAdapter(arrayAdapter);    Diyebilirim
 Ama bir eksik var.
    6) AppCompatActivity altına "ArrayAdapter arrayAdapter;"
 Yazıyorumki diğer yerlerden erişebilelim.
 Ve 3Kodumuzu şu şekilde güncelliyorum
    3) arrayAdapter = new ArrayAdapter (this,android.R.layout.simple_list_item_1,nameArray);
Yukarda tanımladığım için sadece başındaki ArrayAdapter'i siliyorum dikkat ederseniz.
Ve while döngüsü dışına gelip cursor.close(); 1 öncesine olay bittikten sonra
    7) arrayAdapter.notifyDataSetChanged();
7:Kodun açıklaması : yani ben yeni bir veri ekledim dizilerime yani bunu bir zahmet listende göster diyiceğim
Tabi devam etmeden normalde bütün veritabanı işlemlerini try and catch içerisine almak mantıklı olucaktır
get data methodu içerisindekileri alıyorum kabaca.İncelerseniz zaten neyi aldığımı görebilirsiniz.

Şimdi şu aşamada app'imizi açarsak bakalım getData çalışıcakmı verileri çekip gösterebilcekmiyiz
App ilk açıldığında Gözükülceği üzere bende direk daha önce eklediğim mona lisa geliyor normalde bir proglem olmaması lazım bende olmadı keza ama olduda bir proglemle karşılaştınız örneğin bir şekilde gözükmedi yada bir önceki kaydınz olmadı vs
Yapmamız gereken sqlite ile çalışırken sıkıntılar olabiliyor yapmamız gereken app'i silip bir tekrar yüklemek sonra gelip tek tek artname id arts hepsini bir düzgünce yaptıkmı diye kontrol etmek herşeyi doğru yazmışmıyız bir yüzde yüz emin olmak bir yanlış varsa düzeltmek sonra tekrar emulatoru açarak denemek.

Sıradaki aşama "VeriAktarımıNot.java"
 */