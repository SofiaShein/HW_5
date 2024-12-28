package jpa1;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApartmentDAO dao = new ApartmentDAO();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter price:");
            int price = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter '>' or '<':");
            String operator = sc.nextLine();

            System.out.println("Enter number of rooms:");
            int rooms = sc.nextInt();

            List<Apartment> apartments = dao.findByPriceAndRooms(price, operator, rooms);
            apartments.forEach(System.out::println);
        } finally {
            dao.close();
        }
    }
}
