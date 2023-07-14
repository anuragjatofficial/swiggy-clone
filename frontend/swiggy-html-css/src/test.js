let data = null;



window.addEventListener("load",()=>{
    data = JSON.parse(localStorage.getItem("order"));
    Display(data);
})

function Display(orderData){
    if(orderData.orderId)
    document.getElementById("ordercard").innerHTML = OrderCard(orderData);
    else
        document.getElementById("ordercard").innerHTML = "sorry";
}


function OrderCard(orderData){
    return `<div>
                <div id="overview">
                    <h1>#${orderData.orderId}</h1>
                    <span><p id="date">${orderData.orderDateAndTime}</p>
                    <p>${orderData.orderStatus}</p><span>
                </div>
                <div>
                    <div id="orderStatus" class="card">
                        <div>
                            <div>
                                <h3>Food items</h3>
                            </div>
                            <div id="food-items">
                                <div>
                                    <img width="64" height="64" src="https://img.icons8.com/cotton/64/fast-food--v2.png"
                                        alt="fast-food--v2" />
                                </div>
                                <div>
                                    <h3>Items</h3>
                                    <br>
                                   ${orderData.items.map(item => `<p>${item}</p>`).join('')}
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div>
                            Total bill - ₹${orderData.totalAmount}
                        </div>
                    </div>
                    <div id="restaurantDetails" class="card">
                        <div>
                            <div>
                                <h3>Restaurant details</h3>
                            </div>
                            <div id="food-items">
                                <div>
                                    <img width="64" height="64" src="https://img.icons8.com/dusk/64/restaurant.png" alt="restaurant" />
                                </div>
                                <div>
                                    <h3>${orderData.restaurant.restaurantName}</h3>
                                    <br>
                                    <p>${orderData.restaurant.address}</p>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div>
                            ${orderData.restaurant.address}
                        </div>
                    </div>
                    <div id="customerDetails" class="card">
                        <div>
                            <div>
                                <h3>Customer details</h3>
                            </div>
                            <div id="food-items">
                                <div>
                                    <img width="64" height="64" src="https://img.icons8.com/officel/80/gender-neutral-user.png"
                                        alt="gender-neutral-user" />
                                </div>
                                <div>
                                    <h3>${orderData.customer.name}</h3>
                                    <br>
                                    <p>${orderData.customer.email}</p>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div>
                            ${orderData.customer.email}
                        </div>
                    </div>
                    <div id="deliveryPartenerdetails" class="card">
                        <div>
                            <div>
                                <h3>delivery partner details </h3>
                            </div>
                            <div id="food-items">
                                <div>
                                    <img width="64" height="64"
                                        src="https://img.icons8.com/external-flaticons-lineal-color-flat-icons/64/external-delivery-boy-cyber-monday-flaticons-lineal-color-flat-icons.png"
                                        alt="external-delivery-boy-cyber-monday-flaticons-lineal-color-flat-icons" />
                                </div>
                                <div>
                                    ${orderData.deliveryPartner ? `<h3>${orderData.deliveryPartner.name}</h3>
                                    <br>
                                    <p>${orderData.deliveryPartner.mobileNumber}</p>` :   `<h3>Not assigned yet </h3>`}
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div>
                            ${orderData.deliveryPartner ? `${orderData.deliveryPartner.address}`: `<h3>Not assigned yet</h3>`}
                        </div>
                    </div>
                </div>
            </div>`;
}