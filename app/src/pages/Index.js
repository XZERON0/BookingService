import React, { useEffect, useState } from "react";
import apiClient from "../api/ApiClient"; // Убедитесь, что путь правильный


const Index = () => {
  const [data, setData] = useState([]);
  const [searchValue, setSV] = useState("");
  document.title = "Сервисы";
  useEffect(() => {
    if (searchValue!= "") {
      apiClient.get(`/user?search=${searchValue}`).then((response) => {
        console.log(response);
        
        setData(response.data);
        });
      }
    apiClient.get("/user?ascending=true")
      .then(response => {
        console.log(response.data.content);
        
        setData(response.data.content);
      })
      .catch(error => console.error("Ошибка загрузки данных:", error));
  }, [searchValue]);

  // const handleSearch = (e) => {
  //   setSV(e.target.value);
  //   }
  //   const filteredData = data.filter(item => item.name.toLowerCase().includes(searchValue.toLowerCase()));
  //   console.log(filteredData);
  //   return (
  //     <div>
  //       <input type="text" value={searchValue} onChange={handleSearch} placeholder="Поиск" />
  //       <ul>
  //         {filteredData.map((item, index) => (
  //           <li key={index}>
  //             <a href={item.url}>{item.name}</a>
  //             </li>
  //             ))}
  //             </ul>
  //     </div>
  //   )}
return (
  
  <div>
    <div className="form-search">
      <form>
        <input type="text" name="searchUser" value={searchValue} onChange={(e)=> setSV(e.target.value)} />
      </form>
  </div>
    {Array.isArray(data) && data.map(item => (
      <div key={item.id}>   
        <p>Имя: {item.name}</p>
        <p>Фамилия: {item.email}</p>
        <p>статус: {item.role}</p>
        <p>ID: {item.id}</p>
      </div>
    ))}
  </div>
);
}
export default Index;
