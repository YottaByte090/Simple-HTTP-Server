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

package com.yottabyte090.httpserver.response;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-11
 */

public class Response {
    private String version;
    private int statusCode;
    private String statusMessage;
    private LinkedHashMap<String, String> fields = new LinkedHashMap<String, String>();
    private String body;

    public String getVersion(){
        return this.version;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public int getStatus(){
        return this.statusCode;
    }

    public void setStatus(int code){
        this.statusCode = code;
        this.statusMessage = ResponseCode.getMessage(code);
    }

    public LinkedHashMap<String, String> getFields() {
        return this.fields;
    }

    public void setField(String key, String value){
        this.fields.put(key, value);
    }

    public void removeField(String key){
        this.fields.remove(key);
    }

    public String getBody(){
        return this.body;
    }

    public void setBody(String body){
        this.body = body;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();

        buffer.append(this.version);
        buffer.append(' ' + this.statusCode);
        buffer.append(' ' + this.statusMessage);

        Iterator<String> keys = fields.keySet().iterator();

        while (keys.hasNext()) {
            String key = keys.next();
            String value = fields.get(key);
            buffer.append("\r\n" + key + ": " + value);
        }

        if(this.body != null) buffer.append("\r\n\r\n" + body);

        return buffer.toString();
    }
}
