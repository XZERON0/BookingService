import React, { Component, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import apiClient from '../../api/ApiClient';
import useAuth from '../../hooks/useAuth';
const Login = ()=>{
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error ,setError] = useState("");
    const {login} = useAuth();

    const handleSubmit = async (e)=>{
        e.preventDefault();
        try
        {
            const response = await apiClient.post("/user/login", {email, password});
            let token = response.data;
            login(token);
        }
        catch (err)
        {
            setError("Ошибка при входе");
        }
    }
    return (
        <div className="login">
            <form onSubmit={handleSubmit}>
                <input type="email" value={email} onChange={(e)=>setEmail(e.target.value)} placeholder="Email"/>
                <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)} placeholder="Пароль"/>
                {error && <div style={{color: "red"}}>{error}</div>}
                <button type="submit">Войти</button>
                </form>
        </div>
    );
}

export default Login;