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

public class Game_Engine {

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
    }

    void characterSelect() {
        Game_Engine run = new Game_Engine();
        try {
            String decision;
            boolean repeat = true;
            Hashtable charaList = new Hashtable(); //charaList menyimpan angka sebagai index dari LinkedList nanti
            System.out.println("=============================");
            System.out.println("--- Select Your Character ---");
            System.out.println("=============================");
            System.out.println("1. Ferguso");
            charaList.put(1, "Ferguso");
            System.out.println("2. Sueb");
            charaList.put(2, "Sueb");
            System.out.println("3. Ferrentino");
            charaList.put(3, "Ferrentino");
            System.out.println("4. Mamank");
            charaList.put(4, "Mamank");
            System.out.print("Pilih : ");
            selector = input.nextInt();
            System.out.println("Anda akan bermain sebagai " + charaList.get(selector) + "!, Anda Yakin? Y/N"); //pada saat index Hashtable dipanggil, maka index ini juga dapat menampilkan nama pemain
            decision = input.nextLine();
            while (repeat == true) {
                if (decision.equals("Y") || decision.equals("y")) {
                    repeat = false;
                    System.out.println("Jalankan start game dan gacha untuk lokasi awal dengan fungsi firstQueue");
                } else if (decision.equals("N") || decision.equals("n")) {
                    repeat = false;
                    System.out.println("Silahkan pilih karakter kembali!");
                    run.characterSelect();
                } else {
                    repeat = true;
                }
            }
        } catch (InputMismatchException e2) {
            System.out.println("Pilihan anda tidak ada di daftar atau format salah. Silahkan pilih kembali!");
        }

    }

    void firstQueue() {
        //method untuk penempatan linkedlist awal saat game dimulai
    }

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
    }

    void startGame() {
        Game_Engine run = new Game_Engine();
        run.characterSelect();
    }

    void survivorDB() {

    }

}
