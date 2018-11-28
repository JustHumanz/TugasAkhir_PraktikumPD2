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

//    int hp;
    int index;
    int comp;

    public Character(int i, int c) {
        index = i;
//        hp = h;
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
                System.out.println("Masuk ke db yang isinya list urutan game terakhir");
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

    void characterStat() {
        LinkedList<Character> charaStat = new LinkedList<Character>();
        //charaStat akan menyimpan status hp dari masing - masing karakter
        charaStat.add(new Character(1, 0));
        charaStat.add(new Character(2, 0));
        charaStat.add(new Character(3, 0));
        charaStat.add(new Character(4, 0));

    } //func. untuk inisialisasi status

    void charaHash() {
        Hashtable charaHash = new Hashtable();
        charaHash.put(1, "Ferguso");
        charaHash.put(2, "Fernando");
        charaHash.put(3, "Alonso");
        charaHash.put(4, "Mamank");
        //charaHash menyimpan angka sebagai index dari LinkedList nanti

    } //func. untuk simpan index player dan nama

    void characterSelect() {
        Game_Engine run = new Game_Engine();
        try {
            Hashtable charaHash = new Hashtable();
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
            System.out.println("Anda akan bermain sebagai " + charaHash.get(selector) + " !");
            //pada saat index Hashtable dipanggil, maka index ini juga dapat menampilkan nama pemain
            run.randomNumber();
//            run.firstQueue();

        } catch (InputMismatchException e2) {
            System.out.println("Pilihan anda tidak ada di daftar atau format salah. Silahkan pilih kembali!");
        }
    } // func. untuk pilih karakter

    void firstQueue() {
        int roll;
        double min = 1;
        double max = 5;
        boolean play = true;
        LinkedList<Character> charaStat = new LinkedList<Character>();
        Hashtable charaHash = new Hashtable();
        System.out.println("Masukkan 1 untuk acak angka");
        roll = input.nextInt();
        if (roll == 1) {
            int temp = (int) ((int) (Math.random() * ((max - min) + 1)) + min);
            System.out.println(charaStat.get(selector).comp);
            charaStat.get(selector).comp = temp;
            System.out.println(temp);
            System.out.println(charaStat.get(selector).comp);
            System.out.println("");
            System.out.println("Angka " + charaHash.get(selector) + "pada round ini adalah = " + temp);
            System.out.println("--------------------------------------");
            System.out.println("");
        } else {
            System.out.println("Berhenti mengulang");
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
        boolean play = true;
        while (play) {
            System.out.println("Masukkan 1 untuk acak angka");
            roll = input.nextInt();
            if (roll == 1) {
                int temp;
                temp = (int) (Math.random() * ((max - min) + 1)) + min;
                System.out.println("Angka anda round ini adalah = " + temp);
                System.out.println("--------------------------------------");
                System.out.println("");
            } else {
                System.out.println("Berhenti mengulang");
            }
        }
    } // func. acak angka

    void fight() {
        boolean loop = false;
        while (loop) {

        }
    } // func. swap dan sort serta kurangi hp

}
