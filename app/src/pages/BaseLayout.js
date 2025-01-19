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
import '../css/style.css';

const BaseLayout = () => {
  const {logout} = useAuth();
  let user = useAuthContext().user;
  if (!user || user == null) {
    user = localStorage.getItem('user');
  }
  return (
    <Router>
      <nav className="nav-bar">
        <ul className="nav-list">
          <li className="nav-item"><a href="/" className="nav-link">Главная</a></li>
          <li className="nav-item"><a href="/index" className="nav-link">Индекс</a></li>
          {user ? (
            <>
              <li className="nav-item"><a href={`/profile/${user.id}`} className="nav-link">Профиль</a></li>
              <li className="nav-item"><button onClick={() => {
                logout();
                localStorage.removeItem('user');
                window.location.reload();
              }} className="nav-button">Выход</button></li>
            </>
          ) : (
            <>
              <li className="nav-item"><a href="/login" className="nav-link">Вход</a></li>
              <li className="nav-item"><a href="/register" className="nav-link">Регистрация</a></li>
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