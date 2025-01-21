import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext";
import apiClient, { backUrl } from "../api/ApiClient";
import "../css/profile.css";
const Profile = () => {
  const { user } = useAuthContext();
  const { userId } = useParams();
  const [fetchedUser, setFetchedUser] = useState(null);
  const [file, setFile] = useState(null);
  const [provider, setProvider] = useState(null);
  useEffect(() => {
    try{
      const providerResponse = apiClient.get(`/provider?userId=${userId}`).then(response=>setProvider(response.data)).catch(error=>{console.log();
      })
      
    }
    catch
    {
      
    }
    const fetchProfile = async () => {
      try {
        const response = await apiClient.get(`/user/${userId}`);
        setFetchedUser(response.data);
        document.title = `${response.data.name} - Профиль`;
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

  const changeAvatar = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("file", file);
    formData.append("userId", userId);

    try {
      const response = await apiClient.post("/user/uploadAvatar", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      console.log(response);
    } catch (error) {
      console.error("Ошибка загрузки аватара:", error);
    }
  };

  if (!fetchedUser) {
    return <p>Загрузка профиля...</p>;
  }

  return (
    <div>
      <div className="profile-container">
        {user && user.id === parseInt(userId, 10) && (
          <form onSubmit={changeAvatar} encType="multipart/form-data" className="avatar-form">
            <input
              type="file"
              onChange={(e) => {
                setFile(e.target.files[0]);
              }}
            />
            <button type="submit" className="change-avatar-btn">Изменить аватар</button>
          </form>
        )}
        <img src={backUrl + fetchedUser.avatar} alt="user-avatar" className="circle-image avatar" />
        <div className="user-info">
          {user && user.id === parseInt(userId, 10) && (
            <button>
              <i className="fa-solid fa-gear"></i>
            </button>
          )}
          <h1>Пользователь</h1>
          <p>Имя: {fetchedUser.name}</p>
          <p>Email: {fetchedUser.email}</p>
        </div>
      </div>
      <div className="order">
        Ваши заказы
      </div>
      {provider && (
        <div className="provider">
          <h2>Поставщик</h2>
          <h3>Ваши услуги</h3>
          {user && user.id === parseInt(userId, 10) ? (
            <form>
              <select multiple>
                {provider.map((el) =>
                  el.providerServices.map((element) => (
                    <option key={element.subServiceCategory.id} value={element.subServiceCategory.title} selected>
                      {element.subServiceCategory.title}
                    </option>
                  ))
                )}
              </select>
            </form>
          ) : (
            <div>
              {provider.map((el) =>
                el.providerServices.map((element) => (
                  <p key={element.subServiceCategory.id}>{element.subServiceCategory.title}</p>
                ))
              )}
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default Profile;