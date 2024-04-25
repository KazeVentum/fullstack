
import * as Customer from "../entities/Customer.js";
import * as Products from "../entities/Products.js";
import * as Orders from "../entities/Orders.js";


// Customer 
document.getElementById("customer_btn").addEventListener("click", function() {
    Customer.customers_function_title();
    Customer.customers_function();
    Customer.handleSearchInput();
});

// Products 
document.getElementById("products_btn").addEventListener("click", function() {
    Products.products_function_title();
    Products.products_function();
});

// Orders 
document.getElementById("orders_btn").addEventListener("click", function() {
    Orders.orders_function_title();
    Orders.orders_function();
});
