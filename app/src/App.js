import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Main from "./pages/Main";
import Index from "./pages/Index";
import NotFound from "./pages/NotFound";
import { AuthProvider, useAuthContext } from "./context/AuthContext"; 
import Register from "./components/Auth/Register";
import Login from "./components/Auth/Login";
import CurrentUser from "./pages/profile";
import routes, { url } from "./routes";
import BaseLayout from "./pages/BaseLayout";
const App = () => {
  return (
    <AuthProvider> 
      <Router>
        <Routes>
           {/* Base layout for main pages */}
           <Route path="/" element={<BaseLayout />}>
            <Route path={routes.home} element={<Main />} />
            <Route path={routes.index} element={<Index />} />
            <Route path={routes.userProfile} element={<CurrentUser />} />
          </Route>
          {/* Separate routes for authentication */}
          <Route path={routes.login} element={<Login />} />
          <Route path={routes.register} element={<Register />} />

          {/* Fallback for 404 */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
