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
import com.yottabyte090.httpserver.preprocessor.JsPreprocessor;
import com.yottabyte090.httpserver.preprocessor.DefaultPreprocessor;
import com.yottabyte090.httpserver.router.DefaultRouter;
import com.yottabyte090.httpserver.router.JsRouter;
import com.yottabyte090.httpserver.utils.Config;

import java.io.File;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-08
 */

public class Application {
    private static MultiLogger logger = new MultiLogger();
    private static Config config;
    private static HttpServer server;

    public static void exception(Exception e){
        e.printStackTrace();
    }

    public static void fatal(String reason){
        logger.fatal("서버를 종료합니다 : " + reason);
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

            String preprocessor = (String) config.getValue("Preprocessor.Preprocessor");

            switch(preprocessor){
                case "default":
                    server.setPreprocessor(new DefaultPreprocessor());
                    logger.info("기본 전처리기를 사용합니다.");
                    break;
                case "javascript":
                    server.setPreprocessor(new JsPreprocessor());
                    logger.info("JavaScript 전처리기를 사용합니다.");
                    break;
                default:
                    logger.warn("알 수 없는 전처리기 : " + preprocessor);
                    logger.warn("기본 전처리기를 사용합니다.");
                    server.setPreprocessor(new DefaultPreprocessor());
                    break;
            }

            String router = config.getValue("Router.Router").toString();

            switch(router){
                case "default":
                    server.setRouter(new DefaultRouter());
                    logger.info("기본 라우터를 사용합니다.");
                    break;
                default:
                    if(FileManager.getRouterFile(router) != null){
                        server.setRouter(new JsRouter(FileManager.getRouterFile(router)));
                        logger.info(String.format("JavasScript 라우터(%s)를 사용합니다.", router));
                    }else{
                        logger.warn("존재하지 않는 라우터 : " + router);
                        logger.warn("기본 라우터를 사용합니다.");
                        server.setRouter(new DefaultRouter());
                    }
                    break;
            }

            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
            server.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static LoggerBase getLogger(){
        return logger;
    }

    public static Config getConfig(){
        return config;
    }
}