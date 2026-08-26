public class CardPayment extends Payment {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Payment of ₹" + amount +
                " completed using Card."
        );
    }
}