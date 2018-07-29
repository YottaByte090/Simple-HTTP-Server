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

package com.yottabyte090.httpserver;

import com.yottabyte090.httpserver.preprocessor.Preprocessor;

import java.io.*;
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
    private Preprocessor preprocessor;
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

    public HttpServer(int port, Preprocessor preprocessor){
        if(port < 0 || port > 65535){
            throw new InvalidParameterException();
        }else{
            this.port = port;
        }

        if(preprocessor != null) this.preprocessor = preprocessor;
    }

    public Preprocessor getPreprocessor(){
        return this.preprocessor;
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
            SocketTask clientTask = new SocketTask(this, socket);

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
                if(client != null)  client.close();
            }

            WebServer.getLogger().info("서버가 종료되었습니다.");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
