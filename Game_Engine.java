/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Responsi2;

/**
 *
 * @author Asus
 */
import java.util.*;

class Character implements Comparable<Character> { //class untuk membuat object character

    int index;
    int comp;

    public Character(int i, int c) {
        index = i;
        comp = c;

    }

    @Override
    public int compareTo(Character o) {
        int comparedSize = o.comp;
        if (this.comp > comparedSize) {
            return 1;
        } else if (this.comp == comparedSize) {
            return 0;
        } else {
            return -1;
        }
    }

    public int toInt() {
        return index;
    }

}

public class Game_Engine {

    int current;
    int selector;
    Scanner input = new Scanner(System.in);
    Hashtable charaHash = new Hashtable();
    int loopChance = 3;
    LinkedList<Character> charaStat = new LinkedList<Character>();
    char ubah;
    int temp;

    void menu() {
        Game_Engine run = new Game_Engine();
        try {

            System.out.println("--- Zombie Survival ---");
            System.out.println("1. New Game");
            System.out.println("2. Survivor List");
            System.out.println("3. Rules and Gameplay");
            System.out.println("4. Exit");
            System.out.print("Pilih : ");
            selector = input.nextInt();
            if (selector == 1) {
                run.characterSelect(); //masuk ke chara. select lalu start game
            } else if (selector == 2) {
                //run.survivorDB();
                System.out.println("Masuk ke db yang isinya karakter yang digunakan dan jumlah turn karakter yang terakhir");
            } else if (selector == 3) {
                run.rules(); //masuk ke fungsi rules yang isinya aturan + cara kerja game
            } else if (selector == 4) {
                System.exit(0);
            } else {
                System.out.println("Masukkan anda salah, silahkan isi sesuai pilihan yang ada!");
                System.out.println("--------------------");
                run.menu();
                //untuk eksepsi apabila angka yang diinput tidak ada pada if
            }
        } catch (InputMismatchException e) {
            System.out.println("Format isian anda salah, pilih menu sesuai angka yang ada!");
            System.out.println("----------------------------------");
            run.menu();
            //untuk eksepsi apabila yang diinput bukan angka
        }
    } // menu utama

    void characterStart() {
        //charaStat akan menyimpan status hp dari masing - masing karakter
        charaStat.add(new Character(1, 0));
        charaStat.add(new Character(2, 0));
        charaStat.add(new Character(3, 0));
        charaStat.add(new Character(4, 0));

    } //func. untuk inisialisasi status
//
//    void characterSet() {
//        charaStat.set(current, temp);
//    }

    void charaHash() {
        charaHash.put(1, "Ferguso");
        charaHash.put(2, "Fernando");
        charaHash.put(3, "Alonso");
        charaHash.put(4, "Mamank");
        //charaHash menyimpan angka sebagai index dari LinkedList nanti

    } //func. untuk simpan index player dan nama

    void characterSelect() {
        Game_Engine run = new Game_Engine();
        charaHash();
        try {
            System.out.println("");
            System.out.println("=============================");
            System.out.println("--- Select Your Character ---");
            System.out.println("=============================");
            System.out.println("1. Ferguso");
            System.out.println("2. Fernando");
            System.out.println("3. Alonso");
            System.out.println("4. Mamank");
            System.out.println("5. Custom Character");
            System.out.print("Pilih : ");
            selector = input.nextInt();
            System.out.println("Selector skrg " + selector);
            current = selector;
            System.out.println("Current skrg " + current);
            if (selector == 5) {
                System.out.println("Masuk ke db untuk edit nama karakter");
            } else {
                System.out.println("Anda akan bermain sebagai " + charaHash.get(selector) + " !");
                randomNumber();
                //pada saat index Hashtable dipanggil, maka index ini juga dapat menampilkan nama pemain
            }

//            run.firstQueue();
        } catch (InputMismatchException e2) {
            System.out.println("Pilihan anda tidak ada di daftar atau format salah. Silahkan pilih kembali!");
        }
    } // func. untuk pilih karakter

    void firstQueue() {
        charaStat.clear();
        int max = 5;
        int min = 1;
        for (int awal = 1; awal <= 4; awal++) {
            int randomize = temp = (int) (Math.random() * ((max - min) + 1)) + min;
            charaStat.add(new Character(awal, temp));
        }

        //method untuk penempatan linkedlist awal saat game dimulai
    } // func. untuk membuat list pertama

