package org.example;
//Задание1-2
class Sotrudnik {

    private String fio;
    private String dolzhnost;
    private String email;
    private String telefon;
    private String zarplata;
    private String age;

    public Sotrudnik(String fio, String dolzhnost, String email, String telefon, String zarplata, String age) {
        this.fio = fio;
        this.dolzhnost = dolzhnost;
        this.email = email;
        this.telefon = telefon;
        this.zarplata = zarplata;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("ФИО: " + fio);
        System.out.println("Должность: " + dolzhnost);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + telefon);
        System.out.println("Зарплата: " + zarplata);
        System.out.println("Возраст: " + age);
    }

    @Override
    public String toString() {
        return "Sotrudnik{" +
                "ФИО='" + fio + '\'' +
                ", Должность='" + dolzhnost + '\'' +
                ", Email='" + email + '\'' +
                ", Телефон='" + telefon + '\'' +
                ", Зарплата='" + zarplata + '\'' +
                ", Возраст='" + age + '\'' +
                '}';
    }
}

public class Main {
    private static Sotrudnik sotrudnik;

    public static void main(String[] args) {

        Sotrudnik[] persArray = new Sotrudnik[5];

        persArray[0] = new Sotrudnik("Ivanov Ivan", "Engineer", "ivivan@mail.ru", "89024567834", "50000", "35");
        persArray[1] = new Sotrudnik("Petrov Petr", "Manager", "petrov@mail.ru", "89024567835", "60000", "40");
        persArray[2] = new Sotrudnik("Sidorova Anna", "Designer", "sidorova@mail.ru", "89024567836", "45000", "30");
        persArray[3] = new Sotrudnik("Kuznetsov Sergey", "QA", "kuznetsov@mail.ru", "89024567837", "55000", "28");
        persArray[4] = new Sotrudnik("Smirnova Maria", "DevOps", "smirnova@mail.ru", "89024567838", "70000", "32");

        for (Sotrudnik person : persArray) {
            System.out.println(person);
        }
        sotrudnik.displayInfo();
    }
}

