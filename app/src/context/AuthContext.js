import React, { createContext, useContext, useState, useEffect } from "react";
import Cookies from "js-cookie";
import apiClient from "../api/ApiClient"; 

const AuthContext = createContext();

export const useAuthContext = () => {
  return useContext(AuthContext);
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  useEffect(() => {
    const token = Cookies.get("token");
    if (token) {
      apiClient.get("/system/token")
        .then(response => {
          if (response.data === "Token is valid") {
            setIsAuthenticated(true);
            getUserInfo();
          } else {
            setIsAuthenticated(false);
            refreshToken();
          }
        })
        .catch(() => {
          setIsAuthenticated(false);
          refreshToken();
        });
    } else {
      setIsAuthenticated(false);
    }
  }, []);

  const refreshToken = () => {
    const refreshToken = Cookies.get("refreshToken");
    if (refreshToken) {
      apiClient.post("/system/token/refresh", {}, {
        headers: {
          "Authorization": `Bearer ${refreshToken}`,
        }
      })
        .then(response => {
          const newAccessToken = response.data.accessToken;
          Cookies.set("token", newAccessToken, { expires: 7, secure: true });
          setIsAuthenticated(true);
          getUserInfo();
        })
        .catch(() => {
          setIsAuthenticated(false);
          Cookies.remove("refreshToken");
        });
    }
  };
  
  const getUserInfo = async() => {
    await apiClient.get("/user/current")
      .then(response => {
         setUser(response.data);
         localStorage.setItem('user', response.data.id);
        })
        .catch(() => {
        setIsAuthenticated(false);
      });
  };

  const login =  async (token, refreshToken, callback) => {
    Cookies.set("token", token, { expires: 7, secure: true });
    Cookies.set("refreshToken", refreshToken, { expires: 7, secure: true });
    await getUserInfo();
    setIsAuthenticated(true);
    if (callback) callback();
  };

  const logout =  async() => {
    Cookies.remove("token");
    Cookies.remove("refreshToken");
    setUser(null);
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, user, login, logout, getUserInfo }}>
      {children}
    </AuthContext.Provider>
  );
};
