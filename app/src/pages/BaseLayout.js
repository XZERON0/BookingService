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
import Order from "./Order";
import Settings from "./Settings";
import routes from "../routes";
// import 'bootstrap/dist/css/bootstrap.min.css';
function BaseLayout() {
  const { logout } = useAuth();
  let user = useAuthContext().user;
  let isAuthenticated = useAuthContext().isAuthenticated;
  if ((!user || user == null) && isAuthenticated) {
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
              } } className="nav-button">Выход</button></li>
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
        <Route path={routes.home} element={<Main />} />
        <Route path={routes.index} element={<Index />} />
        <Route path={routes.userProfile} element={<Profile />} />
        <Route path={routes.login} element={<Login />} />
        <Route path={routes.register} element={<Register />} />
        <Route path={routes.order} element={<Order />} />
        <Route path={routes.settings} element={<Settings />} />
        <Route path={routes.notFound} element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default BaseLayout;