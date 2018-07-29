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

package com.yottabyte090.httpserver.logger;

import java.util.ArrayList;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-27
 */

public class MultiLogger extends LoggerBase {
    private ArrayList<LoggerBase> loggers = new ArrayList<>();

    public MultiLogger() {

    }

    public MultiLogger(LoggerBase[] loggers){
        for(LoggerBase logger : loggers){
            this.loggers.add(logger);
        }
    }

    public void addLogger(LoggerBase logger){
        loggers.add(logger);
    }

    public void removeLogger(LoggerBase logger){
        loggers.remove(logger);
    }

    @Override
    public void log(String message){
        for(LoggerBase logger : loggers){
            logger.log(message);
        }
    }

    @Override
    public void info(String message){
        for(LoggerBase logger : loggers){
            logger.info(message);
        }
    }

    @Override
    public void notice(String message){
        for(LoggerBase logger : loggers){
            logger.notice(message);
        }
    }

    @Override
    public void warn(String message){
        for(LoggerBase logger : loggers){
            logger.warn(message);
        }
    }

    @Override
    public void error(String message){
        for(LoggerBase logger : loggers){
            logger.error(message);
        }
    }

    @Override
    public void fatal(String message){
        for(LoggerBase logger : loggers){
            logger.fatal(message);
        }
    }
}
