import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../../api/ApiClient";
import useAuth from "../../hooks/useAuth";

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const {login} = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await apiClient.post("/user/register", { name,email, password });
      console.log(response.data);
      // login(response.data.token, response.data.user);

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
    </div>
  );
};

export default Register;
