import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient";
import { useNavigate } from "react-router-dom";
import routes, { url } from "../routes";

const Order = () => {
    document.title = "Бронирование";
    const [provider, setProvider] = useState([]);
    const providerId = localStorage.getItem('order');
    const navigate = useNavigate();
    useEffect(() => {
        apiClient.get(`/provider/${providerId}`)
            .then(response => {
                setProvider(response.data);
                console.log(response.data);
            })
            .catch(error => console.error(error));   
            
            }, []);
    return (
        <div>
            <h1>Бронирование</h1>
            <p>
                Выберите услугу и время бронирования из этого списка от поставщика
                {provider!=null && provider != [] && provider.user != null ?  (<button 
                onClick={() => navigate(url(routes.userProfile, {userId:provider.user.id}))}>
                    {provider.user.name}
                </button>) : ''}
                <form>

                <select multiple required>
                    {provider!=null && provider != [] && provider.providerServices != null ? 
                    provider.providerServices.map((service, index) => (
                        <option key={index}>{service.subServiceCategory.category.type}</option>
                    )) : ''}
                        
                </select>
                <input type="date" required/>
                <input type="time" required/>
                <button type="submit">Заказать услугу</button>
                </form>
            </p>
        </div>
    );
}

export default Order;
