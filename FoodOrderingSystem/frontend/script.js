let foodItems = [];
let cart = [];

// Get food items from Java backend
fetch("http://localhost:8080/api/food")
    .then(response => response.json())
    .then(data => {
        foodItems = data;
        displayFood();
    })

    .catch(error => {

        console.log("Error:", error);

        document.getElementById("food-container")
            .innerText =
            "Unable to load food items.";
    });

// Display food items
function displayFood() {

    let container = document.getElementById("food-container");
    container.innerHTML = "";
    foodItems.forEach(food => {
        container.innerHTML += `
            <div class="food">
                <h3>${food.name}</h3>
                <p>Price: ₹${food.price}</p>
                <button onclick="addToCart(${food.id})">
                    Add to Cart
                </button>
            </div>
        `;
    });
}

// Add food to cart
function addToCart(id) {
    let food = foodItems.find(item => item.id === id);
    cart.push(food);
    displayCart();
}


// Display cart
function displayCart() {
    let cartDiv = document.getElementById("cart");
    let total = 0;
    cartDiv.innerHTML = "";

    if (cart.length === 0) {
        cartDiv.innerText = "Your cart is empty.";
        document.getElementById("total").innerText = "0";
        return;
    }


    cart.forEach(item => {
        cartDiv.innerHTML += `
            <p>
                ${item.name} - ₹${item.price}
            </p>
        `;

        total += item.price;
    });


    document.getElementById("total").innerText = total;
}

// Place order
function placeOrder() {

    if (cart.length === 0) {

        alert("Your cart is empty!");

        return;
    }

    // Get IDs of selected food items
    let itemIds = cart.map(item => item.id);

    fetch("http://localhost:8080/api/order", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            itemIds: itemIds
        })

    })

    .then(response => response.json())

    .then(data => {

        alert(
            data.message +
            "\n\nOrder ID: " +
            data.orderId +
            "\nTotal: ₹" +
            data.total
        );

        cart = [];

        displayCart();

    })

    .catch(error => {

        console.log(error);

        alert("Unable to place order.");

    });
}