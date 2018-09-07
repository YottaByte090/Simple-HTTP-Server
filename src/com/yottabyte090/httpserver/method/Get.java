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

package com.yottabyte090.httpserver.method;

import com.yottabyte090.httpserver.request.Request;
import com.yottabyte090.httpserver.response.Response;
import com.yottabyte090.httpserver.response.ResponseCode;
import com.yottabyte090.httpserver.utils.FileLoader;

import java.io.*;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-08
 */

public class Get extends Method {
    private File file;

    public Get(Request request) {
        super(request);

        try{
            this.file = FileLoader.getFile(request.getUri());
        }catch(FileNotFoundException e){
            response.setStatus(404);
        }
    }

    @Override
    public Response getResponse() {
        return this.response;
    }

    public File getFile(){
        if(this.file != null) return this.file;
        return null;
    }
}
