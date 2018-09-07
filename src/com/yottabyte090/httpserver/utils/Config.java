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

import com.yottabyte090.httpserver.file.ResourceManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-28
 */

public class Config {
    private JSONObject json;

    public Config(File configFile) throws FileNotFoundException, ParseException, IOException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(configFile));
        json = (JSONObject) obj;
    }

    public static File createNewConfig(File file) throws IOException, InvalidParameterException {
        file.createNewFile();
        ResourceManager.resourceToFile("/Config.json", file);

        return file;
    }

    public Object getValue(String key) {
        String subKeys[] = key.split("\\.");
        JSONObject targetObject = this.json;

        for(int i=0; i<subKeys.length - 1; i++){
            targetObject = (JSONObject) json.get(subKeys[i]);
        }

        return targetObject.get(subKeys[subKeys.length - 1]);
    }
}
