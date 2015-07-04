package me.aktor.quicknote.app.services.network;

import android.util.Pair;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okio.ByteString;

/**
 * Created by Andrea Tortorella on 6/27/15.
 */
public class WebServices {

    public static final String APPCODE= "1234567890";
    public static final String ADDRESS = "10.0.3.2";//"10.0.2.0";
    public static final int PORT = 9000;
    private static final String APP_HEADER = "X-BAASBOX-APPCODE";
    private static final String SESSION = "X-BB-SESSION";
    private static final String NOTES = "notes";


    private static final MediaType JSON= MediaType.parse("application/json");


    private static volatile OkHttpClient sClient;
    private static final Object sLock = new Object();

    private static OkHttpClient client(){
        if (sClient == null) {
            synchronized (sLock) {
                if (sClient == null){
                    sClient = new OkHttpClient();
                    sClient.networkInterceptors().add(new AppcodeInterceptor());
                }
            }
        }
        return sClient;
    }

    private static class AppcodeInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            builder.addHeader(APP_HEADER,APPCODE);
            return chain.proceed(builder.build());
        }
    }

    public static JSONObject fetchNotes(String token){
        Request request = new Request.Builder()
                .get()
                .url(composeUrl("document", NOTES))
                .addHeader(SESSION,token)
                .build();
        try {
            Response resp = client().newCall(request).execute();
            return responseJson(resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject responseJson(Response resp) throws IOException, JSONException {
        if (resp.code() /100 == 2){
            String s = resp.body().string();
            return new JSONObject(s);
        }
        throw error(resp);
    }

    public static JSONObject login(String username,String password){
        try {
            JSONObject o = new JSONObject();

            o.put("username",username);
            o.put("password",password);
            o.put("appcode",APPCODE);
            FormEncodingBuilder form = new FormEncodingBuilder();
            form.add("username",username)
            .add("password",password)
            .add("appcode",APPCODE);

            Request req = new Request.Builder()

                    .post(form.build())
                    .url(composeUrl("login"))
                    .build();

            Response resp = client().newCall(req).execute();
            return responseJson(resp);
        } catch (JSONException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject createDocument(String token,String title,String content){
        try {
            JSONObject object = new JSONObject();

            object.put("title", title);
            object.put("content", content);
            Request request = new Request.Builder()
                    .url(composeUrl("document", NOTES))
                    .addHeader(SESSION, token)
                    .post(jsonBody(object))
                    .build();
            Response resp = client().newCall(request).execute();
            return responseJson(resp);
        } catch (JSONException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static JSONObject signup(String username,String password){
        try {
            JSONObject req = new JSONObject();
            req.put("username", username);
            req.put("password", password);
            Request request = new Request.Builder()
                        .post(jsonBody(req))
                        .url(composeUrl("user")).build();
            Response resp =client().newCall(request).execute();
            int code = resp.code();
            return responseJson(resp);
        } catch (JSONException e) {
           throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static RequestBody jsonBody(JSONObject object){
        String jsonString = object.toString();
        ByteString utf = ByteString.encodeUtf8(jsonString);
        return RequestBody.create(JSON,utf);
    }

    private static HttpUrl composeUrl(){
        return composeUrl(null,null);
    }

    private static HttpUrl composeUrl(String ... params){
        if (params == null || params.length==0){
            return composeUrl(null,null);
        } else {
            return composeUrl(Arrays.asList(params),null);
        }
    }


    private static RuntimeException error(Response response){
        String url = response.request().urlString();
        return new RuntimeException(String.format("%s ==> %d %s",url,response.code(),response.message()));
    }

    private static HttpUrl composeUrl(List<String> segments,List<Pair<String,String>> queryParams){
       HttpUrl.Builder builder = new HttpUrl.Builder();
       builder.scheme("http")
                .host(ADDRESS)
                .port(PORT);
       if (segments != null){
           for (String segment : segments){
               builder.addPathSegment(segment);
           }
       }
        if (queryParams != null){
            for (Pair<String,String> query:queryParams){
                builder.addQueryParameter(query.first,query.second);
            }
        }
        return builder.build();
    }

}
