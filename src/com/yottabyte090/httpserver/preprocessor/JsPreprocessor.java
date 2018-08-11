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

package com.yottabyte090.httpserver.preprocessor;

import com.yottabyte090.httpserver.method.Method;
import com.yottabyte090.httpserver.response.Response;
import org.mozilla.javascript.Context;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-29
 */

public class JsPreprocessor extends Preprocessor {
    private Context context = Context.getCurrentContext();

    public JsPreprocessor(Method method){
        super(method);
    }

    @Override
    public Response getResponse(Method method) {
        return null;
    }
}
