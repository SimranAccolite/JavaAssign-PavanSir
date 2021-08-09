import java.util.ArrayList;

class Bank {
    private long amount = 0;

    synchronized void deposit(long amount) {
        System.out.println("Amount before deposit: " + this.amount);
        this.amount += amount;
        System.out.println("Amount after deposit: " + this.amount);
        notify();
    }

    synchronized void withDraw(long amount) {
        if (amount > this.amount) {
            System.out.println("Sorry! Not sufficient balance");
            System.out.println("Your current balance is: " + this.amount);
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Amount before withdraw: " + this.amount);
            this.amount -= amount;
            System.out.println("Amount after withdraw: " + this.amount);
        }
    }
}
public class BankMoney {
    public static void main(String[] args) {
        Bank b = new Bank();
        ArrayList<Bank> bank = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bank.add(b);
        }
        for(Bank bank1: bank){
            new Thread(){
                public void run(){bank1.withDraw(15000);}
            }.start();
            new Thread(){
                public void run(){bank1.deposit(10000);}
            }.start();
        }
    }
}

