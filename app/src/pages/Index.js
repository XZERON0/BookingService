import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient";
const Index = () => {
  const [data, setData] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [searchValue, setSearchValue] = useState("");

  document.title = "Сервисы";

  useEffect(() => {
    // Получение категорий
    apiClient.get("/service")
      .then(response => {setCategories(response.data);
      })
      .catch(error => console.error("Ошибка загрузки категорий:", error));

    // Получение провайдеров
    apiClient.get("/provider")
      .then(response =>{ setData(response.data); console.log(response);
      })
      .catch(error => console.error("Ошибка загрузки данных:", error));
  }, []);

  const handleSearch = () => {
    const query = `/user?search=${searchValue}&category=${selectedCategory}`;
    apiClient.get(query)
      .then(response => setData(response.data))
      .catch(error => console.error("Ошибка поиска:", error));
  };

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
        <button onClick={handleSearch}>Найти</button>
      </div>

      {Array.isArray(data) && data.length > 0 ? (
        data.map(item => (
          <div key={item.id}>
            <p>Имя: {item.name}</p>
            <p>Email: {item.email}</p>
            <p>Статус: {item.role}</p>
            <p>ID: {item.id}</p>
          </div>
        ))
      ) : (
        <p>Ничего не найдено</p>
      )}
    </div>
  );
};

export default Index;
