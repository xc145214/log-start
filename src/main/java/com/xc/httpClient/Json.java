/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.xc.httpClient;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.util.JSONTokener;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  httpClient请求页面并返回JSON格式数据
 *
 *  @author xiachuan at 2016/9/2 16:58。
 */

public class Json {

    public static void main(String[] args) throws JSONException {

        Map<String,String> map = new HashMap<String,String>();
        map.put("action","query");
        map.put("name","zhangshan");
        map.put("idNo","123");

        JSONObject json = new JSONObject();
        json.put("Name", "123");
        json.put("Pass", "123");
        json.put("data",map);
        System.out.println(URLEncoder.encode(json.toString()));
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Name", "123"));
        params.add(new BasicNameValuePair("Pass", "123"));
        //要传递的参数.
        String url = "http://www.----aspx?" + URLEncodedUtils.format(params, HTTP.UTF_8);
        //拼接路径字符串将参数包含进去
        System.out.println(url);

    }


}

