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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-27
 */

public class ColoredConsoleLogger extends LoggerBase {
    @Override
    public void log(String message){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(String.format("%s %s", dateFormat.format(new Date()), message));
    }

    public void log(String color, String message){
        log(color + message + ConsoleColor.RESET);
    }

    public void log(String color, String prefix, String message){
        log(String.format(color + "[ %s ] %s" + ConsoleColor.RESET, prefix, message));
    }

    @Override
    public void info(String message){
        log(ConsoleColor.FOREGROUND_WHITE, "INFO", message);
    }

    @Override
    public void notice(String message){
        log(ConsoleColor.FOREGROUND_BRIGHT_WHITE, "NOTICE", message);
    }

    @Override
    public void warn(String message){
        log(ConsoleColor.FOREGROUND_BRIGHT_YELLOW, "WARN", message);
    }

    @Override
    public void error(String message){
        log(ConsoleColor.FOREGROUND_BRIGHT_RED, "ERROR", message);
    }

    @Override
    public void fatal(String message){
        log(ConsoleColor.FOREGROUND_RED, "FATAL", message);
    }
}
