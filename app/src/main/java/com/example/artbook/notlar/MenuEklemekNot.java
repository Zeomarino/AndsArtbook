/*
Daha öncede belirttiğim üzere mainActivity içerisinde menü ekleme işlemini yapıcağız bu sayede istediğimiz aktivitelere vs bir menü ile gidebiliceğiz.
                                                Daha önce bunu yapmadık nasıl yapabiliriz ?
Plan Bir : Öncelikle menümüzü tasarlayabilceğimiz bir layout dosyası gerekiyor.
Açıklama Bir : Layout dosyasını aslında res'in altında tasarlıcağız aslında bildiğimiz xml dosyası oluşturcağız ama bunu menü üzerinde yapmamız lazım
Yapım Bir : Bunu yapabilmek için res'e sağ tıklıyoruz New/Directory Seçiyoruz yani klasör klasörün içerisinde "menu" yazıyoruz.
menu diye klasör cıkıyor solda . Varsayalım olduda çıkmazsa : resi yine senkronize edebiliriz sağ tıklayıp veya File/Invalide Caches /Restart diyebiliriz
Peki neden bu önemli : menu klasörüne gelip sağ tıklayıp new dersek yeni dediğimizde yani Menu resource file seçeneği cıkıcak karşımıza Aslında dediğim gibi bu xmlden farklı değil ama direk menu için oluşturuyor bunu daha kolay oluyor işimiz.
İşlemlerimizi yaptığımızda bir isim seçmemiz gerekiyor çıkan pencerede örneğin "add_art" gibi sanat ekle menüsü yapıyoruz okey'e basalım daha sonra.
Menüleri eklemek text kısmında daha kolay yani xml' kısmından sağdaki preview açık değilse bakabiliriz xml kullanırken görülceği üzere ekranımızda tıklanabilcek birşey yok o yüzden bir item yapmamız gerekiyor.
Nasıl yapılıyor
    //Add_art.xml kod zamanı 1.
    1) <item
"<item" yazdığım gibi zaten sağ tarafta küçük bir menü beliriyor dikkatli bakarsanız buna ikitane şey vermemiz lazım boşluğa basarsak cıkar zaten Bir : Tittle İki id "tittle dediğimiz" bildiğimiz ne yazıcak içerisinde "id dediğimizde" bunun bildigiğimiz idsi hani nasıl biz textView'e imageView'e idler veriyoruz aynı şekilde bunada id veriyoruzki sonradan kullanıcı nereye tıkladı anlıyabilelim o yüzden önce.
    1) <item android:id"@id/add_art_item"
anroid:id= çift tıkladığımızda "@id/" diye birşey cıkıcak bu şekilde başlaması gerekiyor herhalükarda mesela "add_art_item" dedik. Sonra.
    1) <item android:id"@id/add_art_item"  Bir kere daha boşluğa basarsak "android:title="Add Art" diyip > kapatabiliriz oda bize şöyle </item> itemi kapatıcaktır yani.
    1)<item android:id="@id/add_art_item" anroid:title="Add Art"></item" şeklinde olucaktır
 Ve gözükülceği üzere sağ pencerede Add Art çıktı burdaki tasarımla işimiz bitti.Peki burda işimiz bittimi hayır bitmedi çünkü : bu menüyü MainActivity içerisinde tanıtmamamız gerekiyor
 Ve bunu yapmak için ikitane method cağıracağız 2 tane daha doğrusu onCreate gibi düşünelim daha önceden hazır tanımlanmış methodları override ediceğiz üstüne yazıcağız.
 Bunun birincisi onCreate options menu
    //MainActivitiy Kod Zamanı 1
    1) oncreate yazarsak "oncreate dışındayız aktivitenin içerisindeyiz" "onCreateOptionsMenu diye" zaten bir seçenek çıkar.Çift tıklayabiliriz ve buda şöyle olur.
    "@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }" ikinciside.
    2)onoptions yazarsak aynı şekilde onOptionsItemSelected çıkıcak.  buda şu şekilde olucak.
    "@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }"
Menu methodlarımız bu şekilde getirebiliceğiz peki ne işe yarıyor bunlar.
Çok basit :
onCreateOptionsMenu'de "hangi menüyü göstericeğiz bu aktivitede onu belirliyoruz."
onOptionsItemSelected'de "kullanıcı herhangi bir item'i seçerse ne yapıcağımızı belirtiyoruz".
Şimdi : Bir xml yaptığımızda onu aktivite içerisinde gösterebilmek için "add_art.xml" den bahsediyorum "Inflater" dediğimiz birşey kullanıyoruz inflate etmek şişirmek gibi bunu sadece menülerde değil.
başka xml'lerdede görüceğiz.Konumuza dönüceksek app'imizde aslında sadece bir tane inflater var onuda alabilmek için şöyle bir kod yazmak gerekiyor.
    //MainActivity Kod Zamanı 2
    1) "onCreateOptionsMenu" içerisine."MenuIflater" diyoruz adına yine "menuInflater" diyebiliriz = koyduktan sonra "getMenuInflater()" bu şekilde. bu bize zaten birtane menuInflaterimiz var onu verebiliyor ve bunu kullanrak bu objeyi kullanarak menümüzü burdaki aktiviteye bağlıyabiliyoruz.
Şu şekilde olucak yani "MenuInflater menuInflater = getMenuInflater();" bu şekilde.Olucak ilk kod satırımız. İkincisi ise hemen altına
    2)menuInflater.inflate "dersek bize birtane menu resource soruyor biraz önce oluşturduğumuz birde menü soruyor oda zaten onCreateOptionsMenu(Menu menu) burda verilen menü. Yani.
    2) menuInflater.inflate(R.menu.add_art,menu) yazıcağım ve işimiz biticek bu şekilde burda daha önce oluşturuduğumuz menu dosyasını aktivitemize bağlıyabiliyoruz sonra geliyorum bu menüden herhangi birşey seçilirse ne yapıcağız.
 Yani "onOptionsItemSelected'e geliyoruz ve.
 öncelikle neyin seçildiğini kontrol etmem lazım şuanki örneğimde benim tek bir menüm var daha doğrusu tek bir item'im var menü içerisinde fakat bu üçtanede olabilirdi 4 tanede olabilirdi yani o yüzden kullanıcının neye tıkladığını anlamam lazımki ona göre işlem yapayim bunuda anlıyabilmek için.
 if kullanacağım bakın tıklanılan yer bana item olarak veriliyor zaten.
    3) if(item.getItemId() == ) diyiceğim oda "R.id.add_art_item)" e eşitse diyiceğim ve {} koyucağım yani. Şu şekilde.
    3) if(item.getItemId() == R.id.add_art_item) {} " bu ne demek " add_art_item'e tıkladıysa ne yapıcağız ne yapıcağız peki Intent yapıcağız. if { süslüsünün içerisine bitişinden öncesine.
    4)  Intent intent = new Intent () diyorum bizden bir ApplicationContext isticek daha doğrusu context isticek buna "MainActivity.this" diyebilirim birde hangi aktiviteye gidiyim dicek bunada "main2Ativitiy.class" diyebilirim yani. Şu şekilde olucak.
    4) Intent intent = new Intent (MainActivity.this,Main2Activity.class);
Bunu dedikten sonra  direk "startActivity(intent) diyip intentin adını verebilirim yani.
    5)startActivity(intent); olucak.
Bir deniyelim bakalım gerçekten diğer tarafa götürücekmi bu ve götürüyorsa bu diğer taraftaki tasarımımız düzgün çalışıyormu eğer bunlar olduysa hemen 2.aktivitedeki olaya girmemiz lazım.
... tıklıyorum Ve Add art'a basıyorum ve gözükülceği üzere menümüzde geliyor gayet düzgün bir şekilde gözüküyor.
Şimdi iş şuraya geldi select imageye bastıgımızda gallery'e nasıl gideriz bunu yapmamız gerekiyor çünkü kaydediceğimiz zaten bir image var bilgiler var bu bilgileri almak zaten kolay kullanıcı buraya yazıcak.
ama imageye tıklandıgında ne yapıcağımızı çözmemiz gerekiyor o yüzden burda duralım bir sonraki aşamada görsel nasıl seçilir biraz bundan bahsedelim.
Oda : "KullanıcıİzinleriNot" şeklinde anlatıcağım.


 */