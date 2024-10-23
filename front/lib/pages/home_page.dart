import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Главная страница'),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.pushNamed(context, '/login');
            },
            child: Text('Вход', style: TextStyle(color: Colors.white)),
          ),
          TextButton(
            onPressed: () {
              Navigator.pushNamed(context, '/services');
            },
            child: Text('Услуги', style: TextStyle(color: Colors.white)),
          ),
        ],
      ),
      body: Center(
        child: Text('Добро пожаловать в наше приложение!'),
      ),
    );
  }
}
