import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book theLostManBook = new Book(sc.nextLine());
        theLostManBook.countPopularWords(2);
        theLostManBook.countUniqueWords();
        theLostManBook.printStatistics();

        Book watershipDownBook = new Book(sc.nextLine());
        watershipDownBook.countPopularWords(2);
        watershipDownBook.countUniqueWords();
        watershipDownBook.printStatistics();
    }
}