// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, sort_child_properties_last

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(primaryColor: Colors.red),
      home: home(),
    );
  }

  Widget home() {
    return Scaffold(
      appBar: AppBar(
        title: Text("Olá mundo"),
      ),
      body: Container(
        color: Colors.yellow,
        width: double.infinity,
        height: double.infinity,
        child: Column(
          children: [
            const Text(
              "Texto",
              style: TextStyle(
                color: Colors.indigoAccent,
                decoration: TextDecoration.none,
                fontSize: 48,
                fontWeight: FontWeight.w500,
                fontStyle: FontStyle.italic,
              ),
            ),
            Text("Outro texto"),
          ],
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.center,
        ),
      ),
      drawer: Container(
        color: Colors.white,
      ),
      floatingActionButton: FloatingActionButton(onPressed: () {}),
    );
  }
}
