import axios from "axios";
import Cookies from "js-cookie";
const token = Cookies.get("token");
console.log("accessToken"+token);
console.log("refreshToken"+Cookies.get('refreshToken'));

let headers = 
{
  "Content-Type": "application/json",
}

if (token)
  {
    headers["Authorization"]=`Bearer ${token}`
  }

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: headers,
  withCredentials: true
});

export default apiClient;
