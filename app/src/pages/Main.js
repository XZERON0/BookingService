import React from "react";

const Main = () => {


  return (
    <div>
      <h1>Добро пожаловать на главную страницу!</h1>
      <p>Здесь будет описание проекта или что-то еще.</p>
      <p>{localStorage.getItem('token')}</p>
    </div>
  );
};

export default Main;
