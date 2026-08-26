public class UPIPayment extends Payment {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Payment of ₹" + amount +
                " completed using UPI."
        );
    }
}