import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Main from "./pages/Main";
import Index from "./pages/Index";
import NotFound from "./pages/NotFound";
import { AuthProvider } from "./context/AuthContext";
import Register from "./components/Auth/Register";
import Login from "./components/Auth/Login";
const App = () => {
  return (
    <AuthProvider>
      <Router>
        <nav>
          <ul>
            <li><Link to="/">Главная</Link></li>
            <li><Link to="/index">Индекс</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/index" element={<Index />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
