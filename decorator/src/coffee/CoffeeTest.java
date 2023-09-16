package coffee;

public class CoffeeTest {

    public static void main(String[] args) {
        KenyaCoffee kenyaCoffee = new KenyaCoffee();

        kenyaCoffee.brewing();

        System.out.println();
        Coffee kenyaLatte = new Latte(kenyaCoffee);
        kenyaLatte.brewing();

        System.out.println();
        Coffee mochaKenya = new MochaCoffee(new Latte(new KenyaCoffee()));
        mochaKenya.brewing();

        System.out.println();
        Coffee etiopiaCoffee = new MochaCoffee(new Latte(new KenyaCoffee()));
        etiopiaCoffee.brewing();
    }

}
