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

import com.yottabyte090.httpserver.method.Get;
import com.yottabyte090.httpserver.method.Post;
import com.yottabyte090.httpserver.request.Request;
import com.yottabyte090.httpserver.request.RequestParser;
import com.yottabyte090.httpserver.response.Response;
import com.yottabyte090.httpserver.response.ResponseCode;
import com.yottabyte090.httpserver.utils.FileLoader;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-11
 */

public class SocketTask extends Thread {
    private HttpServer httpServer;
    private Socket socket;

    public SocketTask(HttpServer httpServer, Socket socket){
        this.httpServer = httpServer;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            BufferedReader inputBuf = new BufferedReader(input);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String line = inputBuf.readLine();
            StringBuffer requestStr = new StringBuffer();

            while(!line.isEmpty()){
                requestStr.append(line + "\r\n");
                line = inputBuf.readLine();
            }

            Request request = RequestParser.parse(requestStr.toString());
            Response response = null;

            Application.getLogger().info(request.getMethod() + " Method, Request \'" + request.getUri() + "\' From " + socket.getInetAddress().toString().replaceAll("/", ""));

            switch(request.getMethod()){
                case "GET":
                    Get get = new Get(request);
                    response = get.getResponse();

                    if(get.getFile() != null){
                        if(get.getFile().length() <= (long) Application.getConfig().getValue("Preprocessor.MaxSize")){
                            byte[] header = get.getResponse().setVersion(get.getRequest().getVersion())
                                    .setStatus(200)
                                    .toString()
                                    .getBytes();
                            byte[] body = httpServer.getPreprocessor().process(FileLoader.readAsString(get.getFile())).getBytes();

                            byte[] data = new byte[header.length + body.length];
                            System.arraycopy(header, 0, data, 0, header.length);
                            System.arraycopy(body, 0, data, header.length, body.length);

                            output.write(data);
                            output.flush();
                        }else if(get.getFile().length() <= (long) Application.getConfig().getValue("Server.BlockSize")){
                            byte[] header = get.getResponse().setVersion(get.getRequest().getVersion())
                                    .setStatus(200)
                                    .toString()
                                    .getBytes();
                            byte[] body = FileLoader.readAsByteArray(get.getFile());

                            byte[] data = new byte[header.length + body.length];
                            System.arraycopy(header, 0, data, 0, header.length);
                            System.arraycopy(body, 0, data, header.length, body.length);

                            output.write(data);
                            output.flush();
                        }else{
                            byte[] header = get.getResponse().setVersion(get.getRequest().getVersion())
                                    .setStatus(200)
                                    .toString()
                                    .getBytes();
                            output.write(header);
                            output.flush();

                            long fileSize = get.getFile().length();
                            long blockSize = (long) Application.getConfig().getValue("Server.BlockSize");
                            long blockCount = fileSize / blockSize;
                            long extraSize = fileSize % blockSize;

                            System.out.printf("fileSize : %d\nblockSize : %d\nblockCount : %d\nextraSize : %d\n", fileSize, blockSize, blockCount, extraSize);

                            FileInputStream fis = new FileInputStream(get.getFile());

                            for(int i=0; i<blockCount; i++){
                                byte[] data = new byte[(int) blockSize ];
                                System.out.println(i);

                                fis.read(data, 0, (int) blockSize);
                                output.write(data);
                                output.flush();

                            }

                            if(extraSize != 0){
                                byte[] extraData = new byte[(int) extraSize];

                                fis.read(extraData, 0, (int) extraSize);
                                output.write(extraData);
                                output.flush();
                            }
                        }
                    }else{
                        socket.getOutputStream().write(ResponseCode.getMessage(404).getBytes());
                    }

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

        }catch(Exception e){
            try {
                socket.getOutputStream().write(ResponseCode.getMessage(500).getBytes("UTF-8"));
                e.printStackTrace();
            }catch(Exception ex){
                ex.printStackTrace();
            }
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
