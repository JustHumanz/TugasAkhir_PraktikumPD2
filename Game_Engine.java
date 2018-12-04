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
import java.sql.*;
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

    int selector2;
    String sql;
    ResultSet rs;
    int id_up;
    char dec;

    int isi = 1;

    void menu() {
        try {
            charaDBtoHash();
            System.out.println("--- Zombie Survival ---");
            System.out.println("1. New Game");
            System.out.println("2. Survivor List");
            System.out.println("3. Rules and Gameplay");
            System.out.println("0. Exit");
            System.out.println("Pilih : ");
            selector = input.nextInt();
            if (selector == 1) {
                charaDBtoHash();
                characterSelect(); //masuk ke chara. select lalu start game
            } else if (selector == 2) {
                survivorDB();
            } else if (selector == 3) {
                rules(); //masuk ke fungsi rules yang isinya aturan + cara kerja game
            } else if (selector == 0) {
                System.exit(0);
            } else {
                System.out.println("Masukkan anda salah, silahkan isi sesuai pilihan yang ada!");
                System.out.println("--------------------");
                charaDBtoHash();
                menu();
                //untuk eksepsi apabila angka yang diinput tidak ada pada if
            }
        } catch (InputMismatchException e) {
            System.out.println("Format isian anda salah, pilih menu sesuai angka yang ada!");
            System.out.println("----------------------------------");
            charaDBtoHash();
            menu();
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

    void charaDBtoHash() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Scanner input2 = new Scanner(System.in);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/zombie", "root", "461999");
            Statement stmt = conn.createStatement();
            sql = "SELECT name FROM survivor order by id";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (isi < 5) {
                    String name = rs.getString("name");
                    charaHash.put(isi, name);
                    isi = isi + 1;
                }
                //charaHash menyimpan angka sebagai index dari LinkedList nanti
            }
            isi = 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("terserah");
        }
    } //func. untuk simpan index player dan nama

    void characterSelect() {
        try {
            charaDBtoHash();
            System.out.println("");
            System.out.println("=============================");
            System.out.println("--- Select Your Character ---");
            System.out.println("=============================");
            for (int tampil = 1; tampil <= 4; tampil++) {
                Object dll = charaHash.get(tampil);
                System.out.println(" |" + tampil + "| " + dll);
            }
            System.out.println("5. Custom Character");
            System.out.print("Pilih : ");
            selector = input.nextInt();
            current = selector;
            if (selector == 5) {
                changeNameMenu();
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
        System.out.println("==========================");
        System.out.println("--- Rules and Gameplay ---");
        System.out.println("==========================");
        System.out.println("1. Dalam game ini, anda akan bermain sebagai salah satu survivor");
        System.out.println("2. Satu round akan berisi 1 pemain dan 3 AI, beserta 1 AI Zombie");
        System.out.println("3. Tugas anda adalah bertahan hingga anda menjadi survivor terakhir");
        System.out.println("4. Setiap character memiliki 3 HP");
        System.out.println("4. Tiap giliran anda dapat mengacak angka antara 1 - 5 sebanyak 3 kali");
        System.out.println("5. Usahakan untuk mendapat angka tertinggi 5, agar terus berada di depan");
        System.out.println("6. Pemain dengan angka terkecil akan dipindah ke paling belakang");
        System.out.println("7. Pemain yang terletak paling belakang akan secara otomatis kehilangan 1 nyawa");
        System.out.println("8. Game berakhir apabila karakter yang dipilih pemain berhasil bertahan ataupun kehabisan HP");
        System.out.println("=====================================================================");
        menu();
    } // berisi peraturan permainan

    void survivorDB() {
        try {
            Scanner input2 = new Scanner(System.in);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/zombie", "root", "461999");
            Statement stmt = conn.createStatement();
            sql = "SELECT name, score FROM survivorScore order by score";
            rs = stmt.executeQuery(sql);
            System.out.println("--- Survivor History ---");
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println(" |" + name + "| " + score);
                System.out.println("-----------------------");
            }
            menu();
            System.out.println("");
        } catch (Exception e4) {
            System.out.println(e4.getMessage());
        }
    }

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

    void confirmLoop() {
        if (ubah == 'Y' || ubah == 'y') {
            randomNumber();
        } else if (ubah == 'n' || ubah == 'N') {
            //sort();
        } else {
            System.out.println("Masukkan salah, silahkan ");

        }
    }

    void changeNameMenu() {
        try {
            Scanner input2 = new Scanner(System.in);
            System.out.println("");
            System.out.println("------------------------");
            System.out.println("--- Custom Character ---");
            System.out.println("1. Change Name");
            System.out.println("2. View Character List");
            System.out.println("0. Exit");
            System.out.println("Input : ");
            selector2 = input2.nextInt();

            if (selector2 == 1) {
                changeNameEngine();
            } else if (selector2 == 2) {
                viewNameEngine();
            } else if (selector2 == 0) {
                charaDBtoHash();
                menu();
            }

        } catch (Exception e) {
            System.out.println("Input anda salah, mohon coba kembali!");
        }

    }

    void changeNameEngine() {
        try {
            Scanner input2 = new Scanner(System.in);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/zombie", "root", "461999");
            Statement stmt = conn.createStatement();
            sql = "SELECT id, name FROM survivor order by id";
            rs = stmt.executeQuery(sql);
            System.out.println("--- Character List ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(" |" + id + "| " + name);
                System.out.println("-----------------------");
            }
            System.out.print("Masukkan ID dari character yang akan diubah namanya :");
            id_up = input2.nextInt();
            System.out.print("Nama baru : ");
            String sisa = input2.nextLine();
            String new_name = input2.nextLine();
            sql = "UPDATE survivor set name='" + new_name + "' WHERE id=" + id_up;
            stmt.executeUpdate(sql);
            System.out.println("Nama karakter berhasil diganti!");
            charaDBtoHash();
            System.out.println("Ubah nama lain? Y / N");
            dec = input2.next().charAt(0);
            if (dec == 'Y' || dec == 'y') {
                charaDBtoHash();
                changeNameEngine();
            } else if (dec == 'N' || dec == 'n') {
                charaDBtoHash();
                changeNameMenu();
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }

    }

    void viewNameEngine() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/zombie", "root", "461999");
            Statement stmt = conn.createStatement();
            System.out.println("-----------------------");
            System.out.println("--- Character List ---");
            sql = "SELECT id, name FROM survivor order by id";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(" |" + id + "| " + name);

            }
            System.out.println("-----------------------");
            charaDBtoHash();
            changeNameMenu();

        } catch (Exception e5) {
            System.out.println(e5.getMessage());

        }
    }
}
