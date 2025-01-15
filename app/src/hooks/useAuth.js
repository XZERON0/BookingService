import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import routes from "../routes";

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsAuthenticated(true);  // Если токен существует, считаем пользователя аутентифицированным
    }
  }, []);  // Этот хук срабатывает только при монтировании компонента

  const login = (token) => {
    localStorage.setItem("token", token);
    setIsAuthenticated(true);  // Помечаем пользователя как аутентифицированного
    navigate(routes.userProfile);
  };

  const logout = () => {
    localStorage.removeItem("token");
    setIsAuthenticated(false);  // Помечаем пользователя как неаутентифицированного
    navigate(routes.home);
  };

  return { isAuthenticated, login, logout };
};

export default useAuth;
