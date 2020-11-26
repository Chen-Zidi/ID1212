# Task 4, HTTPServer / HTTPClient with SSL socket

##generate certificate and key store using keytool
Find jdk folder => go to bin folder => there is a keytool.exe

First you should use command line to the bin folder, then type in

`
keytool -genkey -alias server -keypass changeit -keyalg RSA -keysize 1024 -validity 365 -keystore d:/server.keystore -storepass changeit
`
to generate a server certificate file.
A few things that you can change:

server: alias

changeit:password

d:/server.keystore: file name and dir

Then you should import this file server.keystore into browser eg. Firefox Options=>Privacy and security=>Certificates=>View certificates=>your certificate=>import 

You can also use the server.keystore in the current folder, but you need to change the address in the program Server.java and also import it in the browser.

If you only want one-way verification, then this is enough.

If you want two-way verifiaction, please check: https://blog.csdn.net/weixin_30735745/article/details/97370781