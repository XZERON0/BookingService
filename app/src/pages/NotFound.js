import React, { useEffect } from "react";

const NotFound = () => {
  useEffect(()=>{
    document.title = "404: Страница не найдена";
  }, [])
  return (
    <div>
      <h1>404 - Страница не найдена</h1>
      <p>К сожалению, такой страницы не существует.</p>
    </div>
  );
};

export default NotFound;
