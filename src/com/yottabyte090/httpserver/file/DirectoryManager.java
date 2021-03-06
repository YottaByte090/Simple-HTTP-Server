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

package com.yottabyte090.httpserver.file;

import java.io.File;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-28
 */

public class DirectoryManager {
    private static final String homeDir = System.getProperty("user.dir") + '/';

    public static String getDirectory(String dirName){
        File directory = new File(homeDir + '/' + dirName);
        if(!directory.exists()) directory.mkdirs();

        return homeDir + '/'+ dirName;
    }

    public static String getHomeDir(){
        return homeDir;
    }

    public static String getRootDir(){
        return getDirectory("root/");
    }

    public static String getLogDir(){
        return getDirectory("logs/");
    }

    public static String getRouterDir(){
        return getDirectory("routers/");
    }
}
