/*
Bu aşamada şimdi sorunumuz ne işimiz bitince saveden sonra yani savede fnish ile kapatıyoruz "finish();" kısmı mainActivity2.javada.
Ama fnish ile kapattığımız için mainActivity'de onCreate cağrılmıyor onCreate cağrılmayıncada devam ettiği için onResume cağrılıyordu e on resume içerisindede o işlemleri yapmadığımız için
Aslında sorunlar yaşıyoruz da bunun bir çok çözümü var mesela:
Main2Activty içerisinde finish yapmak yerine direk intent yapabilirim dolasıyla finish'i bir yorum haline getirdim.
İntent yaparsam intent yapınca onCreate cağrılıcaktır  mesela deniyelim.
    Kod zamanı 1 :
    1) Intent intent = new Intent (Main2Activity.this,MainActivity.class);
1.Kod açıklaması - Main2activity.this diyorum burda olduğumu belirtiyorum ve mainActivity.class'a gidiceğim diyorum mesela ve.
    2) startActivity(intent);
Diyorum bir görsel ekledikten sonra intent ile diğer tarafa geçmeyi deniyelim.
Bu çalışıcak bu arada ama başka sorunlar getirir beraberinde
Çalıştıralım bakalım ne olucak :  misal mona lisa var şuan gayet düzgün çalışıyor ... eklersem açıyor bir görsel daha ekliyebiliriz save ediyor eklediğim resmi gösterir vs
aslında çalışıyor gibi ama burayı gelip intent ile açtığımız ve diğer tarafı bitirmediğimiz için resme tıklayıp geri döndüğümde bir önceki aktiviteye geri dönüyor sonuç olarak.
Hem arkada bir sürü aktivite bırakıyor hemde geri dönmek riskli aslında 2 tane resim var ama 1 tane gözüküyor felan filan şimdi bu bize bir sorun oluşturuyor tabiki.
Nasıl çözeriz : Misal şöyle birşey olsa hoş olurdu intent yaparken daha önce açık olan bütün aktiviteleri kapat diyebilisek güzel olurdu yoluda var
Oda şu  startactivity(intent); 'den öncesine yazıcağım.
    3) intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
3.Kodun açıklaması  : Bir flag ekliyerek intenti başlatıcağım bir bayrak ekliyerek başlatıcağım (içerisinde) Intent. dersek eklenicek bayrakları görürüz.Bizim aradağımız intenti çıkarma şekli
"FLAG_ACTIVITY_CLEAR_TOP" bunu arıyorum bu daha önceki bütün aktiviteleri kapatmamıza olanak sağlıcak.
Eklediklerim gözüküyor tıkladıklarım çalışıyor ve burda geri basarsam uygulama kapanıcak sanki daha önce hiç bir aktivte yokmuş gibi
ve yok gerçektende istediğim gibi çalışıyor
Tabiki bu app istek üzerine geliştirilebilir hem bir çok şeyi yeni öğrendik.
görsel nasıl alınır izin nasıl alınır bunları zaten çok fazla pratik ediceğiz
Bir sonraki projede görüşmek üzere.

 */