import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient"; // Убедитесь, что путь правильный


const Index = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    apiClient.get("/user")
      .then(response => {
        console.log(response.data.content);
        
        setData(response.data.content);
      })
      .catch(error => console.error("Ошибка загрузки данных:", error));
  }, []);

return (
  <div>
    {Array.isArray(data) && data.map(item => (
      <div key={item.id}>   
        <p>Имя: {item.name}</p>
        <p>Фамилия: {item.email}</p>
        <p>статус: {item.role}</p>
        <p>ID: {item.id}</p>
      </div>
    ))}
  </div>
);
}
export default Index;
