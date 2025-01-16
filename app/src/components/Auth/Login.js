import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import apiClient from "../../api/ApiClient";
import routes, { url } from "../../routes";
import { useAuthContext } from "../../context/AuthContext";

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const { login, isAuthenticated} = useAuth();
    const {user} = useAuthContext();
    const navigate = useNavigate();
    
    useEffect(()=>
        {
        document.title = "Логин";
        if(isAuthenticated && user)
        {
            navigate(url(routes.userProfile, { userId: user.id }));
        }
        // else
        // {

        // }
        })

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.post("/user/login", { email, password });
      const { token, refreshToken, user } = response.data;
      login(token, refreshToken);
      const userProfileUrl = url(routes.userProfile, { userId: user.id });
      navigate(userProfileUrl);
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