    void rules() {
        Game_Engine run = new Game_Engine();
        System.out.println("==========================");
        System.out.println("--- Rules and Gameplay ---");
        System.out.println("==========================");
        System.out.println("1. Dalam game ini, anda akan bermain sebagai salah satu survivor");
        System.out.println("2. Satu round akan berisi 1 pemain dan 3 AI, beserta 1 AI Zombie");
        System.out.println("3. Tugas anda adalah bertahan hingga anda menjadi survivor terakhir");
        System.out.println("4. Setiap character memiliki 3 HP");
        System.out.println("4. Tiap giliran anda akan mengacak angka antara 1 - 5");
        System.out.println("5. Usahakan untuk mendapat angka tertinggi 5, agar terus berada di depan");
        System.out.println("6. Pemain dengan angka terkecil akan dipindah ke paling belakang");
        System.out.println("7. Apabila ada 2 atau lebih pemain mendapat angka yang sama, maka angka akan diacak kembali");
        System.out.println("8. Pemain yang terletak paling belakang akan secara otomatis kehilangan 1 nyawa");
        System.out.println("9. Game berakhir apabila 1 pemain berhasil bertahan di paling depan ataupun kehabisan HP");
        System.out.println("=====================================================================");
        run.menu();
    } // berisi peraturan permainan

    void startGame() {
        Game_Engine run = new Game_Engine();
        run.characterSelect();
    } // memulai game

    void survivorDB() {

    } // db untuk riwayat permainan

    void randomNumber() {
        int roll;
        int min = 1;
        int max = 5;
        System.out.println("==============");
        try {
            do {
                if (loopChance != 0) {
                    System.out.println("--------------------------------------");
                    System.out.println("Anda punya " + loopChance + " kesempatan acak angka!");
                    charaStat.add(new Character(current, temp)); //menambahkan list
                    System.out.println("Isi temp sebelum random " + temp);
                    System.out.println("Isi comp sebelum random " + charaStat.element().comp);
                    temp = (int) (Math.random() * ((max - min) + 1)) + min;
                    charaStat.element().comp = temp;
                    System.out.println("Nilai temp skrg " + temp);
                    System.out.println("Nilai comp skrg " + charaStat.element().comp);
                    System.out.println("Angka " + charaHash.get(current) + " round ini adalah = " + temp);
                    System.out.println("--------------------------------------");
                    loopChance -= 1;
                    System.out.println("Acak lagi? Y/N");
                    //String sisa = input.nextLine();
                    ubah = input.next().charAt(0);
                    confirmLoop();
                } else if (loopChance == 0) {
                    System.out.println("Kesempatan acak ronde ini habis!");
                    temp = (int) (Math.random() * ((max - min) + 1)) + min;
                    System.out.println("Angka " + charaHash.get(current) + " round ini adalah = " + temp);
                    System.out.println("--------------------------------------");

                    //sort();
                }
            } while (loopChance != 0);
        } catch (InputMismatchException e3) {
            System.out.println("Berhenti mengulang");

        }
    }
    
    void sort() {
        Game_Engine run = new Game_Engine();
        HashMap<Integer, Object> charaSort = new HashMap<Integer, Object>();
         charaSort.put(temp, charaHash.get(current));
         for (int g=0;g<=5 ;g++ ) {
           if (g==selector) {
               continue;
           }
           charaSort.put(temp, charaHash.get(current));
           Random random = new Random();
           int x = random.nextInt(5) + 1;
           charaSort.put(x, charaHash.get(g));
         }

         Map<Integer, Object> map = new TreeMap<Integer, Object>(charaSort);
         System.out.println("After Sorting:");
         Set set2 = map.entrySet();
         Iterator iterator2 = set2.iterator();
         while(iterator2.hasNext()) {
         Map.Entry me2 = (Map.Entry)iterator2.next();
         System.out.print(me2.getKey() + ": ");
         System.out.println(me2.getValue());
          }
    
    
    void confirmLoop() {
        if (ubah == 'Y' || ubah == 'y') {
            randomNumber();
        } else if (ubah == 'n' || ubah == 'N') {
            //sort();
        } else {
            System.out.println("Masukkan salah, silahkan ");

        }
    }
}
