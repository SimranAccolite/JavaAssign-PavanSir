import java.util.ArrayList;
import java.util.Scanner;

class Reservation
{
    int[] seats = {0,0,0,0,0,0,0,0,0,0};
    synchronized public void reserve() {
        Scanner sc = new Scanner(System.in);
        StringBuilder availableSeats = new StringBuilder("");
        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (seats[i] == 0) {
                availableSeats.append(i + 1 + " ").toString();
                count++;
            }
        }
            if (count == 0)
                System.out.println("Sorry!! No seats are available for reservation");
            else {
                System.out.println("Total seats available are: " + count);
                System.out.println("Seat numbers are: " + availableSeats);
            }
            System.out.println("Enter the seats you want to book:");
            System.out.println("Enter the seats sepearted by space, Example: x y z ");
            String required = sc.nextLine();
            String[] reqSeats = required.split(" ");
            if (reqSeats.length > count)
                System.out.println("Sorry you requested for more seats than available");
            else {
                boolean flag = false;
                for (String s : reqSeats) {
                    int seatNo = Integer.parseInt(s);
                    if(seatNo <= 0 || seatNo > 10){
                        System.out.println("Wrong Seat Number");
                        flag = true;
                        break;
                    }
                    if (seats[seatNo -1] == 1)
                    {
                        System.out.println("Sorry!! Seat no." + seatNo + " Already booked");
                        flag = true;
                        break;
                    }
                    else{
                        seats[seatNo-1] = 1;
                    }
                }
                if(flag){
                    System.out.println("We can't book any seats for you");
                    System.out.println();
                }
                else
                    System.out.println("Congratulations! your seats have been booked successfully");
                System.out.println();
            }

        }
    }

class Person extends Thread{
    Reservation r1;
    Person(Reservation r1)
    {
        this.r1 = r1;
    }

    @Override
    public void run() {
        r1.reserve();
    }
}

public class SeatReservation {
    public static void main(String[] args) {
        Reservation reservation =new Reservation();
        ArrayList<Person> person = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person(reservation);
            person.add(p);
        }
        for(Person p : person) {
            p.start();
        }
    }
}
