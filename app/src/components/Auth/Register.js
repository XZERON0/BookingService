import React, { useEffect, useState } from "react";
import apiClient from "../../api/ApiClient";
import useAuth from "../../hooks/useAuth";
import {useAuthContext } from "../../context/AuthContext";
import routes, {  url } from "../../routes";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const {login, isAuthenticated} = useAuth();
  const {user} = useAuthContext();
  // const [loading, setLoading] = useState(false);
  // const [success, setSuccess] = useState(false);
  // const [successMessage, setSuccessMessage] = useState("");
  const navigate = useNavigate();
  useEffect(()=>
    {
    document.title= "Регистрация";
    if (isAuthenticated && user)
      {
        navigate(url(routes.userProfile, {userId:user.id}));
      }
    })
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await apiClient.post("/user/register", { name,email, password });
      const loginResponse = await apiClient.post("/user/login", {email, password});      
      login(loginResponse.data.token, loginResponse.data.refreshToken, true);
      navigate(url(routes.userProfile, {userId:(loginResponse.data.user.id)}));

    } catch (err) {
      setError("Ошибка при регистрации. Попробуйте снова.");
    }
  };

  return (
    <div>
      <h2>Регистрация</h2>
      <form onSubmit={handleSubmit}>
        <div>
            <label>
                Имя:
            </label>
        </div>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        <div>
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Пароль</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <div>{error}</div>}
        <button type="submit">Зарегистрироваться</button>
      </form>
      <a href={routes.login}>Войти</a>
    </div>
  );
};

export default Register;
