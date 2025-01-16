const routes = {
    home: "/",
    index: "/index",
    userProfile:`/profile/:userId`,
    login: "/login",
    register: "/register",
    about: "/about",
    // Добавьте сюда остальные маршруты
  };
  /**
 * Генерирует путь, подставляя параметры в шаблон.
 * @param {string} path - Маршрут с параметрами (например, '/profile/:userId')
 * @param {object} params - Объект параметров (например, { userId: 123 })
 * @returns {string} - Сгенерированный путь (например, '/profile/123')
 */
export const url = (path, params) => {
  return Object.keys(params).reduce(
    (url, key) => url.replace(`:${key}`, params[key]),
    path
  );
};
  export default routes;
  