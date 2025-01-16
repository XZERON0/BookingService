import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient";
import useAuth from "../hooks/useAuth";  // Исправлено импортирование
import { useParams } from "react-router-dom";

const CurrentUser = () => {
  const { logout } = useAuth();  // Используем хук useAuth
  const [data, setData] = useState({});  // Используем объект вместо массива
  const {userId} = useParams();
  const [currentUserId, uId] = useState("");
  useEffect(() => {
    apiClient.get("/user/current").then(response=>{console.log(response.data);
      uId(response.data.id);
    }).catch((error)=> {}
    );
    apiClient.get(`/user/${userId}`).then(
      (response) => {
        setData(response.data); 
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
      {currentUserId === data.id && (
        <button onClick={handleLogout}>Выйти</button>  // Кнопка для выхода
      )}

    </div>
  );
};

export default CurrentUser;
