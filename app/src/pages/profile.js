import React, { useEffect, useState } from "react";
import { useAuthContext } from "../context/AuthContext";
import { useNavigate, useParams } from "react-router-dom";
import apiClient from "../api/ApiClient";
import routes from "../routes";

const CurrentUser = () => {
  const { user, logout} = useAuthContext();
  const { userId } = useParams();
  const [fetchedUser, setFetchedUser] = useState(null);
  const [myProfile, setMyProfile] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    if (user && user.id==userId) {
      document.title = "Ваш профиль";
      setMyProfile(true);
    }
    
    else {
      apiClient.get(`/user/${userId}`)
        .then(response => {
          setFetchedUser(response.data);
          document.title = `${response.data.name} - Профиль`;
        })
        .catch(error => {
          console.error(error);
          document.title = "Ошибка загрузки профиля";
        });
      }

    return () => {
      document.title = "Мой сайт";
    };
  }, [user, userId]);

  const handleLogout = () => {
    logout();
    navigate(routes.home);
  };
  

  // Проверяем, если данные о пользователе еще не загружены
  const currentUser = myProfile? user : fetchedUser;

  if (!currentUser) {
    return <p>Загрузка профиля...</p>;
  }

  return (
    <div>
      <h1>Пользователь</h1>
      <p>Имя: {currentUser.name}</p>
      <p>Email: {currentUser.email}</p>
      {myProfile && currentUser.id == userId && (
        <button onClick={handleLogout}>Выйти</button>
      )}
    </div>
  );
};

export default CurrentUser;
