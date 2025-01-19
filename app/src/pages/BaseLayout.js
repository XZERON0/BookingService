import React, { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext";
import Main from "./Main";
import Index from "./Index";
import Profile from "./profile";
import Login from "../components/Auth/Login";
import Register from "../components/Auth/Register";
import NotFound from "./NotFound";
import useAuth from "../hooks/useAuth";

const BaseLayout = () => {
  // const { user } = useAuthContext();
  const {logout} = useAuth();
  let user = useAuthContext().user;
  if (!user || user == null)
    {
      user = localStorage.getItem('user');
    }
  return (
    <Router>
      <nav>
        <ul>
          <li><a href="/">Главная</a></li>
          <li><a href="/index">Индекс</a></li>
          {user ? (
            <>
              <li><a href={`/profile/${user.id}`}>Профиль</a></li>
              <li><button onClick={() => {
                logout();
                localStorage.removeItem('user');
                window.location.reload();
              }}>Выход</button></li>
            </>
          ) : (
            <>
              <li><a href="/login">Вход</a></li>
              <li><a href="/register">Регистрация</a></li>
            </>
          )}
        </ul>
      </nav>

      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/index" element={<Index />} />
        <Route path="/profile/:userId" element={<Profile />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
};

export default BaseLayout;
