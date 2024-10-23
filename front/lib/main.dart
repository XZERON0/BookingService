import 'package:flutter/material.dart';
import 'pages/home_page.dart';
import 'pages/login_page.dart';
import 'pages/services_page.dart';
import 'pages/register_page.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Frontend App',
      initialRoute: '/register',
      routes: {
        '/': (context) => HomePage(),
        '/login': (context) => LoginPage(),
        '/services': (context) => ServicesPage(),
        '/register': (context) => RegisterPage(),
      },
    );
  }
}
