import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Main from "./pages/Main";
import Index from "./pages/Index";
import NotFound from "./pages/NotFound";
import { AuthProvider } from "./context/AuthContext";
import Register from "./components/Auth/Register";
import Login from "./components/Auth/Login";
import CurrentUser from "./pages/profile";
import routes from "./routes";
const App = () => {
  return (
    <AuthProvider>
      <Router>
        <nav>
          <ul>
            <li><Link to={routes.home}>Главная</Link></li>
            <li><Link to={routes.index}>Индекс</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path={routes.home} element={<Main />} />
          <Route path={routes.index} element={<Index />} />
          <Route path={routes.login} element={<Login />} />
          <Route path={routes.register} element={<Register />} />
          <Route path={routes.userProfile} element={<CurrentUser />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
