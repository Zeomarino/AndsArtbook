/*
Biraz önce anlatmaya çalıştığım sanat kitabım uygulamasını beraber bir şekilde yazıcağız yaptığım işlemleri düzgün bir şekilde not tutucağım.
Sizde anlıyabilir vaziyette olabiliceksiniz yani  ve bu aşamada sadece sqlite değil daha önce öğrenmediğimiz bir çok şeyi öğrenmeyi amaçlıyorum ve öğretmeyi tabiki.
Şimdi projemizde ilk olarak tasarımla başlıcağım şu sebepten projemizde ikitane aktivitemiz olucak bir seçtiğimiz sanatların kaydedildiği liste
ikincisi sanatı kaydettiğimiz veya gösterdiğimiz aktivite şimdi bu aktivitede görsel seçmeyi veya kullanıcıların galerisine gitmeyi kullanıcıdan izin isteme gibi gibi bir çok yeni işlemi öğreneceğiz o yüzden 2 tane aktivitemiz olucağı için hemen aradan çıkartalım ve birdaha bunlarla uraşmayalım istiyorum açıkcası o yüzdenki projemiz oluşturuldugu gibi bir aktivite daha eklememiz gerekiyor.
Zaten ikinci aktivitedende kodları yazmaya başlıcağız niçin çünkü öncelikle veri kaydedebilmek için bir fotoğraf seçmemiz bir editTextimizin olması oraya kullanıcının girdi yazması vs gerekiyor yani herşeyden önce 2.aktivimizde işe başlıcağız
O yüzden evvela 2 aktivitemi oluşturuyorum "com.example.artbook" 'a sağ tıklayıp yeni(new) diyorum ve Activity'i  ve galery'i seçiyorum. adını "Main2Activity" olarak bırakıcağım. Direk geldiği için.
Ama sizler başka yapabilirsiniz keza proffesionel bir şekilde yapıcak olsaydım eğer bende başka isim girerdim...
Şimdi gelelim res/layout/activity_main2.xml bölümüne.Herşeyden önce layoutumuzu bir aradan cıkarıcağız istiyorum.
        //Tasarım plan vakti.
Şimdi tasarımımızda ne istiyoruz birtane görsel olucak "bana tıkla" "görsel seçme için bana tıkla" "Select image gibi birşey yazıcak üstünde" altındada
"editText" ler olucak hangi verileri almak istiyorsak kullanıcıdan onları yazıcağız örneğin sanat eserinin adı , ressamın adı ,hangi yılda yapıldığı gibi gibi farklı verileri yada siz başka siz istiyorsanız onları.
Alabiliriz.  O yüzden herşeyden önce birtane görsel oluşturucağız  görsel oluşturmak için hazır ImageView görselleri değilde ben kendim bir görsel ekliyeceğim.
Bunun için bir tasarım programına ihtiyacımız var pek çok detaya girmiceğim elimizdeki herhangi birşeyi kullanabiliriz misal paintiniz varsa onu bile kullanabiliriz photoshop adope xd gibi farklı farklı tasarım programlarını kullanabiliriz.
Ben adobe xd'yi kullanıcağım. Şuan sadece select image gibi basit bir görsel elde etmek istiyorum zaten.

//Adobe XD aşamaları.
Custom size seçtim küçük bir kare yaptım içini boymaka istiyorsanızki ki istiyorum fill kısmından rengi seçiyorum  içerisinde sadece bir adet yazı olsun istiyorum o yüzden T kısmından birtane yazı ekliceğim.
Fontu kendini dizayn şeklinize göre yapabilirsiniz.Sonuç olarak ben textime "select image" yaptım  kullanıcı yeterki üstüne tıklıycağını anlasın yani...
Şu ikisini toplam seçtikten sonra sağ tıklayıp group yapabiliriz daha sonra "Mark for export" a tıkladıgımızda cıkarılabilir hale getiriyoruz.
Ve en sonunda File / Export / Selected dememiz yeterli
Bunu yapınca bize dicekki bunu tek birşey olarakmı atayim yoksa Android içinmi atayim Android için atatım tabiki hazır androidde tasarım yapıyoruz adınada select image diyelim mesela ve export edelim.
Yaptıysanız eğer drawble gibi dosyalar biriktiğini görebilirsiniz export ettiği yerde bu dosyaların aslında hepsinin içerisinde aynı dosyalar var fakat hepsinin boyutları farklı biri 8kb biri 11 kb
küçük dosyalar ama aslında hepsinin boyutu farklı yani bu farklı farklı boyutlarda vermesinin sebebi farklı boyutlarda cihazlarda kullanırken kolaylık olsun diye çok daha büyük bir tablette çalışılırsa açılırsa.
Daha büyük görseli küçük bir telefonda açılırsa daha küçük görseli ands otomatikmen kullanılıyor bunu doğru düzgün bizim projemize aktarabilmek için.
Proje dosyamızı bulmamız gerekiyor
Artbook dosyamda benim giriyorum içerisinde app'e ordan src'e ordan main'e ordan res'e gelip ve ben bu dosyaların hepsini alıp içerisine atıcağım.
Keza bakın drawblenin içerisine değil res'in içerisine atıyorum ve bunu yaptıktan sonra eğer ands geri dönersem.
Res'e sağ tıklayıp senkronize et dersem drawblemin içerisinede selectimage diye klasör görünücek bende group olarak gözüktü sıkıntı degil ve içerisinde farklı boyutlarda aynı select imagenin farklı
görselleri olucak. isimlerini "selectimage.png" olarak değiştirdim uyumsuz şekildeydi ve klasör olarak selectimageye döndü zaten.
eğerki paint vsde yaparsak tek bir görsel cıkarsa tabiki direk drawble içerisine koyup direk kullanabiliriz sadece bunuda bir genel kültür olsun diye anlatmak istedim.

    //Activity_main2.xml Tasarım zamanı 1
    1) imageView alıp app'imize atarsam project içerisinde selectimage çıkıcaktır ve bunu seçip istediğimiz gibi kullanabiliriz.
    2)onClick methodunu atıyorum "selectImage" diyorum onunda adına "idsinede aynı şekilde."
    3) Plain Text koyucağım koyduktan sonra textini siliyorum ve hint kısmına art name yazıyorum kullanıcı buraya bir sanatın ismini giriceğini anlasın mesela monalisa gibi bununda idsine"artNameText" diyorum.
    4) aynı şekilde 1 tane daha plain text getirceğim sanatçının ismini alabiliriz o yüzden textinide siliyorum bunun hint kısmına "painter name" yani ressamın ismini idsine "painterNameText" yapıyorum.
    5) aynı şekilde "plain text" yılını alalım istiyorum son olarak "idsine yearText" diyorum hintinede "year" diyorum
    6) bir tanede button ekliyorum butonumuzuda tüm bunları kaydetmek için kullanıcağım "textine save" diyorum onclick methodunada "save" diyorum
Tasarımla ilgili işimiz nerdese bitti "infer constraint " tusuna basarak otomatik constraintlerini vericeğim

    //MainActivity2.javaya girip hemen yazdığımız methodları yazalım hemen hızlıca unutmadan.
    //MainActivity2 kod zamanı.
      1) public void selectImage (View view) {}
      Select image var bu aynı zamanda yine view view olucak yani görünüm tarafından cağırılcak o yüzden onu yazıyorum birde.
      2) public void save(View View) {}
      Buda kaydetmek için sqlite fonsiyonlarını vs yazdığımız yer olucak şimdi bunu açıp emuda test etmek isterdik fakat edemiceğiz neden çünkü mainActivityden mainactivity2 ye gitmenin şuan bir yolu yok yazmadık yani.
      O yüzden nasıl gözüküyor onu bile göremiceğiz o sebeple burda durucağız bir sonraki aşamada hemen mainActivity'e küçük bir menü eklemeyi öğreneceğiz bu menü sayesinde mainActivity2 ye nasıl gidiceğimizi çözüceğiz.
  Sıradaki aşama "MenuEklemekNot"
 */