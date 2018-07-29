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
import com.yottabyte090.httpserver.file.FileManager;
import com.yottabyte090.httpserver.logger.*;
import com.yottabyte090.httpserver.utils.Config;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-08
 */

public class WebServer {
    private static MultiLogger logger = new MultiLogger();
    private static Config config;
    private static HttpServer server;

    public static void exception(Exception e){
        e.printStackTrace();
    }

    public static void main(String[] args){
        try{
            if(System.getProperty("os.name").toLowerCase().contains("win")){
                logger.addLogger(new ConsoleLogger());
                logger.addLogger(new FileLogger(FileManager.getLogFile(), "\r\n"));
            }else{
                logger.addLogger(new ColoredConsoleLogger());
                logger.addLogger(new FileLogger(FileManager.getLogFile(), "\n"));
            }

            config = new Config(FileManager.getConfigFile());;
            server = new HttpServer(80);

            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
            server.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static LoggerBase getLogger(){
        return logger;
    }
}