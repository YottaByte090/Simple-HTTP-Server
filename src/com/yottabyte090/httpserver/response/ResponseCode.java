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

import java.util.TreeMap;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-12
 */

public class ResponseCode {
    public static TreeMap<Integer, String> messages = new TreeMap<>();

    static {
        //messages.put(100, "Continue");
        //messages.put(101, "Switching Protocols");
        //messages.put(102, "Processing");
        //messages.put(103, "Early Hints");
        messages.put(200, "OK");
        //messages.put(201, "Created");
        //messages.put(202, "Accepted");
        //messages.put(203, "Non-Authoritative Information");
        //messages.put(204, "No Content");
        //messages.put(205, "Reset Content");
        //messages.put(206, "Partial Content");
        //messages.put(207, "Multi-Status");
        //messages.put(208, "Already Reported");
        //messages.put(226, "IM Used");
        //messages.put(300, "Multiple Choices");
        //messages.put(301, "Moved Permanently");
        //messages.put(302, "Found");
        //messages.put(303, "See Other");
        //messages.put(304, "Not Modified");
        //messages.put(305, "Use Proxy");
        //messages.put(306, "Switch Proxy");
        //messages.put(307, "Temporary Redirect");
        //messages.put(308, "Permanent Redirect");
        //messages.put(400, "Bad Request");
        //messages.put(401, "Unauthorized");
        //messages.put(402, "Payment Required");
        //messages.put(403, "Forbidden");
        messages.put(404, "Not Found");
        //messages.put(405, "Method Not Allowed");
        //messages.put(406, "Not Acceptable");
        //messages.put(407, "Proxy Authentication Required");
        //messages.put(408, "Request Timeout");
        //messages.put(409, "Conflict");
        //messages.put(410, "Gone");
        //messages.put(411, "Length Required");
        //messages.put(412, "Precondition Failed");
        //messages.put(413, "Payload Too Large");
        //messages.put(414, "URI Too Long");
        //messages.put(415, "Unsupported Media Type");
        //messages.put(416, "Range Not Satisfiable");
        //messages.put(417, "Expectation Failed");
        //messages.put(418, "I'm a teapot");
        //messages.put(421, "Misdirected Request");
        //messages.put(422, "Unprocessable Entity");
        //messages.put(423, "Locked");
        //messages.put(424, "Failed Dependency");
        //messages.put(426, "Upgrade Required");
        //messages.put(428, "Precondition Required");
        //messages.put(429, "Too Many Requests");
        //messages.put(431, "Request Header Fields Too Large");
        //messages.put(451, "Unavailable For Legal Reasons");
        messages.put(500, "Internal Server Error");
        //messages.put(501, "Not Implemented");
        //messages.put(502, "Bad Gateway");
        //messages.put(503, "Service Unavailable");
        //messages.put(504, "Gateway Timeout");
        //messages.put(505, "HTTP Version Not Supported");
        //messages.put(506, "Variant Also Negotiates");
        //messages.put(507, "Insufficient Storage");
        //messages.put(508, "Loop Detected");
        //messages.put(510, "Not Extended");
        //messages.put(511, "Network Authentication Required");
    }

    public static String getMessage(int code){
        if(messages.get(code) == null){
            return "";
        }else{
            return messages.get(code);
        }
    }
}
