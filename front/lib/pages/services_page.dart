import 'package:flutter/material.dart';

class ServicesPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Страница услуг')),
      body: Center(
        child: Text('Список доступных услуг'),
      ),
    );
  }
}
