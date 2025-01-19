import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext";
import apiClient from "../api/ApiClient";

const Profile = () => {
  const { user } = useAuthContext();
  const { userId } = useParams();
  const [fetchedUser, setFetchedUser] = useState(null);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await apiClient.get(`/user/${userId}`);
        setFetchedUser(response.data);
        document.title = `${response.data.name} - Профиль`;
        const provider = await apiClient.get('')
      } catch (error) {
        console.error("Ошибка загрузки профиля:", error);
        document.title = "Ошибка загрузки профиля";
      }
    };

    if (user && user.id === parseInt(userId, 10)) {
      setFetchedUser(user);
      document.title = "Ваш профиль";
    } else {
      fetchProfile();
    }
  }, [user, userId]);

  if (!fetchedUser) {
    return <p>Загрузка профиля...</p>;
  }

  return (
    <div>
      <h1>Пользователь</h1>
      <p>Имя: {fetchedUser.name}</p>
      <p>Email: {fetchedUser.email}</p>
    </div>
  );
};

export default Profile;
