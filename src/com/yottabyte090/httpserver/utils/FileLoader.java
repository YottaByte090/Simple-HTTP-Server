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

package com.yottabyte090.httpserver.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-26
 */

public class FileLoader{
    public static File getFile(String filePath) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "/root" + filePath);

        if(filePath.equals("/")){
            file = new File(System.getProperty("user.dir") + "/root/index.html");
        }

        if(file.exists()){
            return file;
        }else{
            throw new FileNotFoundException();
        }
    }

    public static String readAsString(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuffer buffer = new StringBuffer();
        String currentLine;

        while((currentLine = reader.readLine()) != null){
            buffer.append(currentLine + System.getProperty("line.separator"));
        }

        return buffer.toString();
    }

    public static byte[] readAsByteArray(File file) throws IOException {
        byte[] byteArray = Files.readAllBytes(Paths.get(file.getCanonicalPath()));
        for(byte b : byteArray){
            System.out.write(b);
        }
        return byteArray;
    }
}
