// ENTITY CUSTOMERS QUERIES

export function customers_function() {
    const token = sessionStorage.getItem('jwtToken'); 
    fetch('http://localhost:8080/register/customer/all', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        const clientCardsContainer = document.getElementById('showData');
        let html = '';
        data.forEach(customer => { 
            const id = customer.id;
            const username = customer.username; 
            const email = customer.email; 

            html += `
                <div class="card">
                    <div class="head">
                        <div>
                            <h1>Customer ID: ${id}</h1> 
                            <h2>${username}</h2>
                            <br>
                            <li><strong>Email: </strong> ${email}</li> 			
                        </div>
                    </div>
                </div>
            `;
        });
        clientCardsContainer.innerHTML = html;
    })
    .catch(error => console.error('Error:', error));

}


export function customers_function_title(){
    const title = document.querySelector("#customer_btn a");
    const newTitle = "Registered Customers"
    const dashboardTittle = document.getElementById("titleSection");
    dashboardTittle.innerHTML = newTitle;
}





