import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient";
import useAuth from "../hooks/useAuth";
import { useParams } from "react-router-dom";

const CurrentUser = () => {
  const { logout } = useAuth();
  const [data, setData] = useState({});
  const { userId } = useParams();
  const [currentUserId, uId] = useState("");

  useEffect(() => {
    // Получение текущего пользователя
    apiClient.get("/user/current").then((response) => {
      console.log(response.data);
      uId(response.data.id);
    }).catch((error) => console.error("Ошибка получения текущего пользователя: ", error));

    // Получение данных пользователя по ID
    apiClient.get(`/user/${userId}`).then((response) => {
      setData(response.data);
    }).catch((error) => console.error("Ошибка при загрузке данных: ", error));
  }, [userId]); // Добавьте userId в зависимости, чтобы обновление происходило при изменении параметра

  useEffect(() => {
    // Изменение title страницы
    if (data.name && data.id != currentUserId) {
      document.title = `${data.name} - Профиль`;}
    else if(data.id == currentUserId) 
      {
        document.title = "Ваш профиль";
    } else {
      document.title = "Загрузка профиля..."; // Временный title
    }

    // Очистка title при размонтировании компонента
    return () => {
      document.title = "Мой сайт"; // Установите базовый title
    };
  }, [data.name]); // Изменяйте title при изменении имени пользователя

  const handleLogout = () => {
    logout();
  };

  return (
    <div>
      <h1>Пользователь</h1>
      <p>Имя: {data.name}</p>
      <p>Email: {data.email}</p>
      {currentUserId === data.id && (
        <button onClick={handleLogout}>Выйти</button>
      )}
    </div>
  );
};

export default CurrentUser;
