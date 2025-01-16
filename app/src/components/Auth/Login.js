import React, { Component, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import apiClient from '../../api/ApiClient';
import useAuth from '../../hooks/useAuth';
import routes, {url} from '../../routes';
const Login = ()=>{
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error ,setError] = useState("");
    const {isAuthenticated,login} = useAuth();
    const navigate = useNavigate();
    const handleSubmit = async (e)=>{
        e.preventDefault();
        try
        {
            const response = await apiClient.post("/user/login", {email, password});
            let token = response.data.token;
            console.log(response.data.user.id);
            login(token);
            const userProfileUrl = url(routes.userProfile, { userId: response.data.user.id });
            console.log(userProfileUrl);
            navigate(userProfileUrl); 
            
            
        }
        catch (err)
        {
            setError("Ошибка при входе");
            console.log(err);
            
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