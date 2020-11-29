/*
Ne demiştik biz daha önce main2Activity.java üzerinde "startActivitiyForResult" diyerek bir sonuç için akvitivetemizi başlatıyoruz ki bu zamana kadar if ile yaptığımız işlem başlattığımız şey bir aktivite değil bir galeriye götürmek
ve bunun sonucunda bize seçilen verilerin verilceği bir method verilmesi  lazım zaten "startActivityForResult"u sadece bu sebepten kullanıyoruz Bu methodun adıda. Dikkat edin Yine tüm methodların dışına cıkıcağım ama aktivitenin içerisinde kalıcağım. Main2Activitiy.java'dan yapıcağım.
"onactivitiy" yazarsak şunu arıyoruz "onActivitiyResult" çift tıkladığımızda ands bizim için methodumuzu oluşturcak ozaman.
            //Method Oluşturma Zamanı 1.
            1." @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }       //OnActivityResult Süslüsü Bitiş." şu şekilde bir hal alıcak.
Methodun Açıklaması 1 : Peki bu bize neler veriyor : "Bir RequestCode veriyor" bizim requestCodemiz bu sefer 2'ydi .Birkere bunu kontrol ediceğiz. Bu sefer birde "int resultCode" Result code veriyor.
Result code şöyle result code okeyi kontrol ediceğiz biz adam gitti bir resme tıkladı bu cancel olabilir kullanıcı seçmeyebilir vs saçma sapan birşey seçmeye çalışabilir.
Birde "Intent data" data veriliyor buda geriye dönen data kullanıcı ne seçti bunun adı bazen değişiyor şuanda ve aslında "ıntent" bu intent yapabilirler yine i yapabilirler d yapabilirler burda ne veriliyorsa biz tabiki kendi kodumuzda bunu kullanmamız gerekiyor
peki nasıl kullanıcağız.

        //Kullanım Kod Zamanı 1.
        1. if(requestCode == 2 && resultCode == RESULT_OK && data != null) {}
Kodun açıklaması 1 :
RequestCode Eşittir 2 ise if ile control ettim bu arada. "&&" ve "RESULT_ " yazdğımızda bir çok sonuç cıkıyor canceled felan var canceled'se birşey yapmama gerek olmadığından okeyi kontrol ediceğim.
"&&" ve data != null " data eşit değildir null ise ve {} Yani adam gitmiş resmi geri gelen veride aslında data null'da değil demekki artık işlemimi yapabilirim. Şimdi data bana ne veriyor bir ona bakalım.
Hemen altına gelip. ifin { içerisine yani.
    2. data.get
Kodun açıklaması  2 :
Şimdi data bana ne veriyor bir ona bakalım "data.get" dersek bize uri veriyor yani nereye kayıtlı olduğunun yolunu bu şekilde alabiliyoruz o yüzden benim tam olarak ihtiyacım bu aslında "data.getData();" yani.
Peki gelelim bunu uri olarak bir kaydedelim oda şu şekilde Uri imageData = data.getData();
   2.Uri imageData = data.getData();

    //Açıklama 3Y Code :
Şimdi bunu aldıktan sonrası aslında normalde çok kolay bir işlemdi fakat son versiyonda 2020 itibari ile biraz zorlaşmaya başlıyacak. Neden şöyle basladı aslında bizim bunu bitmape çevirebilmemiz daha öncesinde çok kolaydı şöyle.
    Şu şekilde main2Activitiy appCompat altında " Bitmap selectedImage;" diye tanımlıyorum diğer yerlerden ulaşabilmek için.
    Sonra 2.Codemizin altına gelip urinin yani.
    "selectedImage =
Diyorum normalde biz bu uri alıp bitmape çok kolay çevirebilirim nasıl. "selectedImage = MediaStore.Images.Media.getBitmap()" var bitmap'in üstü çizizli şimdi birşeyin üstü çiziliyse şu anlama geliyor
Tedavülden kalktı bu demek birşey tedaviden kalkarsa yerine birşey mutlaka çıkarır ands fakat yerine alternatif olarak çıkarttığı şey şuan için api 28 öncesi telefonlarda çalışmazken eskiside yenilerinde çalışmıyor şimdi böyle bir ikilimdeyiz.
Tabiki ya onu ya bunu seçmek değil buna bir çözüm bulmamız gerekicek peki bulalım önce eski telefonlar için olan kısmını yazalım.
 Sonrada yenisini yazarız "selectedImage = MediaStore.Images.Media.getBitmap()" () içerisinde bizden contentResolver diye birşey istiyor content resolver : içerik çözümleyici demek.Aktivitelerden türetilir ve bu içerik çözümleyici sadece birtanedir bunu kullanarak uri bitmape dönüştürme vs gibi.Çözümleme işlemlerini yapabiliyoruz
  bunu almak için  (this.getContentResolver()) dememiz yeterli ve (), bırakırsak yazdıktan sonra bize uri sorucak oda zaten ,imageData şeklinde uride almıştık yani.
 O halde eskisi için şunu yapalım.
    Kod Zamanı 2 OLD .
    1.selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
 Şeklinde oluyor -getBitmap- satırında bir hata veriyor bize altı çizili getbitmap'i kullanıyorsun ama diyor unhandled exceptions var diyor yani nedir bu :
 Burda bazı sorunlar çıkabilir try and catch içerisinde yapman lazım bunu diyor kendimiz try and catchi alabiliriz yada hata olan yerde alt entere basıp (surround with try/catch) seçeneğini seçip.
 Try and catch içerisine sok diye seçebiliriz oda şu şekilde hal alır.
    "try {
            selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
            } catch (IOException e) {
            e.printStackTrace();
            }"

Bir hal alır peki bunu aldıktan sonra bizim imageView'imizi tanımlamışmıydık biz ?
Hayır tanımlamamışız hemen bir tanımlama yapmamız gerekiyor o halde layoutumuzun içerisinde idmizi bir hatırlamamız lazım bunun için.
res/layout/activity_main2.xml'e girip idsini kontrol ediyorum "selectImage" yapmışım mainActivity içerisinde bir imageView tanımlıcağım. Ohalde.
    //Tanımlama 1
    1.) ImageView selectImage; yapıyorum keza bir edit text tanımlıycağım.
    2) EditText artNameText, painterNameText, yearText;
    3) Button saveButton;
 diyorum ve bunların hepsini onCreate altında tekrar bir getiriyorum. Oda şu şekilde.
    4) selectedImage = findViewById(R.id.selectImage);
    5) artNameText = findViewById(R.id.artNameText);
    6) painterNameText = findViewById(R.id.painterNameText);
    7)yearText = findViewById(R.id.yearText);
    8)saveButton = findViewById(R.id.button);
Şeklinde tanımlıyorum.
Ve geliyorum try { methodumuzun içindeki old kısmında selectedImagenin altına.
    Şu şekilde :
    "selectedImage.setImageBitmap(selectedImage);" yapmam yeterli oluyor  aslında bu çalışıcaktır ama dediğimiz gibi vu versiyon sorunu dolasıylabirşey daha yazıcağız onuda şöyle yapıcaz.

   Version İçin Kod 1
   1) selectedImage = MediaStoreye daha gelmeden try içerisinde "if (Build.VERSİON.SDK_INT >= 28){}
Ne yaptık :  eğer Bu telefonun buildi sdk ınti 28 den büyük veya eşitşe dedik burda yeni methodu yapıcağız } dah sonra "else" { } yazıcağız yani.
    2) } else { }
    yapıp eski methodumuzu içine yapıştırağım ifde ise yeni methodu yapıcağız.
    Yani şöyle olucak.
    "try {
                if (Build.VERSION.SDK_INT >= 28) {

                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);
                }"
Olurda biz kullanırken eski yeni methodu çözdülerse şu methoduda kullanabiliriz direk tabiki. "if (Build.VERSION.SDK_INT >= 28) {" içerisine
    3) ImageDecoder.Source
3.Kodun Açıklaması : Şimdi bu şu işe yarıyor aynı getBitMap gibi content resolveri kullanarak bir uri alıp bitmap'e çevirmek için geliştirilmiş bir sınıf aslında bunun için bir source oluşturucağız "source = "
Source kaynak demek ve yine "source = ImageDecoder.createSource() " dersek bizden () içerisinde ikitane şey istiyor contentresolver ve uri onuda şöyle yaparsak (this,getContentResolver(),imageData); demem yeterli burda.
Yani .
    3)ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);
 Şeklini alıyor.
 "selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);" burda yaptığımdan çok farkı yok tek farkı Aşağıda görebilceğiniz method
    4) Hemen altına "selectedImage = ImageDecoder.decodeBitmap(source)"
4.Kodun Açıklaması : Bu bizden bir source isticek () içerisinde onada source demem gerek. Gözükülceği üzere diğer code ile nerdeyse aynı sayılır sadece sınıflar farklı imageDecoder daha yeni daha modern bir sınıf
getBitmap eskidiği için bunu kaldırdılar yerine ımageDecoder'i getirdiler diyebiliriz.Hemen altına.
    5)imageView.setImageBitmap(selectedImage);
5: Kodun açıklaması yukardakinide dediğimizde yeterli bir hal oluyor .

Şimdi nerdeyse herşeyi bitirdik şu aşamada artık deniyebiliriz. Şuan eğer herşeyimiz tamamsa ilk kez tıkladıımızda bizden bir izin istenecek izni verdiğimiz taktirde galeriye gidilicek ve galeriye tıklanıcak
Galerimizde emulatorumuzda bir görsel olmuyabilir hemen nasıl alınıyor anlatayim emudan chroma gidelim herhangi bir arama yapalım misal mona lisa veya starry night olabilir ondan sonra görsellere gelip basılı tuttugumuzda
Görseli indirin dersek görseli indiricektir ve sonrasında tekrar app'imize geri dönersek Dowloands içerisinde çıkıcaktır resimlerimiz dowloand'a tıklıyalım açılsın açılıyor bizi farklı bir bölüme gönderiyor.
tekrar tıkladığımızda işlerde karışmıyor istediğimiz kadar bu işlemi yapabiliriz ve hepsinde çalışıyor tabiki boyutlarında bazı fırsatlar var onu constraintlerle vs ayarlıyabiliriz sonuç olarak şuan imageView'e gelme işlemi şuanda çalışıyor.
bunun daha düzgün durmasını istiyorsak constraint sorunu yaşanırsa selected imageye gelebiliriz res/layout/activity_main2.xml'den
İnferConstraint yaptığımızda otomatik constraint vermemis biz kendimiz verebiliriz veya layout_width ve layout_heighti kendimiz yapabiliriz mesela "width 300dp" "height 250dp" gibi yapabiliriz benimki normal ama daha statik daha düzgün duran görseli elimizle böylede yapabiliriz diye anlattım.
Olurda görsellerle ilgili bir sıkıntı olursa bu şekilde rahatlıkla çözebiliriz diye belirtmek istedim Şimdi buraya kadar geldik artık görsel seçiyor isimleri yaşı vs girebiliyor ne kaldı save basıp bunları sqliteye kaydetmek kaldı.
onuda biraz sonra birlikte yapalım.
Sıradaki aşama : Görsel Küçültmek Not şeklinde olucak.






 */