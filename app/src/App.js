import React from "react";
import { AuthProvider, useAuthContext } from "./context/AuthContext"; 
import BaseLayout from "./pages/BaseLayout";

const App = () => {
  return (
    <AuthProvider>
      {/* Здесь просто показываем компоненты без роутинга */}
      <BaseLayout />
      {/* Все страницы отображаются внутри BaseLayout, например */}
      {/* Нужно будет добавить логику отображения отдельных страниц */}
    </AuthProvider>
  );
};

export default App;
