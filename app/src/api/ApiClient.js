import axios from "axios";
import Cookies from "js-cookie";
export const backUrl = "http://localhost:8080"; 
const token = Cookies.get("token");
console.log("accessToken"+token);
console.log("refreshToken"+Cookies.get('refreshToken'));

let headers = 
{
  "Content-Type": "application/json",
}

if (token!= null) 
  {
    headers["Authorization"]=`Bearer ${token}`
  }

const apiClient = axios.create({
  baseURL: backUrl,
  headers: headers,
  withCredentials: true
});

export default apiClient;
