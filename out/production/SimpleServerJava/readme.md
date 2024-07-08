This client program is straightforward and simple because the echo server implements a simple protocol. The client sends text to the server, and the server echoes it back. When your client programs are talking to a more complicated server such as an HTTP server, your client program will also be more complicated. However, the basics are much the same as they are in this program:

Open a socket. \
Open an input stream and output stream to the socket. \
Read from and write to the stream according to the server's protocol. \
Close the streams. \
Close the socket. 