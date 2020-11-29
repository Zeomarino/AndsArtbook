/* Artık kullanıcı görsel seçebiliyor isimleri vs yazabiliyor
Bunların hepsini şuanda alalım ve SQLiteye kaydicek hale getirelim Şimdi bunları almak kolay özellikle yazı olanı öncelikle save methodunu bulalım.
MainActivity2.java içerisinde mesela ne alıcağız.
    KodZamanı 1)
    1) String artName = artNameText.getText().toString();
    2) String painterName = painterNameText.getText().toString();
    3) String year = yearText.getText().toString();

    Şimdi yılı tabiki integer veya long vb herhangi bir şekilde kaydetmek bizim elimizde fakat yıl olarakta kaydedebiliriz çünkü sqlite gerek yok yani string olarak kaydet geri çektiğinde isterse integere dönüştür çarp vs vs birçok şey düşünebilir ve bununda aslında birazdan öğreneceğimiz konu ile ilgili bir bağlantısı var neden böyle string şeklinde kaydetmeyi tercih edebilceğimiz bazen görüceğiz.
Tabi integer kaydedemezmiyiz tabiki kaydedebiliriz şuan için işleri basit tumak için string olarak tutuyorum sadece çünkü zaten şimdi görsel kaydetmeyi görüceğiz çünkü daha demin görmedik integeri gördük varchar'ı gördük görseli görmedik.
Şimdi görselin ne diye geçtiğini dahi bilmiyoruz sqlitede birazdan öğreneceğiz ama öncelikle yapmamız gereken şey görseli veriye çevirmek çünkü biz görselleri bitmap olarak veritabanlarına kaydetmiyoruz bu sadece android için veya sqlite içinde değil aynı zamanda diğer veritabanlarındada ileride zaten cloud veritabanlarında vs çalışacağız firebase vs vs oradada bizim bir görseli veriye çevirmemiz lazımki bir yere kaydedelim sonra veriyi alıp tekrar görsele çevirmemiz yeterli oluyor aslında. Peki bunu nasıl yapıyoruz bizim bitmap'imiz neydi.
MainActivitiy2.java içerisinde " Bitmap selectedImage;" save methodunun altına gelip.
    //KodZamanı 2)
    1) selectedImage.compress dersek
Kodun açıklaması 1 : Compress şu işe yarıyor compress bizim bu veriye çevirme işlemini yaparken kullanıcağımız özellikleri belirtmemize yarıyor bizden sadece 3 şey istiyor sonuç olarak .
Bir :  Hangi formatta bu resmi çeviriyim mesela pngmi jpegmi vs İki :  kalitesi ne olsun 0 ile 100 arası bir kalite soruyor 100 en yüksek sayı 0 en düşük Üç :  Birde OutputSteam dediğimiz bir bite verisi daha doğrusu bite dizisi haline getirebilmek için bunu veriye çevirmek için kullandığımız bir sınıf şimdi o yüzden
Bir PNG  yazıcağım formata     png yazarsak şu şekilde çıkar zaten " Bitmap.CompressFormat.PNG" daha sonra , koyucağım İkinci şey zaten kalite bu çok önemli aslında Şimdilik 50 diyiceğim ama açıklıyacağız çünkü burada yaptığımız kalite çokta boyutu değiştirmeyebilir sqlitede boyutlar çok önemli 1mb üstünde görselle çalışırsak sqlite çökebilir o yüzden biz görsellerimizi küçültüceğiz sadece buradaki quality ile değil birazdan kendi methodumuzu yazarak küçültüceğiz o sebeple şimdilik bunu ortalama olarak 50 de bırakıyorum üç outputstream'i soruyor
Şimdi bu outputStream nasıl oluşturuluyor Öncelikle bir onu görelim selectedImage.Compress'in hemen üstüne.
    2) "ByteArrayOutputStream"
    2.Kodun açıklaması : Bunu arıyoruz keza yazıpta çağırırsak bunu kullanarak biraz sonra yapıcağımız işlemi yapabiliriz adına "outputSteam" diyorum = koyuyorum ve tek yapmamız gereken bunu yeni bir obje olucağını söylemek.
    2) BytreArrayOutputSteam outputStream = new ByteArrayOutputSteam(); Şeklinde oluyor yani.Daha sonra bunu alıp 1.Kodumuza dönersek.
    1)selectedImage.compress(Bitmap.CompressFormat.PNG, 50,outputStream); şeklini alıyor sonrasında tekrar bu outputStream'i çağıracağız.
    3) outputSteam.toByteArray();
    3.Kodun açıklaması :  Şimdi bu şekilde ByteArray dersek istediğimiz byte dizisini bu görseli veriye çevirme işlemini artık bitireceğiz tam olarak bunu yapıyoruz tabi bunu yaptığımızda bunu bir değişkene atamamız lazımki sqlitede aslında o değişkeni kaydedelim yani :
    3) byte[] byteArray = outputStream.toByteArray();
    Bunuda bir byte dizi olarak atıcağım o yüzden şöyle byte[] şeklinde gösteriyorum adınada byteArray gibi birşey diyebilirim  Tek yaptığımız işlem şuan bize biraz yabancı gelmiş olabilir ama görseli almak ve veriye çevirmek.
Byte array outputStream sınıfını kullanarak bunu yapıyoruz görselin şeklini belirtiyoruz kalitesini belirtiyoruz ve çeviriyoruz ama bu bizim için yeterli değil  biz aslında gerçekten görselleri küçük olduğundan emin olmamız gerekiyor Bunu nasıl yapıcağız ?
Bunun için yeni bir method yazıcağız yazmak zorundamıyız diye sorarsak değiliz ama yazalimki birazcık güvenli olsun çok büyük görseller internetten indirip seçebilir kullanıcılarımız bu yüzden bunu yapalım bu arada sqlite ile bu programı appi yapmak zorundamıyız çokmu mantıklı hayır değil.
Bunu cloud'dada yapabiliriz bu verileri internettede saklıyabiliriz telefonunuzda bukadar görsel saklayıp şişirmenin manası yok ama olurda görseli sqliteye kaydetmemiz gereken bir durum olursa nasıl yapıldını öğrenmemiz gerekiyor  o yüzden sqlite 1 mb üstü dosyalarda çöküyor bunları yapmıyalım diyerek geçmekte olmuyor bütün detayları görmemiz gerekiyor
Şimdi o yüzden 1 tane yeni method yazıcağım ve daha küçük bir görsel nasıl oluşturulur onu görüceğiz Birkere public void değil "public Bitmap" yazıcağım çünkü bunun sonunda bir bitmap elde etmek istiyorum mesela makeSmallerImage gibi birşey diyebilirim ozaman :
        /Method oluşturma Zamanı :
        1) public Bitmap makeSmallerImage() {} şeklinde oluşturucağım
 Methodun açıklaması 1 : Bunun içerisinde () kendimden yani developerden 2 tane şey isticeğim Bir: Birtane bitmap isticeğim adına "image, diyebiliriz" birtanede int isticeğim onunda adına "maximumsize" diyiceğim
Şu şekilde oluyor "public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {} "
Bu nedemek :  ben büyük görseli yazıcağım "bitmap image " makeSmallerImage diyince mesela selectedImage.compress'teki selectgedImage mantığıyla buraya vericeğim bu verdiğim imageye birde "maximumSize" vericeğim ve ona göre en fazla bukadar büyük olsun diyiceğim onuda şöyle yapıcağım.
misal görsel yataysa örneğin 1500 x 1200 gibi görselse biz sadece bunu 300 x 200 yap bunu diyiceğim 300 x 200 gibi birşey yaparsamda en fazla 100 200 kb lık bir şey ortaya cıkıcaktır oda bizim için gayet yeterli olucaktır birkere bunu anlamak için öncelikle şunu anlamamız lazımki.
Deminki görselimizden düşünelim Hatta adobe xd'yi açalım yine gerçi sizin açmanıza ihtiyaç yok Sadece daha iyi ne yaptığımızı anlatabilmek için üstünkörü anlıtcağım ben Resim eğer dikdörtgen bir şekildeyse misal boyut w 851 h 362 olan bir resim biz bu 852 ve 362 yi alıcağız bunu aynen aynı oranı tutarak kücültmek istiyorum bunu abuk subuk bir hale getirmek istemiyorum 850 x 362 neyse o boyutta kücültmek istiyorum
Ve buranın en fazla 300 gibi birşey olmasını istiyorum o yüzden önce suna bakıcağım görsel yataymı yoksa dikeymi onu anlıcağım dikeyse yüksekligini en fazla 300 yap diyiceğim yataysa genişliğini en fazla 300 yap diyiceğim ve aynı oranda diğer tarafı küçült diyiceğim yapıcağımız islem resim kücültmek ama cözünürlük bozmamaktan ibaret image compress yani
Aslında kolay bir işlem yapıyoruz ama ilk defa yaptığımız için karışık görünebilir bunun sqlite veya app ilede ilgisi yok benim tamamen kendim uydurarak yazdığım birşey gibi düsünebiliriz küçültme fonksiyonu
    //Method içerik doldurma Zamanı :
    1. Methodumuzun içerisine "  int width = image.getWidth();"
Birinci Kodun Açıklaması :  Bir width oluşturdum ve dedimki "image.getWidth(); " image.getWidth ile bitmaplerin genişliğini alabiliriz tabiki aynısını height içinde yapıcağım
    2. "int height = image.getHeight();"
 2.Kodun açıklaması:  Şuanda ne yaptık uzunluğunu ve genişliğini aldık height uzunluk.

Peki bitmap'in uzunlugu ve genişligini birbirine bölersek ne elde ederiz biraz önce bahsettiğim gibi 800 x 300 gibi aradaki oranı bulmak istiyorum bu oranı integer olarak bölmüceğim float yada doubleye çevirip bölüceğimki böylece aslında kesirli bir rakam elde edersem yuvarlanmasın mesela 300 ü 200 e bölüp 1 bulmayim 1.5 bulim felan.
O yüzden bunlara float diyiceğim mesela bitmapRatio olsun bunun adı o 2 sinin birbirine bölümünün adı
   3.float bitmapRatio = width / height
3.Kodun açıklaması : Width'i height'a bölüceğim ama şuanda bu iksiide integer olduğu için yani width'de integer height'te integer sonuç yine bana float yazsamda integer gibi gelicektir o yüzden ben bu ikisinide float'a çevirip bölüceğim.
(float) eğer başlarına gelip (float) yazıp paranteze alırsak bunu float'a çevir demek bu."float bitmapRatio = (float) width / (float) height;" şeklinde float'a çevirip bölüyoruz misal width 300'se 300.0 'ı bölüyor height'te aynı şekilde 200'se 200.0 'ı bölüyor Eğer bunun sonucu birden büyükse
Nedemek bu :  width daha büyük yani genişlik daha büyük demek bu yani resim yatay demek yani.
    4. if(bitmapRatio > 1) {}
4.Kodun açıklaması : if bitmapRatio büyük bir ise burda resim yatay
    5.else{] " resim dikey
Şimdi resim yataysa ne demiştik maximum kaç yapıcaksak mesela 300 x 200 yapıcaksak 300'ü yatay kesime vermemiz lazım yani if(bitmapRatio) ' da width'i maximumSize eşitliceğim.
    6)width = maximumSize; elsede ise height'ı maximum sizeye eşitliceğim
    7) height = maximumSize; şeklinde
Ve if methodunun içerisinde height'ı yine aynı oranda bölüceğim height'ı aynı oranda küçültmem lazım o yüzden "width = maximumSiz;" hemen altına.
    8) height =
 8. Kod açıklaması : Height ne olucak width'i bitmapRatio'ya bölersem bunu bulabilirim tabi width'i direk bitmap ratioya bölersem float vericektir "height = width / bitmapRatio;" şeklinde yaparsam o yüzden şöyle yapalım.
    8) height = (int) (width / bitmapRatio);
Aynı şekilde else içerisindede
    9) width = (int) (height * bitmapRatio);
9. Kod Açıklaması : Width = bu seferde height çarpı (*) neden : çünkü bitmapRatio şuan birden küçük o yüzden bölmek dahada büyütür çarpmamız lazım
Ve bütün bunlar bittikten sonra şöyle birşey yapıcağım "return diyiceğim "
    10 ) return Bitmap.createScaledBitmap()
 10.Kodun açıklaması bu methodu kullanıcağım () içerisinde bize şunu soruyor : Bir :  sourceyi soruyor yani hangi görseli küçültüceğini soruyor. Görselin kendisi "image" İki : Hangi boyutta yapayim diyor width'ini soruyor bu "width"
 Hangi heightta yapayim diyor "buda height" birde filter yapayimmi diyor buda " true" yap diyorum ve bu bana işte bize küçültülmüş bir bitmap veriyor .Bunu else süslüsü dışında ama . public bitmap süslüsü içerisinde yazıyorum.
 Eğer karışık geldiyse  : üstünden hemen geçelim.
 1 Kodumuzda - Width'i aldım  , 2 Kodumuzda - Height'ı aldım , Yani burda genişliği ve yüksekliği aldım
 3.Kodumuzda :  yataymı dikeymi onu kontrol ettim yataysa alt tarafını uzun yaptım yani yatay tarafını uzun yaptım dikeyse dikey tarafını uzun yaptım ve diğer tarafıda aynı oranda küçülttüm tek yaptığımız bu.
 Ve 10.Kodumuzda - Bitmap'in kendi fonksiyonunu kullanarak yeni bir bitmap oluşturdum  o yüzden misal. Save methodunun içerisinde string'in altına gelip yeni bitmap'imi oluşturabilirim
    11.Bitmap smallImage = makeSmallerImage(selectedImage,300);
 11.Kod Açıklaması : Small ımage adı eşittir makeSmallerImage'yi cağırıp (selectedImageyi) verirsem selectedImageyi alıp small'a çeviricektir. tabi birde kaç boyutta yapacağını verebiliriz 300'de verelim misal.
 Alt tarafı 300 olsun misal üst tarafıda ona göre küçültülmüş olsun .Ve bitmap smallImage dedikten sonra
 "selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);  //Açıklaması "GörselKüçültmekNot.java - Kod Zamanı 2 - 1'de.""
 Bunu
    12. smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
Small ımageye artık compress ediceğim onu byteye çeviriceğim böylece kesinkez artık küçük bir görselim olucağına emin olmuş durumdayım ve görselimide veriye çevirdim
Burayı bir çok dokümantasyon bir çok kursta veya farklı internet kaynaklarında görmeyiz o yüzden uzadığını düsünebiliriz ama sqlite ile çalışırken boyutların kontrolünün bizim elimizde olması önemli bir mevzu o yüzden böyle bir aşama gördük
Şimdi artık bunları aldığımıza göre  sqliteye kaydetmeye hazırız
Sıradaki aşama " VerileriKaydetmekNot"

 */