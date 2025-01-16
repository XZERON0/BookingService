import { useState, useEffect } from "react";
import Cookies from "js-cookie";
import apiClient from "../api/ApiClient";

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = Cookies.get("token"); // Получаем токен из cookies

    if (token) {
      apiClient.get("/system/token").then(response => {
        if (response.data === "Token is valid") {
          setIsAuthenticated(true); // Если токен валиден, помечаем пользователя как аутентифицированного
        } else {
          setIsAuthenticated(false); // Если токен не валиден, убираем аутентификацию
        }
      }).catch(() => {
        setIsAuthenticated(false); // В случае ошибки токен невалиден
      });
    }
  }, []); // Этот хук срабатывает только при монтировании компонента

  const login = (token) => {
    Cookies.set("token", token, { expires: 7, secure: true }); // Сохраняем токен в cookies (срок жизни 7 дней)
    setIsAuthenticated(true); // Помечаем пользователя как аутентифицированного
  };

  const logout = () => {
    Cookies.remove("token"); // Удаляем токен из cookies
    setIsAuthenticated(false); // Помечаем пользователя как неаутентифицированного
  };

  return { isAuthenticated, login, logout };
};

export default useAuth;
