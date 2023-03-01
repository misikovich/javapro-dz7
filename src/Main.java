import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book theLostManBook = new Book(sc.nextLine());
        System.out.println(theLostManBook.countPopularWords(2));
        System.out.println(theLostManBook.countUniqueWords());
        theLostManBook.printStatistics();

        Book watershipDownBook = new Book(sc.nextLine());
        System.out.println(watershipDownBook.countPopularWords(2));
        System.out.println(watershipDownBook.countUniqueWords());
        watershipDownBook.printStatistics();
    }
}