/*
Şimdi bizim bir sonraki adımımız görsel seçmek herşeyi bir alıp toplayalımki bunları artık sqLiteye kaydedicek hale gelelim
Görsel seçmek için kullanıcıdan izin nasıl alınır evvela bunu öğrenmemiz gerekiyor peki neden?  Çünkü :  bu tarz işlemler yaparken görsel seçmek , internet kullanmak , kullanıcının konumunu almak , mesajlarına ulaşmak vs gibi farklı farklı işlemler yapıcaksak kullanıcıdan bunların iznini almamız gerekiyor Fakat izinler ikiye ayrılıyor.
Bazı izinleri sadece bu kullanılıcaktır diye belirterek kullanıcı uygulamayı indirirken kabul etmiş saydırabiliriz bazı izinleri kullanıcıya açık açık sormamız gerekiyor Misal : Ben senin galerine gidiceğim izin veriyormusun gibi burda onu yapıcağız peki.
Bunları neye göre belirliyoruz yani nereden biliceğiz kullanıcıyamı sorucagımızı yoksa sadece belirteceğimizimi Şöyle :
Şimdi bütün izinler manifest/AndroidManifest.xml'e koyuluyor bir kere Manifest'e çift tıklarsak manifestin içerisinde aktivitemizin daha doğrusu uygulamızın yani applicationumuzun ana ayarları var mesela.
"<activity android:name=".MainActivity2"></activity>
        <activity android:name=".MainActivity">" Şu şatırda belirtildiği üzere 2 adet aktivite var Main2Activity ve mainActivitiy ve bu :
        "    <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>"
Main ve launcher dediği şeyde ilk açıldığında hangi aktivitenin gözüküceği mesela  : " <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>"
Gözükülceği üzere mainActivity'nin launcher olarak tanımlandığını görebiliyoruz bu "AndroidManifest.xml" içerisine daha hiç application'a gelmeden < açarsak packegenin altında yani.
uses-permission diye birşey çıkar. Bu uses permission'a çift tıklarsak şöyle birşey oluyor.
"<uses-permission android:name="  tam burda "" istiyeceğimiz izinlerin coğu var çok uzun bir liste tabiki ilgili izini istememiz gerekiyor sadece bizim istediğimiz izin bu durumda.
"READ_EXTERNAL_STORAGE" olarak sunuluyor çünkü kullanıcının hafızasına ulasıcağız.Oda şu şekilde oluyor "<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE""
Bunu bir seçelim çift tıklıyalım > kapatalım bizim için kapatsın ands oda şu şekilde oluyor "<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>"
otomatik tanımlamazsa birebir aynısını yazabiliriz dert değil sonra gelelim biraz önceki sorduğumuz soruya biz external_storegeyi <application içerisine yazsam okeymi yoksa kullanıcıya açık açık sormalımıyım
Bazı izinlerde aynı bu şekilde application üstüne yazsak yeterli mesela internet izni çünkü bu izin çok yaygın internet kullanmıyan app neredeyse zaten yok keza kullanıcının bunu vermesinde büyük ihtimal bir sakınca olmaz ve uygulamayı indirilirken bu zaten kullanıcıya haber veriliyor ama kullanıcının galerisine gitmek baya bildiğimiz özel bir durum
İşte o yüzden bu özel durumu ayırt edici bazı ipucuları veriyor bize ands örneğin : "READ_EXTERNAL_STORAGE" yi kopyalayıp google gidersek arattığımızda Manifest.permission diye zaten android developer sayfasında bize birşey çıkıyor
Buraya girdiğimizde "https://developer.android.com/reference/android/Manifest.permission" Manifest permission içerisinde aklımıza gelebilcek bütün izinler var
Bizim aradığımız READ_EXTERNAL_STOROGE misal gelip tıklarsak buna gözükülceği üzere "read_external_storoge" iznini alırsak bize ne yapıcağımızı vs anlatıyor burada şöyle bir yer var burası önemli
"Protection level : dangerous " diyor yani tehlikeli diyor yani eğer bu izinlerde protection level dangerous görürsek demekki izni açık açık sormak zorundayız eğerki görmezsek ozaman dümdüz işlemlerimizi yapabiliriz misal burdan
internet iznine gidelim burda internet iznine "protection level normal" diyor normalse sadece manifeste yazıp geçebiliriz bazılarında dangerous diyor dangeroussa sormak zorundayız yani peki nasıl sorucağız.
"AndroidManifest.xml"'e yazıcağız bu arada buraya yazmadanda olmaz hem yazıcağız hemde main2activity içerisinde selectimageye tıklandığında bunu sorucağız.
Peki nasıl sorucağız :
Birkere birkere bu izni sadece birkere sormak yeterli yani kullanıcı izni verdikten sonra her tıklandığında tabiki sormucağız yani kullanıcı bu uygulamayı sadece ilk kullandığında bu izni bize vericek
O yüzden bir kontrol ederek buna başlıcağım bu izin daha önceden alınmışmı diye eğer alınmıssa tekrar tekrar bu izni istememize gerek yok ama eğer alınmamıssa mecburen istiyeceğiz peki nasıl kontrol ediliyor.
Gelelim aşaşamıza  : Main2Activity.java içerisinde - public void selectImage(View view) { süslüsü içerisine girip kodlarımızı yazalım.
        /Kod zamanı 1 :
       1) if (ContextCompat.)
    // ContextCompat ne alaka niye kullanıyoruz derseniz :"checkSelfPermission" bunu arıyoruz yani burda izin varmı yokmu onu kontrol et diyor
    Peki : ContextCompet nedir : ContextCompet aslında api 23 öncesindeki ve sonrasındaki değişiklikleri bize hissetirmeden çalıştırmak için geliştirdiği bir yöntem android'in neden ?
Çünkü android 23 öncesi yani api 23 öncesi bizim bu izinleri istememize gerek yoktu manifest'e yazıp geçebiliyorduk yani şöyle söylemek gerekiyor  Daha önceden belirtiyorduk ayrı bir izin istememize gerek yoktu.
Yazıp geçiyorduk aynı şuan internette yaptığımız gibi ama sonrasında read external storege izinlerine vs baktılar herkes herşeye ulaşabiliyor güvensiz bir hale geldi ve dolasıyla oda doğru olmaz diyip kullanıcıdan bunu açık açık istemeye başladılar fakat eğer kullanılan telefon
api 23 den önceyse bu sefer bize sorunlar çıkarıcaktır misal çalışmıcaktır kod o yüzden "ContextCompat'ı kullanıyoruzki 23 ve öncesiyse hiç bu işe bulaşmayalım 23 ve sonrası içinde izni istiyelim bizim için kod zaten bunu kendiliğinden otomatik yapıcak" o yüzden başka bir kod kurs veya internette ContextCompat kullanılmadığını görüyorsak yanlış yapıyorlardır yada sdk'leri zaten minimum sdk seviyesi 23 den yukarıdadır diye düşünebiliriz.
O yüzden : ContextCompat.checkSelfPermission() ) diyorum ve bu bana ikitane şey soruyor () içerisinde bir : context'i soruyor context dediğimiz aktivite  oluyor dolasıyla this, diyorum iki : string permissionu soruyor.
Hangi stringi  Hangi permissionu kontrol ediyim diyor  permissionlara anlattığım gibi Manifest.permission.READ_EXTERNAL_STOROGE şeklinde ulaşabilirim manifest dediğimizde hangisini import ediyim diye sorarsa android olanı import etmemiz gerekiyor
Buna şöyle birşey diyebiliriz "!=" eşit değil ise PackageManager.PERMİSSİON_GRANTED diyebiliriz "package manager" : diyince bir izin verildi "PERMİSSİON_GRANTED " birde  "PERMİSSSİON_DENİDED " izin verilmedi var.
Biz PERMISSION_GRANTED'İ kontrol ediyoruz ya izin verilmediyse kodumuz şu şekilde bir hal alıyor.
    1)if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){ }
Şimdi { içerisinde izin verilmediyse ne yapılıcağını yazıyoruz birde izin verilirse var Hemen  if'in süslüsüden sonra.} "else {" yazıcağım.
İzin daha önce verildiyse ne yapıcağız onuda "else {" de yazıcağız. if'de izin istiyeceğiz elsede kullanıcının galerisine gidiceğiz izin istemeyi görelim.
    2) ActivitiyCompat.requestPermissions
Request Permissions : izinleri iste demek tek bir izinleri iste diye bir method yok izinleri iste diye bir method var ve bizden yine bir aktivite istiyor buna yine this diyebiliriz birde permissions'ların girildiği bir string dizisi istiyor.
dizi ile girmeye ne gerek var biz bir izin istiyeceğiz diyebiliriz ama methodu böyle yazmışlar o yüzden bir string dizisi içerisine hangi izni isticeğimizi griiceğiz sonrasında birtane requestcode istiyor bizden bununda ne olduğuna bakıcağız merak etmeyin.
Şimdi activitiymiz this, Permission string dizisi istiyor hemen "new String[] diyip { açıyorum hiç bosu bosuna ayrı bi string dizisi felan oluşturmadan  {} içerisinde Manifest.permission.READ_EXTERNAL_STORAGE diye
yazıyorum bunu } dan sonra  , bırakıyorum şimdilik requestCode 1 yazıyorum ve kodumuz şu hali alıyor.
    2) AcitivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
Ne yaptım : Activity istiyordu verdim String dizisi içerisinde hangi izni isticeğimi soruyordu "new String[]" yeni bir string dizisi oluşturdum ve içine tek bir eleman koydum "{Manifest.permission.READ_EXTERNAL_STORAGE}" olarak
Ve bize bir izin kodu sordu bu şu işe yarıyor birden fazla izin isteme isteğinde bulunabiliriz hangisinde hangi istekten hangi cevap geldi bu kodları kullanrak aslında bakıyoruz buraya 1 yerine 100'de yazabilirdim 1000'de yazabilirdim
Herhangi bir integer yazabilirdim birazdan ama izinler gelince ne yapıcağız diye bir method yazıcağız orda bu requestcode bize lazım olucak o yüzden buraya ne yazdığımızı aklımızda tutmamız gerekiyor isterseniz 1 tane integer değişkeni oluşturabiliriz sadece bunun için bile
Buraya kadar geldiysek bir duralım izin nasıl kontrol ediliyor anladık izin nasıl isteniyor anladık birazda detaylarına girelim izin istedikten sonra ne yapıcağız kullanıcı galerisine nasıl gidiceğiz gittikten sonra ne yapıcağız hepsini tek tek ele almamız gerekiyor
Sıradaki aşama : "İzinVerilirseNot" şeklinde olucak.
*/