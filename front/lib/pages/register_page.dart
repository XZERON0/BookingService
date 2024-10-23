import 'package:flutter/material.dart';

class RegisterPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Страница регистрации')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Форма регистрации'),
            TextField(
              decoration: InputDecoration(labelText: 'Имя'),
            ),
            TextField(
              decoration: InputDecoration(labelText: 'Email'),
            ),
            TextField(
              decoration: InputDecoration(labelText: 'Пароль'),
              obscureText: true,
            ),
            ElevatedButton(
              onPressed: () {
                // Здесь будет логика регистрации
                Navigator.pushNamed(context, '/');
              },
              child: Text('Зарегистрироваться'),
            ),
          ],
        ),
      ),
    );
  }
}
