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

import com.sangwon.httpserver.request.Request;
import com.sangwon.httpserver.request.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-11
 */

public class SocketTask extends Thread {
    private Socket socket;

    public SocketTask(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            BufferedReader inputBuf = new BufferedReader(input);

            OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());

            String line = inputBuf.readLine();
            StringBuffer requestStr = new StringBuffer();

            while(!line.isEmpty()){
                requestStr.append(line + "\r\n");
                line = inputBuf.readLine();
            }

            Request request = RequestParser.parse(requestStr.toString());

            switch(request.getMethod()){
                case "GET":

                    break;
                case "POST":

                    break;
                case "PUT":

                    break;
                case "HEAD":

                    break;
                case "DELETE":

                    break;
                case "OPTIONS":

                    break;
                case "TRACE":

                    break;
                case "CONNECT":

                    break;
                case "LINK":

                    break;
                case "UNLINK":

                    break;
                default:

                    break;
            }

            socket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n<!DOCTYPE html><html><head></head><body><h1>Hello, World!</h1></body></html>".getBytes("UTF-8"));
            socket.close();


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                this.socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void close(){
        try{
            this.socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
