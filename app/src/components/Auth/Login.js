import React, { useEffect, useState } from "react";
import useAuth from "../../hooks/useAuth";
import apiClient from "../../api/ApiClient";
import routes, {url } from "../../routes";
import { useAuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const { login, isAuthenticated} = useAuth();
    const {user} = useAuthContext();
    const navigate = useNavigate();
    document.title = "Логин";
    useEffect(()=>
        {
          
        if(isAuthenticated && user)
        {
            navigate(url(routes.userProfile, { userId: user.id }));
        }
        }, [isAuthenticated, user]);
        const handleSubmit = async (e) => {
          e.preventDefault();
          try {
            const response = await apiClient.post("/user/login", { email, password });
            login(response.data.token, response.data.refreshToken, true);
            console.log(isAuthenticated);
            
              navigate(url(routes.userProfile, { userId: response.data.user.id }));
          } catch (err) {
            setError("Ошибка при входе");
            console.error(err);
          }
        };
        

  return (
    <div className="login">
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Email"
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Пароль"
        />
        {error && <div style={{ color: "red" }}>{error}</div>}
        <button type="submit">Войти</button>
      </form>
      <a href={routes.register}>Регистрация</a>
    </div>
  );
};

export default Login;
