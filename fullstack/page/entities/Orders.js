export function orders_function() {
    const token = sessionStorage.getItem('jwtToken'); 
    fetch('http://localhost:8080/api/fullstack/orderDetail/all', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        const clientCardsContainer = document.getElementById('showData');
        let html = '';
        data.forEach(orders => { 
            const id = orders.id;
            const orderDate = orders.order_date; 
            const totalAmount = orders.total_amount; 
            const status = orders.status;
            const orderUser = orders.user.username;

            html += `
                <div class="card">
                    <div class="head">
                        <div>
                            <h1>Order ID: ${id}</h1> 
                            <h2>${orderUser}</h2>
                            <br>
                            <li><strong>Order Date: </strong> ${orderDate}</li> 	
                            <li><strong>Total Amount: </strong> ${totalAmount}</li> 
                            <li><strong>Status: </strong> ${status}</li>	
                        </div>
                    </div>
                </div>
            `;
        });
        clientCardsContainer.innerHTML = html;
    })
    .catch(error => console.error('Error:', error));

}


export function orders_function_title(){
    const title = document.querySelector("#customer_btn a");
    const newTitle = "Registered Orders"
    const dashboardTittle = document.getElementById("titleSection");
    dashboardTittle.innerHTML = newTitle;
}
