// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, sort_child_properties_last

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

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
}

Widget home() {
  return ListView(
    padding: const EdgeInsets.all(12),
    children: <Widget>[
      Text("Bem-vindo a nossa loja",
          textAlign: TextAlign.center,
          style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black)),
      Image.asset("whey.png", height: 250, width: 250),
      Text("Suplemento de proteínas para atletas. Concentração 80%.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("soy.png", height: 250, width: 250),
      Text("Suplemento de proteína de soja. Concentração 26/30g", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("creatina.png", height: 250, width: 250),
      Text("Suplemento de creatina em pó", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("bcaa.png", height: 250, width: 250),
      Text("Suplemento de proteínas específicas para construção muscular.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("glutamina.png", height: 250, width: 250),
      Text("Suplemento que atua na recuperação muscular e homeostase do organismo.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("termogenico.png", height: 250, width: 250),
      Text("Suplemento para aumento do gasto calórico, acelerando o metabolismo.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("hipercalorico.png", height: 250, width: 250),
      Text("Suplemento para aumento da ingestão calórica, favorecendo o ganho de massa muscular.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("multivitaminico.png", height: 250, width: 250),
      Text("Suplemento equilibrado de diversas vitaminas.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("albumina.png", height: 250, width: 250),
      Text("Suplemento de proteína da albumina (clara do ovo), concentração 26/30g.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),),

      Image.asset("barra.png", height: 250, width: 250),
      Text("Barra de proteína.", textAlign: TextAlign.center, style:
              TextStyle(decoration: TextDecoration.none, color: Colors.black, fontSize: 18),
      ),
    ],
  );
}
