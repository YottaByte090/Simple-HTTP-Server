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

package com.yottabyte090.httpserver.router;

import com.yottabyte090.httpserver.file.DirectoryManager;
import com.yottabyte090.httpserver.request.Request;
import com.yottabyte090.httpserver.response.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-08-11
 */

public class DefaultRouter extends RouterBase {
    @Override
    public Response process(Request request){
        return new Response();
    }

    @Override
    public byte[] getResource(String url) throws IOException {
        File file = new File(getPathByUrl(url));

        if(file.exists()){
            byte[] data = new byte[/*file.length()*/0];
            FileInputStream fiStream = new FileInputStream(file);
            fiStream.read(data);
            fiStream.close();

            return data;
        }else{
            throw new IOException("File Not Found " + file.getCanonicalPath());
        }
    }

    @Override
    public String getMIME(String url) throws IOException {
        return Files.probeContentType(Paths.get(getPathByUrl(url)));
    }

    @Override
    public String getPathByUrl(String url) {
        if(url.equals("/")){
            return DirectoryManager.getRootDir() + "/index.html";
        }else{
            return DirectoryManager.getRootDir() + url;
        }
    }
}