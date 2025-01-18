import React from "react";
import { Link, Outlet } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext";
import routes, { url } from "../routes";

const BaseLayout = () => {
  const { user, logout } = useAuthContext();

  return (
    <div>
      <nav>
        <ul>
          <li><Link to={routes.home}>Главная</Link></li>
          <li><Link to={routes.index}>Индекс</Link></li>
          {user ? (
            <>
              <li><Link to={url(routes.userProfile, {userId: user.id})}>Профиль</Link></li>
              <li><button onClick={logout}>Выход</button></li>
            </>
          ) : (
            <>
              <li><Link to={routes.login}>Вход</Link></li>
              <li><Link to={routes.register}>Регистрация</Link></li>
            </>
          )}
        </ul>
      </nav>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default BaseLayout;
