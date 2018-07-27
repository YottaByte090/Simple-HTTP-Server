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

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-27
 */

public abstract class LoggerBase {
    public abstract void log(String message);

    public void log(String prefix, String message){
        log(String.format("[ %s ] %s", prefix, message));
    }

    public void log(LogLevel level, String message){
        switch(level){
            case INFO:
                info(message);
                break;
            case NOTICE:
                notice(message);
                break;
            case WARN:
                warn(message);
                break;
            case ERROR:
                error(message);
                break;
            case FATAL:
                fatal(message);
                break;
        }
    }

    public void info(String message){
        log("INFO", message);
    }

    public void notice(String message){
        log("NOTICE", message);
    }

    public void warn(String message){
        log("WARN", message);
    }

    public void error(String message){
        log("ERROR", message);
    }

    public void fatal(String message){
        log("FATAL", message);
    }
}
