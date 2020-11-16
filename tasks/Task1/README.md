# Task 1, Sockets
## Chat application using sockets

Your task is to write a simple chat program consisting of a server, and a client where the server forwards incoming messages from a client to all other clients. Specifically regarding threads, they should be as follows:

* ChatClient.java: Has two threads, one to listen for incoming messages from the server and one to send messages to the server.
* ChatServer.java: Has one thread to each of the clients currently connected but also one thread to listen for new incoming connections from new clients.

There is no requirement to handle user login or chatroom. Plain and simple.

## Extra assignment

Find and download a (simple) network sniffer, record some traffic from your chat and explain the TCP headers and flags. Compare with TCP-datagrams from other traffic for example your web browser.

### Answer

We use the Wireshark software to record the TCP traffic.

Next we will see the difference of teh TCP headers between the cases.

The TCP header will record the port of the source and the destination, the sequence number, etc.

The TCP flags is a 12 bits code. Each flag will control by 1 bit. For example, the PSHACK package's flag is 0x018(000000011000) and an ACK package's flag is 0x010(000000010000). The value of the fourth digit from the last decide the PSH flag.

In our chat packages, the push ack package's info are:
```
Transmission Control Protocol, Src Port: 53328, Dst Port: 8089, Seq: 1, Ack: 1, Len: 6
    Source Port: 53328
    Destination Port: 8089
    [Stream index: 4]
    [TCP Segment Len: 6]
    Sequence number: 1    (relative sequence number)
    Sequence number (raw): 2940399974
    [Next sequence number: 7    (relative sequence number)]
    Acknowledgment number: 1    (relative ack number)
    Acknowledgment number (raw): 1859471980
    1000 .... = Header Length: 32 bytes (8)
    Flags: 0x018 (PSH, ACK)
        000. .... .... = Reserved: Not set
        ...0 .... .... = Nonce: Not set
        .... 0... .... = Congestion Window Reduced (CWR): Not set
        .... .0.. .... = ECN-Echo: Not set
        .... ..0. .... = Urgent: Not set
        .... ...1 .... = Acknowledgment: Set
        .... .... 1... = Push: Set
        .... .... .0.. = Reset: Not set
        .... .... ..0. = Syn: Not set
        .... .... ...0 = Fin: Not set
        [TCP Flags: ·······AP···]
    Window size value: 512
    [Calculated window size: 65536]
    [Window size scaling factor: 128]
    Checksum: 0xfe2e [unverified]
    [Checksum Status: Unverified]
    Urgent pointer: 0
    Options: (12 bytes), No-Operation (NOP), No-Operation (NOP), Timestamps
        TCP Option - No-Operation (NOP)
        TCP Option - No-Operation (NOP)
        TCP Option - Timestamps: TSval 922328009, TSecr 922322300
    [SEQ/ACK analysis]
    [Timestamps]
    TCP payload (6 bytes)

```

And we try to record the tcp when we visit the google.com, there are the info of one ACK package:
```
Transmission Control Protocol, Src Port: 42266, Dst Port: 443, Seq: 1294, Ack: 42399, Len: 0
    Source Port: 42266
    Destination Port: 443
    [Stream index: 0]
    [TCP Segment Len: 0]
    Sequence number: 1294    (relative sequence number)
    Sequence number (raw): 1588773105
    [Next sequence number: 1294    (relative sequence number)]
    Acknowledgment number: 42399    (relative ack number)
    Acknowledgment number (raw): 362548552
    1000 .... = Header Length: 32 bytes (8)
    Flags: 0x010 (ACK)
        000. .... .... = Reserved: Not set
        ...0 .... .... = Nonce: Not set
        .... 0... .... = Congestion Window Reduced (CWR): Not set
        .... .0.. .... = ECN-Echo: Not set
        .... ..0. .... = Urgent: Not set
        .... ...1 .... = Acknowledgment: Set
        .... .... 0... = Push: Not set
        .... .... .0.. = Reset: Not set
        .... .... ..0. = Syn: Not set
        .... .... ...0 = Fin: Not set
        [TCP Flags: ·······A····]
    Window size value: 578
    [Calculated window size: 578]
    [Window size scaling factor: -1 (unknown)]
    Checksum: 0x7866 [unverified]
    [Checksum Status: Unverified]
    Urgent pointer: 0
    Options: (12 bytes), No-Operation (NOP), No-Operation (NOP), Timestamps
        TCP Option - No-Operation (NOP)
        TCP Option - No-Operation (NOP)
        TCP Option - Timestamps: TSval 2405164542, TSecr 1916511043
    [SEQ/ACK analysis]
    [Timestamps]
```