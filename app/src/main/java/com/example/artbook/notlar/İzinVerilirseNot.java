/*
    Kullanıcı izin Verirse Plan :
Daha önce izin yoksayı istedik peki ya varsa kullanıcıyı galeriye götürüceğiz galeriye götürme işlemi yine intent ile yapılıyor aktivite açmak yerine bu sefer galeriyi açıcağız peki nasıl yapılıyor.
    Kullanıcı izin Veririse Kod :
    1) if süslüsünden sonra "else { }" açalım.
    2) Else içerisinde : "Intent intentToGallery = new Intent()
2.Kod açıklaması 1 : intentToGallery dememin sebebi karışmasına engel olmak içindi. Geri kalan kod satırı ise buraya kadar bildiğimiz gibiydi fakat bundan sonra bilmediğimiz şeyler yazıcağız () normalde context'i yazıp nereye gidiceğimizi yazıoyrduk. Misal : (this,) gibi gibi.
Bu sefer ise ne yapıcağımızı yazıcağız "action" yazıcağız buraya () istediği şeyi zaten ands üzerinden görürüz bazı parametlerden birtanesi zaten "action" peki biz hangi action'u alıcağız şu actionu.
    2) "Intent intentToGallery = new Intent(Intent.ACTION.PICK)"
2.Kod açıklaması 2 : Pick Ne demek : toplamak demek bir yerden gidip birşey toplucağız. Demek oluyor bu. Peki gideceğimiz yer neresi ? , koyup.
    2)    "Intent intentToGallery = new Intent(Intent.ACTION.PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)"
2.Kod açıklaması 3 : Şimdi     "MediaStore.Images.Media.EXTERNAL_CONTENT_URI " ne demek : Beni al galeriye götür demek "URI" İSE :  aslında url gibi bir adres belirtiyor sadece webadresi değil dosyanın adresini belirtiyor aslında dosya nerde kayıtlıysa biz oraya gidip o urı alıp geri geliceğiz
Dosyanın nerde kayıtlı olduğunu anlıcağız bu işlemle beraber telefonun içerisinde yada hafıza kartında sonra hemen altına.
    3)startActivitiyForResult();
3.Kod açıkmalası 1 : neden for result :  bu sefer bir sonuç için gerçekten aktivite başlatıyoruz daha doğrusu en azından bir aksiyon başlatıyoruz adam () gidicek sonra eli dolu gelicek bir urı ile gelicek bize bu sonucun verilceği bir method olması gerekiyor bunuda işte
"startActivitiyForResult)="diyerek elde edebiliyoruz.
    3)startActivitiyForResult(intentToGallery,2);
3.Kod açıklaması 2 : Ve bakın bize bir intent soruyor () içerisinde " intentToGallery" koyabiliriz. Ama yine bize ,  sonrası bir Request Code soruyor.Daha önceki gibi daha öncekine requestcode1 demiştik.
Buna 2 diyorum. Böylece bunuda başka startActivitiyForResult'larda yapabiliriz yine ayrıştırmak için kullanıcağız.Buraya kadar anladıysak sorun değil RequestCodeleri birazdan kullanıcağız çok daha net anlıcağınızı tahmin ediyorum.

Şimdi 2 tane durumu ele almamız gerekiyor nedir izin yoksa ne yaptık (if) ile izin istedik. Şimdi izini kullanıcı verirse ne olucağını yazmamız lazım
Şöyle düsünebiliriz kullanıcı izin verilmesse bu bölümdede olduğu  üzere "else{ " içine yazmadıkmı galeriye gidicek evet yazdık ama bu sadece görsele tıklanınca geçerli yani kullanıcı resmi verirse daha doğrusu resme tıklıcak izni verdi sonra tekrar resme tıklarsa galeriye götürür ama biz proffesionel bir uygulama yazarken bu olsun istemeyiz.
Resme tıkladık izin ekranı geldi kullanıcı izni verdiği gibi galeri açılsın isteriz yada kullanıcı izinde vermiyebilir izni vermessede bu sefer hata mesajı gösterebiliriz kullancıya izin vermessen bu özelliklere erişimin yok diyebiliriz nasıl kullanıcağın diyebiliriz vs.
O yüzden bir tane mehod var onu kullanmamız gerekiyor o methodun adıda : "public void selectImage" içinden cıktım süslüsünden yani dikkat edelim yeni bir method .
    Kullanıcı İzin Verirse Kod 2 :
    1 : onrequestPermissionResult
1. Kod açıklaması : Permission Result : Yani izin istendiğinde bunun sonucunda ne olucağı buna çift tıklayıp seçersek bize bazı parametreler sunuyor şu şekilde.
    "    @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    } "
gibi bir permissions :     "@NonNull String[] permissions,"
iki request code : "int requestCode,"
üç : birde şu önemli "@NonNull int[] grantResults
Şimdi bizim requestCodemiz neydi iftek 1 ile istemiştim  ozaman
"public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {" hemen altına.
    2: if(requestCode == 1) { }
2.Kod açıklaması : Eşittir 1 ise "{}" içerisine bundan sonraki yapmak istediğimiz işlemleri yazıcağım eğer değil ise yapmama gerek yok belki başka bir izni ilgilendiriyor olabilir onuda başka bir yerde kontrol edebilirim birden fazla izin istiyorsam kullanıcdan app'imde şuanda öyle bir durumum yok
ama sonuçta selectImage method içerisinde  1 yazdık requestimize sonuçta burdada 1 'i kontrol etmem gerekiyor sonra..
    3) if(requestCode == 1) "{ }" içerisine yine if () yapıcağım
3.Kod açıklaması : "yine if yaptım " neden : grantResult bana verilen sonuçlar sonunda bir integer dizisi içerisinde geliyormuş bir kere " grantresult" içerisinde eleman varmı onu kontrol edeyimki yoksa zaten yine bu işlemleri yapmama gerek yok boş gelmiş olabilir.
Kullanıcı cancel'e bastı telefonu kapadı hepsi olabilir o yüzden.
    3)if (grantResult.length > 0  && grantResult[0] == PackageManager.PERMISSION_GRANTED) { }
3.Kod açıklaması "&&" ve işaretidir "Eğer length > 0 dan büyükse && ve "  "grantResult[]" bu dizi ya [0] elemanı ilk elamanı yani 0.indexi  == eşittir "PackageManager.PERMİSSİON_GRANTED" {}
Yani içerisinde eleman varsa ve bu elemanın ilki "0 " zaten tek birşey istediğim için tek bir cevap gelicek eğer izin verildiyse "(packagemanager.permission_Granted)" Misal burda .
Else yapıp permission denited ise şunu ozaman mesaj göster toast mesajı gibi birşey yapabiliriz peki  izin verilirse ne yapıcağım
selectImage methodu içerisinde elsede ne yaptıysam birebir aynısını yapıcağım kopyalayıp yapıştırabilirim yani intent ve startactivitiyforResultu.
Yani :
    4)Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    4.Kodun açıklaması .3.Kod açıklaması 3 son satırında.
    5)startActivityForResult(intentToGallery,2);
5.Kodun açıklaması . 3Kod Açıklaması 3.SonSatırında.

Yani kullanıcıyı alıcağım galeriye götürüceğim geriye kalıyor tek bir ele almamız gereken şey kullanıcı izin verildiği taktirde her türlü galeriye gidicek galeriye gittikten sonra ne olucak adam görseli seçti.
Ben o görseli alıp ne yapıcağım.
ImageView'emi koyucağım koymuccammı silicemmi app'imi kapıcağım ne yapıcağım onu belirtmem gerekiyor onuda bir sonraki derste görelim.
Sıradaki aşama " GörselSeçmekNot"

 */