import React, { useEffect } from "react";

const ServerError = () => {
  useEffect(()=>{
    document.title = "500: Проблемы с сервером";
  }, [])
  return (
    <div>
      <h1>500: Проблемы с сервером</h1>
      <p>К сожалению, произошла ошибка на сервере.</p>
    </div>
  );
};

export default ServerError;
