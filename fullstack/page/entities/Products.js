export function products_function() {
    const token = sessionStorage.getItem('jwtToken'); 
    fetch('http://localhost:8080/api/fullstack/products/all', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        const clientCardsContainer = document.getElementById('showData');
        let html = '';
        data.forEach(product => { 
            const id = product.id;
            const name = product.name; 
            const description = product.description; 
            const price = product.price; 
            const stock = product.stock_quantity;

            html += `
                <div class="card">
                    <div class="head">
                        <div>
                            <h1>Product ID: ${id}</h1> 
                            <h2>${name}</h2>
                            <br>
                            <li><strong>Description: </strong> ${description}</li> 
                            <li><strong>Price: </strong> ${price}</li> 
                            <li><strong>Stock: </strong> ${stock}</li>			
                        </div>
                    </div>
                </div>
            `;
        });
        clientCardsContainer.innerHTML = html;
    })
    .catch(error => console.error('Error:', error));
}


export function products_function_title(){
    const title = document.querySelector("#products_btn a");
    const newTitle = "Registered Products";
    const dashboardTittle = document.getElementById("titleSection");
    dashboardTittle.innerHTML = newTitle;
}