package homeWork5;

import homeWork5.service.EntityManager;
import homeWork5.service.Logging;

import java.util.Scanner;

/**
 * Created by marva on 10.03.16.
 */
public class Main {
    public static void main(String[] args) {
        EntityManager library = new EntityManager();
        boolean exit =false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("Valige valik:");
            System.out.println("1.Autori otsing nime järgi\n 2.Autori sisestus \n 3.Raamatu otsing nime järgi \n 4. Raamatu sisestus\n 5.Autorite väljastus \n 6.Raamatute väljastus \n 7.exit \n");
            int choose = scanner.nextInt();
            scanner.nextLine();
            String input;

            switch (choose) {
                case 1:
                    System.out.println("Sisesta autori nimi(osaline) keda tahad otsida");
                    input = scanner.nextLine();
                    library.searchAuthorsByName(input);
                    break;
                case 2:
                    System.out.println("Sisesta autori nimi keda tahad luua");
                    input = scanner.nextLine();
                    Logging.log("sisestati autor "+input);
                    library.newAuthor(input);
                    break;
                case 3:
                    System.out.println("Sisesta raamatu nimi mida tahad otsida");
                    input = scanner.nextLine();
                    Logging.log("sisestati raamat "+input);
                    library.searchBooksByName(input);
                    break;
                case 4:
                    String author, book;
                    System.out.println("Sisesta raamatu nimi mida tahad sisestada");
                    author = scanner.nextLine();
                    System.out.println("Sisesta raamatule ka autor");
                    book = scanner.nextLine();
                    library.newBook(book,library.getAuthor(author));
                    break;
                case 5:
                    library.printAuthors();
                    break;
                case 6:
                    library.printBooks();
                    break;
                case 7:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        library.killConnection();
    }
}
