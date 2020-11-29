/*
Öncelikle şimdi ne istiyoruz kullanıcı resim adlarına tıklayınca diğer aktiviteye geçsin istiyoruz bir kere o yüzden
MainActivity.java içerisindeki
"arrayAdapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1,nameArray);     //Açıklaması "VerileriçekmekNot.java - While Döngüsü İçerik Doldurma Zamanı 1 - 3'de"
        listView.setAdapter(arrayAdapter);"
'dan sonrasına hemen bir listView'imize tıklanma listener'i koymalıyız
        Kod Zamanı 1 )
        1) listView.setOnItemClickListener (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view , int position, long id) {

        }
        });"

1.Kodun açıklaması: daha önce görmüştük "ListView.setOnItemClickListener'i seçiyorum" onClickListener seçeneği değil dikkat edelim ve tıkladıktan sonra () içerisine newOn yazdığımızda zaten listenerimizi ands üzerinde bize sunuluyor.
Şimdi bu methodda bize ne veriyordu nereye tıklandığını "int position" bunun adını arada değiştiriyorlar index olabilir i olabilir p olabilir biz burda nereye tıklandığını burdaki int değerinden anlıyacağız
Peki tıklanınca ne olsun istiyorurz birkere intent yapmak istiyoruz herşeyden önce o yüzden onItemClick methodunun içerisine

    2) Intent intent = new  Intent (MainActivity.this,Main2Activity.class);
2.Kodun açıklaması : İntentimiz nereye olucak İntentimiz main'Activitiy.class'a olcak. Ve o yüzden şunuda yazalım. Hemen altına
    3)startActivity(intent);
Ama aktivite çalıştırmadan tabiki diğer tarafa bazı bilgiler yolluyacağız 3 kodumuzu bir tık alta alalım hemen üstüne.
Esas yollucağımız ilk bilgi
    4) intent.putExtra("artId", )
4.Kodun açıklaması : Esas yollucağımız ilkBilgi artId şeklinde yazıyorum artİd'yi nerden alabiliriz idArray'imiz var dolayısıyla idArray ve içerisinde neyimiz var .get() diyip indexinide burdan alabiliriz
"int position"dan
    4) intent.putExtra("artId",idArray.get(position));
Ve böylece kullanıcı nereye tıkladıysa o index buraya verilicek idArray içerisinde buna denk gelen position alınıcak ve diğer aktivitede hangi id'ye tıkladığımız belli olucak ama birşey daha yollamamız gerekiyor oda ne
Şimdi emumuzda kullanıcı isme tıklandığındada aktivite 2 ye gidiceğiz ... tıkladığındada aktivite 2 ye gidiceğiz bunu ayırt edebilmemiz lazım şunu düşünebiliriz.
Ne uğraşıyoruz 3. bir aktivite açalım isme tıklayınca 3.aktiviteye git aynı tasarımı birdaha yap felan öylede olur ama aynı 2 aktivite ile yapmak varken 3 aktiviteyi eklemeye gerek yok sadece ...'danmı tıklandı yoksa isimdenmi tıklandı bunu ayırt edebilceğimiz bir method bulsak yeterli
Onuda şöyle yapıcağım 4 .kodumuzun hemen altına.
    5)  intent.putextra("info","old");
5.Kodun açıklaması : Yine herhangi bir extra yolluyacağım mesela "info" diyorum adına ve örneğin burdan tıklanırsa yani listView içerinde tıklanırsa old. diyorum yani eski anlamında eski sanatı açmaya çalışıyor
Örneğin diğer taraftan menüden tıklanırsa menu methodumuzu bulalım ve yine bir putExtra yapalım.
MainAvtivity.java - "Intent intent = new Intent (MainActivity.this,MainActivity2.class); //Açıklaması : MenuEklemekNot Kod Zamanı 2 / 4'de." hemen altına.
StartActivity'den önce.
    6)intent.putExtra("info","new");
6.Kodun açıklaması : Yine bir putExtra yaptım ve bu sefer aynı infoyu new olarak yolluyim yani yeni bir sanat ekliyicek adam onu söylemeye çalışıyorum  ve böylece ben diğer tarafa gidersem.
Main2Activity'e gidersem
OnCreate içerisinde yollanılan intent'i almam gerekicek. Dolasıyla Main2Activity.java'ya giriyorum ve
    " saveButton = findViewById(R.id.saveButton);" altına
    7) Intet intent  = getIntent();
7.Kodun açıklaması bu şekilde yollanılan intenti alabiliyorduk
Peki hemen altında infoyu nasıl alabilirim
    8) String info = intent.getStringExtra("info");
8.Kodun açıklaması - Bir String info diye değişken oluşturdum ve intent.getStringExtra("info") diyebilirim.
Ve böylece infoda new veya old olucak değilmi . Peki bunu şöyle kontrol edebilirim
    Hemen altına .
    9) if (info.matches("new")) { }
9.Kodun açıklaması - info.matches'i kullanıcağım ("") koyup ("new") new ise demekki adam yeni birşey eklemeye çalışıyor diyiceğim.
Ve hemen sonrasına "else {} koyuyorum "
    10) else {}
10.Kodun açıklaması - Else değil ise demekki adam eski birşeyi göstermeye çalışıyor hatta eski gösterdiği şey içerisinde bana bir id'de yollamış olucak
mainActivity.java içerisinde yolladığımız id "intent.putExtra("artId",idArray.get(position));     //Açıklaması "VeriAktarımıNot.java - Kod Zamanı 1 - 4'de""
Yolladığı id  : idArray.get(position) id bir integer o yüzden else içerisinde :
    11) int artId = intent.getIntExtra("artId",1);
11.Kodun açıklaması artId dedim adına  ve else içerisinde şu şekilde alabiliriz mesela intent.getIntExtra("") int extram ne "artId" sonrasında default bir değer sorucak 0 yada bir olsun farketmez.
Eğer bir şekilde yanlış bir işlem olursa ilk resmi gösteririz. artId birebir bu şekildemi yazmışız göz geçirelim ama ve hatta new ile gelirse yeni resmi yapmak için gerekli olan işlemlerden emin olalım
ne gibi mesela EditTextlerimiz varya artNameText, painterNameText, yearText edit textlerimizi boş hale getirelim eskilerden birşey kalmasın mesela if new methodunda. yazıcağım.
    12) artNameText.setText("");
    13) painterNameText.setText("");
    14) yearText.setText("");
 Bu şekilde bunlara boşluk dedim hatta elsede eğer eski birşey gösteriyorsa buttonu gizliyelim ne gerek var buttonu görmesinler save etmicekler nasılsa eski birşeye bakıyorlar
Nasıl gizliyorduk. Else methodu içerisine yazıcağım.
    14) button.setVisibility(View.INVISIBLE);
Ve aynısını if methodunda visible yapalım böylece adam geldiği yere butonu göremesin dahada iyi olur bizim için
    15) button.setVisibility(View.VISIBLE);
ifde görsün elsede göremesin Son olarak burda birde bitmap'i değiştirelim eğer yeni şey yapıcaksa gerçekten selectedBitmap olduğundan emin olalım if içerisine. yine
    16) Bitmap selectImage = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.selectimage);
 16.Kodun açıklaması : SelectImage diyorum adına ve bunun için "BitmapFactory" i kullanıyorduk BitmapFacroty.decodeResource() diyerek drawble içerisindeki şeyleri decode edebiliyorduk bunun için resourceyi kullanmıştık.
 "(getApplicationContext().getResources()) R.a resources'e ulaşabiliyorduk getResources(),R.drawble.selectimage); ve bunun içerisindede r.drawble.selectimageyi seçebiliyorduk ve böylece
    17) imageView.setImageBitmap(selectImage); diyebiliriz.
Şimdi elsede ise daha başka bir ayar yapmaya gerek yok bundan sonrası zaten sqliteden gelicek
Bir deniyelim bakalım şunu görücekmiyiz : Monalisaya tıkladım button gözükmedi demekki burdan geldiğimi anladı add art'a tıkladığımda button gözüküyor
Bundan sonra geriye ne kaldı burdan aldığım artId yi kullanarak sqliteden verileri çekmek tek işlemimiz bu kaldı onuda hemen bir sonraki aşamada yapalım.
Sıradaki aşama "SeçimArgümanlarıNot.java"

 */