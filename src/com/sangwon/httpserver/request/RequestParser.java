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

package com.sangwon.httpserver.request;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-08
 */

public class RequestParser {
    public static Request parse(String request){
        Request req = new Request();
        String[] reqLines = request.split("\r\n");
        String[] startLine = reqLines[0].split(" ");

        req.setMethod(startLine[0]);
        req.setUri(startLine[1]);
        if(startLine.length >= 3) req.setVersion(startLine[2]);

        for(int i=1; i<reqLines.length; i++){
            if(reqLines[i].equals("")){
                StringBuffer buffer = new StringBuffer();
                for(int j = i + 1; j < reqLines.length; j++){
                    buffer.append(reqLines[j]);
                    if(j != reqLines.length - 1) buffer.append("\r\n");
                }
                req.setBody(buffer.toString());
                break;
            }else{
                req.setField(reqLines[i].split(": ")[0], reqLines[i].split(": ")[1]);
            }
        }

        return req;
    }
}
