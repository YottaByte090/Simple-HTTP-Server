/*
 * MIT License
 *
 * Copyright (c) 2018 Sangwon Ryu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.sangwon.httpserver;

import com.sangwon.httpserver.request.RequestParser;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-08
 */

public class HttpServer {
    private ServerSocket serverSocket;
    private List<SocketTask> clientSocket;
    private int port;

    private boolean isAlive = true;
    private boolean isFirst = true;

    public HttpServer(int port){
        if(port < 0 || port > 65535){
            throw new InvalidParameterException();
        }else{
            this.port = port;
        }
    }

    public synchronized void init() throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(port));

        isFirst = false;
    }

    public synchronized void start() throws IOException {
        if(isFirst){
            init();
        }

        while(isAlive){
            Socket socket = serverSocket.accept();
            SocketTask clientTask = new SocketTask(socket);

            clientTask.start();
        }
    }

    public synchronized void stop()
    {
        try{
            if(serverSocket != null){
                serverSocket.close();
            }

            for(SocketTask client : clientSocket){
                client.close();
            }

            System.out.println("서버 종료");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
