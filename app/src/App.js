import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Main from "./components/Main";
import Index from "./components/Index";
import NotFound from "./components/NotFound";

const App = () => {
  return (
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
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
};

export default App;
