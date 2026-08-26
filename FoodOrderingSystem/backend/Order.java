public class Order {

    private int orderId;
    private Customer customer;
    private Cart cart;

    public Order(int orderId, Customer customer, Cart cart) {
        this.orderId = orderId;
        this.customer = customer;
        this.cart = cart;
    }

    public void placeOrder(Payment payment) {

        double total = cart.calculateTotal();

        payment.pay(total);

        System.out.println(
                "Order #" + orderId +
                " placed successfully for " +
                customer.getName()
        );

        System.out.println(
                "Total Amount: ₹" + total
        );
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotal() {
        return cart.calculateTotal();
    }
}