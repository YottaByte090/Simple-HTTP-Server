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

import java.io.*;
import java.security.InvalidParameterException;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-28
 */

public class ResourceManager {
    public static void resourceToFile(String resourcePath, File file) throws IOException, InvalidParameterException {
        InputStream input =  ResourceManager.class.getResourceAsStream(resourcePath);
        OutputStream output = new FileOutputStream(file);

        if(input == null) throw new InvalidParameterException();

        try{
            byte[] buffer = new byte[1024];
            int length;

            while((length = input.read(buffer)) > 0){
                output.write(buffer, 0, length);
            }
        }catch(IOException | InvalidParameterException ex){
            throw ex;
        } finally{
            input.close();
            output.close();
        }
    }
}
