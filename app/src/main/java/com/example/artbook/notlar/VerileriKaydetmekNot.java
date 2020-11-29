  /*
Şimdi bu aşamaya kadar sqlite veritabanına kaydediceğimiz bütün değerleri aldık "string Artname" , "String painterName" "String Year" isim sanatçı ismi yıl burda ve byte diziside ekledik.
o yüzden artık veritabanımızı oluşturup kaydetme işlemini yapmak kalıyor geriye
Birkere herşeyi try and catch içerisine alıyorum daha önce yaptığımız gibi.
    //Veri Kaydetmek 1 :
    1) byte dizisi hemen altına "try { }"
    2 try hemen yanına yada altına "catch (Exception e) {} "
Diyorum  sonra gelelim try içerisine burda öncelikle ne yapıyorduk öncelikle databasemizi oluşturuyorduk Nasıl oluşturuyorduk :
    3) SQLiteDatabase database =
3 Kodun Açıklaması : Diyorduk ve aktiviteden türetiyorduk şu şekilde " this.OpenOrCreateDatabase()" diyorduk  () içerisinde bize ne soruyordu bir database ismi bir mode birde cursor factory istiyordu
Şimdi isim mesela "Arts" , Modumuz yine "MODE_PRIVATE" , factory yine istemiyorum "null" Yani :
    3)SQliteDatabase database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null); şeklinde oluyor sonra gelelim databaseye hemen altına 1 tablemizi yaratalım
    4) database.execSQL("")
 4.Kodun açıklaması bu şekilde bıraktıktan sonra bu tablemizi oluşturmak için daha önceki yaptığımızın aynısını yapıcağım yani "(CREATE TABLE IF NOT EXISTS ) yazdıktan sonra burda tabloumuzun adını söylüceğim (arts) diyorum küçük a ile bu sefer
 Şimdi içerisinde neler olucak gelelim oraya Bir isim olucak (artname VARCHAR , paintername VARCHAR , year VARCHAR, image BLOB)
 Şimdi şunu hatırlamakta fayda var string varchar demek sqlitede , imageye şu şekilde dememiz gerekiyor ama BLOB , verileri blob olarak kaydediyoruz bu şekilde sqlitede sonuçta burda bir byte dizisi kaydediceğiz aynen byte dizilerini BLOB olarak kaydedebiliyoruz sqlitede.
 Birde Herşeyden önce idmiz var "(id INTEGER PRIMARY KEY , artname VARCHAR , paintername VARCHAR , year VARCHAR, image BLOB)" Kodumuz şu şekli alıyor.
    4) database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY , artname VARCHAR , paintername VARCHAR , year VARCHAR , image BLOB)");
Tam olarak tablomuz istediğimiz gibi bu şekilde olucak  peki sonra  ?
    5) database.execSQL("INSERT INTO arts ")
5.kodun açıklaması :
Şimdi burada daha önce öğrenmediğimiz birşeyi öğreneceğiz
Şimdi biz gelip hemen altına "database.execSQL("");" diyip  "INSERT INTO arts" diyebilirim Ve ondan sonra mesela (artname, paintername, year,image)'yi diyebilirim ama ) Values'i yazabilirmiyim yani şu şekilde :
    5.database.execSQL("INSERT INTO arts (artname, paintername, year, image) VALUES ()") şekinde values'i yazamam neden çünkü normalde bu zamana kadar statik bir şekilde değerleri yazmıştık fakat şuan değerleri bilmiyorum
Değerler şuan değişkende "String artName , painterName , year"  yani o an hangi değeri aldıysa o değişken değerini yazdırmam lazım VALUES'e gelip değişkende yazamam o halde nasıl yapıcağız.
Öncelikle bu bir string : Bunun için SQL'de özel bir gösterim var VALUES (?, ?, ?, ?)"); BURAYA soru işareti koyuyoruz tam olarak 4 adet neden 4 adet diye sorarsak : artname , paintername , year ,image 4 adet oluyor sırasıyla her biri için 4 tane soru işareti koyuyorum Şimdi bu soru işaretlerinin değerlerini.
Ayrı ayrı atıcam ama bunun için bunu EXECSQL ile değil başka birşey ile yapmam gerekiyor bunu normal bir string olarak kaydediceğiz
    6)String sqlString = "INSERT INTO arts (artname, paintername, year, image) VALUES (?,?,?,?)";
6.Kodun açıklaması : Dediğim gibi yeni bir string şeklinde yaptım adına sqlString dedim ve 5 kodumuzdaki içerikleri yapıştırdım bu normal bir string şuanda ama bu normal string'i ben sqlde çalıştırılabilcek bir duruma çeviricem bununda adı "sqlitestatement"
    7) Hemen altına " SQLiteStatement sqliteStatement = database.compliteStatement()
7.Kodun açıklaması : Adına yine sqliteStatement dedim - database.compliteStatement dediğimizde () içerisinde neyi ben complite edim diye sorucak buna işte bu sqlString'i al complite et diyebiliriz.
Şimdi bu bir stringi sqlde sql komutu gibi çalıştırmaya olanak tanıyor üstüne üstük şuna olanak tanıyor. Öncelikle 7.Kodumuzu bir tekrar yazim.
    7)SqliteStatement sqliteStatement = database.compileStatement(sqlString); Daha sonrasında şuna olanak tanıyor.Ben bunu çalıştırmadan
    8) sqLiteStatement.bindString
8.Kodun açıklaması : Buna olanak tanıyor diye geldik. sqLiteStatement.bindString dersem Bize 2 adet şeyi soruyor " Bir index : İki : Neyi bağlıcağım "
Bind ne demek : bağlamak demek yukarda soru işaretleri koyduk 6.Kodumuzda : Şimdi bu soru işaretlerini değişkenle  bu method yardımı ile bağlıyabilirim
Misal 1.Yi şöyle yapalım "sqliteStatement.bindString(1,artName); şeklinde yapalım burda bu arada indexler birden başlıyor dizi gibi düşünmeyelim .Yani :
    8)sqliteStatement.bindString(1,artName);
    9)sqliteStatement.bindString(2,painterName);
    10)sqliteStatement.bindString(3,year);
    Şeklinde ekliyeceğim Resimde ise :
    11)sqliteStatement.bindBlob(4,byteArray) 11: Kodun açıklaması bu sefer bind blob dedim dediğim gibi : 6 koddaki soru işaretine yani imageye : Byte array'i bağla diyiceğiz
//Bakın integer kaydetmemizin diğer sebepleri bind integer diye birşey yok bind yazdıktan sonra tabiki integer yerine double veya long olarakta kaydedebiliriz ama string olarak kaydedip istediğimiz zaman integere çevirebiliriz ben year'ı o yüzden direk string olarak kaydettim dahada işleri karıştırmamak için
    Ve en sonundada
    12)sqLiteStatement.execute();
12.Kodun açıklaması : Execute diyerek bunu çalıştırıcağız.
    13)finish();
Peki bu işimiz bittikten sonra bu aktivitedemi durucağız hayır tabiki bir önceki aktiviteye geri dönüceğiz o sebeple catch süslüsü altına "finish();" yazıcağım
Finish ne yapıyordu aktiviteyi komple bitiryordu böylece açıkta kalmaz hafıza olarakta verimli olur yani bir önceki aktiviteye geri döndürür bizi
App'imizi şu aşamada çalıştırırsak  : Bakalım herşeyi doğru yazdıkmı tabi kötü olan şey şu şuanda herşeyi doğru yazdıkmı göremiceğiz çünkü verileri çektiğimiz bir aktivitemiz yok bunu becerdiğimizi düşünürsek
Geri dönüceğiz onu yapıcağız şimdi add art diyip 1 resim şeçiyorum   yearları dolduruyorum saveye bastığımızda bakalım ne olucak save bastığım gibi beni ana mainActivty'me getirdi sqliteye umarım kaydedilmiştir eğer yanlış birşey yapmadıysak kaydedilmemesi için bir sebep yok.
yanlış birşey yaptıkmı yapmadıkmı verileri çektikten sonra görüceğiz onuda bir sonraki aşamada yapalım
Sıradaki aşama "VerileriÇekmekNot.java"

 */