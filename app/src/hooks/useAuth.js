import { useState, useEffect } from "react";
import Cookies from "js-cookie";
import apiClient from "../api/ApiClient";  // Предполагается, что apiClient - это конфигурация для вашего API.

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = Cookies.get("token"); // Получаем токен из cookies

    if (token) {
      apiClient.get("/system/token")
        .then(response => {
          if (response.data === "Token is valid") {
            setIsAuthenticated(true); // Если токен валиден, помечаем пользователя как аутентифицированного
          } else {
            setIsAuthenticated(false); // Если токен не валиден, убираем аутентификацию
            refreshToken(); // Попытаться обновить токен
          }
        })
        .catch(() => {
          setIsAuthenticated(false); // В случае ошибки токен невалиден
          refreshToken(); // Попытаться обновить токен
        });
    } else {
      setIsAuthenticated(false); // Если нет токена, сразу помечаем как неаутентифицированного
    }
  }, []); // Этот хук срабатывает только при монтировании компонента

  // Функция для обновления токена
  const refreshToken = () => {
    const refreshToken = Cookies.get("refreshToken"); // Получаем refresh токен
    
    if (refreshToken) {
      apiClient.post("/system/token/refresh", {}, {
        headers: {
          "Authorization": `Bearer ${refreshToken}` // Отправляем refresh token
        }
      })
        .then(response => {
          const newAccessToken = response.data.accessToken;
          Cookies.set("token", newAccessToken, { expires: 7, secure: true }); // Сохраняем новый токен в cookies
          setIsAuthenticated(true); // Обновляем статус аутентификации
        })
        .catch(() => {
          setIsAuthenticated(false); // Если refresh токен невалиден, сбрасываем статус аутентификации
          Cookies.remove("refreshToken");
        });
    }
  };

  const login = (token, refreshToken) => {
    Cookies.set("token", token, { expires: 7, secure: true }); // Сохраняем токен в cookies (срок жизни 7 дней)
    Cookies.set("refreshToken", refreshToken, { expires: 7, secure: true }); // Сохраняем refresh token
    
    setIsAuthenticated(true); // Помечаем пользователя как аутентифицированного
  };

  const logout = () => {
    Cookies.remove("token"); // Удаляем токен из cookies
    Cookies.remove("refreshToken"); // Удаляем refresh token
    setIsAuthenticated(false); // Помечаем пользователя как неаутентифицированного
  };

  return { isAuthenticated, login, logout, refreshToken };
};

export default useAuth;
