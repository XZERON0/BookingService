import axios from "axios";
const token = localStorage.getItem('token');
let headers = 
{
  "Content-Type": "application/json",
}
if (token)
{
  headers["Authorization"]=`Bearer ${token}`
}
console.log(headers);

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: headers,
});

export default apiClient;
