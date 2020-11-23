# Encrypted sockets
## Encrypted sockets
Your task is to write a program that connects to your @kth.se account, lists the contents and then retrieves the first mail from it. You are not allowed to use JavaMail but should instead to it "manually" according to IMAP. You should also send one mail to yourself using SMTP. The webmail has the following configuration:

Settings for receiving e-mail (incoming)
* Server: webmail.kth.se
* Port: 993
* Protocol: SSL/TLS
* Authentication: Normal password

Settings for sending e-mail (outgoing)
* Server: smtp.kth.se
* Port: 587
* Protocol: STARTTLS
* Authentication: Normal password

## Extra assignment

Change your browser in Task2 to use encryption and verify that a commercial (not your own hack) browser can connect and play the game.