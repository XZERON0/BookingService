import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient";
import useAuth from "../hooks/useAuth";  // Исправлено импортирование

const CurrentUser = () => {
  const { logout } = useAuth();  // Используем хук useAuth
  const [data, setData] = useState({});  // Используем объект вместо массива

  useEffect(() => {
    apiClient.get("/user/current").then(
      (response) => {
        console.log(response);
        setData(response.data); // Данные, предположительно, в формате { name: "Имя", email: "email@example.com" }
      }
    ).catch((error) => console.error("Ошибка при загрузке данных: " + error));
  }, []);  // Массив зависимостей пустой, чтобы запрос выполнялся только при монтировании

  const handleLogout = () => {
    logout();  // Вызываем logout из useAuth
  };

  return (
    <div>
      <h1>Пользователь</h1>
      <p>Имя: {data.name}</p>
      <p>Email: {data.email}</p>
      <button onClick={handleLogout}>Выйти</button>
    </div>
  );
};

export default CurrentUser;
