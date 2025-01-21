
import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient";
import { useNavigate } from "react-router-dom";
import routes  from "../routes";

const Index = () => {
  const [data, setData] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [searchValue, setSearchValue] = useState("");
  const navigate = useNavigate();

  document.title = "Сервисы";
  useEffect(() => {
    // Получение категорий
    apiClient.get("/service")
      .then(response => {setCategories(response.data);
      })
      .catch(error => console.error("Ошибка загрузки категорий:", error));

    // Получение провайдеров
    apiClient.get("/provider")
      .then(response =>{ setData(response.data);})
      .catch(error => console.error("Ошибка загрузки данных:", error));
  }, []);
const filteredData = data.filter(item => {
  const name = item.user.name.toLowerCase();
  const email = item.user.email.toLowerCase();
  const providerService = item.providerServices.map(item=>item.subServiceCategory.title);
  const search = searchValue.toLowerCase();
  return name.includes(search) || email.includes(search) || providerService.join(' ').toLowerCase().includes(search);
});
  return (
    <div>
      <div className="form-search">
        <input
          type="text"
          name="searchUser"
          value={searchValue}
          placeholder="Поиск..."
          onChange={(e) => setSearchValue(e.target.value)}
        />
        <select
          value={selectedCategory}
          onChange={(e) => setSelectedCategory(e.target.value)}
        >
          <option value="">Все категории</option>
          {categories.map(cat => (
            <option key={cat.id} value={cat.id}>{cat.type}</option>
          ))}
        </select>
      </div>

     {Array.isArray(filteredData) && filteredData.length > 0 ? (
  filteredData.map(item => (
    <div key={item.id}>
      <p>{item.user.name}</p>
      <p>{item.user.email}</p>
      {Array.isArray(item.providerServices) && item.providerServices.length > 0 ? (
        <div>
          <p>Предоставляемые услуги:</p>
          <ul>
            {item.providerServices.map(el => (
              <li key={el.subServiceCategory.category.id}>{el.subServiceCategory.title}</li> 
            ))}
          </ul>
          <button onClick={()=>{localStorage.setItem('order', item.id); navigate(routes.order);
          }}>
            Заказать услугу
          </button>
        </div>
      ) : (
        <p>Нет предоставляемых услуг</p>
      )}
    </div>
  ))
) : (
  <p>Ничего не найдено</p>
       )}
      </div>
    );}

export default Index;
