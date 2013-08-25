ArduinoTemperatureAnalyzer
==========================

Plot-building app for Arduino and DS18S20-like temperature sensor

Hello, folks!

Here is my first really valueable project in Java-Arduino couple. The main idea was to develop a tiny simple app which gets data
from Arudino COM and saves it to build a plot later.

Usage list:  
->Hardware  
|->Arduino Duemilanove w/ ATMega 328  
|->DS18S20 Temperature Sensor (requires OneWire lib)  
->Libraries  
|->JRE (obviously)  
|->jSSC (java simple serial connector https://code.google.com/p/java-simple-serial-connector/)  
|->aw from java2s.com for building plots  
|->OneWire.h for DS18S20  

I'm planing to make a version with BT and more precise measurement. 
