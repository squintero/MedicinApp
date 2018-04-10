package com.squintero.medicinapp.utilities;


public class Volley {

//    public static final String TAG = Volley.class.getSimpleName();
//
//    public static int SOCKET_TIMEOUT  = 15000;  //15 seconds
//
//    private static Volley mInstance;
//    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;
//    private static Context mContext;
//
//    private Volley(Context context) {
//        mContext = context;
//        mRequestQueue = getRequestQueue();
//
//        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
//
//            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
//
//            @Override
//            public Bitmap getBitmap(String url) {
//                return cache.get(url);
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//                cache.put(url, bitmap);
//            }
//        });
//    }
//
//    public static synchronized Volley getInstance(Context context) {
//
//        if (mInstance == null) {
//            mInstance = new Volley(context);
//        }
//        return mInstance;
//    }
//
////    public RequestQueue volleyGetRequestQueue() {
////        if (mRequestQueue == null) {
//////            mRequestQueue = Volley.newRequestQueue(AppCommon.getInstance().getApplicationContext(), new OkHttpStack());
////            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
////        }
////
////        return mRequestQueue;
////    }
//
//    public RequestQueue getRequestQueue() {
//
//        if (mRequestQueue == null) {
//            // getApplicationContext() is key, it keeps you from leaking the
//            // ActivityEdit or BroadcastReceiver if someone passes one in.
////            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext(), new OkHttpStack());
////            mRequestQueue = newRequestQueue(mContext.getApplicationContext(), new OkHttpStack());
//            mRequestQueue = newRequestQueue(mContext.getApplicationContext(), new HurlStack(null, ClientSSLSocketFactory.createSSLSocketFactory()));
//        }
//        return mRequestQueue;
//    }
//
//    public void loadImageWithVolleyAuthorized(String imageId, final ProgressBar progressBar,
//                                              final ImageView image, int maxWidth, int maxHeight,
//                                              final int defaultImage, final boolean showDefault)
//    {
//
//        if (imageId == null || image == null)
//            return;
//
//        ImageRequest mImageRequest;
//        RequestQueue mRequestQueue;
//        String url = BuildConfig.BASE_URL_API + "api/media/" + imageId;
//
//        mRequestQueue = Volley.getInstance(App.getInstance().getApplicationContext()).getRequestQueue();
//        mImageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap bitmap) {
//                if (progressBar != null)
//                    progressBar.setVisibility(View.GONE);   // hide the spinner here
//
//                if (bitmap != null) {
//                    image.setVisibility(View.VISIBLE);          // set the image here
//                    image.setImageBitmap(bitmap);
//                }
//
//                Logs.MessageLogs(TAG, "loadImageWithVolleyAuthorized", "i");
//            }
//
//        }, maxWidth, maxHeight, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                if (progressBar != null)
//                    progressBar.setVisibility(View.GONE);   // hide the spinner here
//
//                if (showDefault) {
//                    image.setVisibility(View.VISIBLE);          // set the image here
//                    image.setImageDrawable(ContextCompat.getDrawable(mContext, defaultImage));
//                }
//                Logs.MessageLogs(TAG, "loadImageWithVolleyAuthorized: " + error.getMessage(), "e");
//            }
//
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                String token = SharedPreferencesManager.getInstance().getStringValue(Constants.SHARED_LOGIN_ACCESS_TOKEN);
//                HashMap<String, String> headers = new HashMap<>();
////                headers.put("Accept", "application/json");
//                headers.put("Authorization", "Bearer " + token);
//
//                return headers;
//            }
//        };
//
//        mRequestQueue.add(mImageRequest);
//    }
//
//    public void loadImageWithVolley(String imageUrl, final ProgressBar progressBar,
//                                    final ImageView image, int maxWidth, int maxHeight,
//                                    final int defaultImage, final boolean showDefault)
//    {
//        if (imageUrl == null || image == null)
//            return;
//
//        ImageLoader imageLoader = getImageLoader();
//        String url = BuildConfig.BASE_URL_API + imageUrl;
//
//        imageLoader.get(url, new ImageLoader.ImageListener() {
//            @Override
//            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//
//                //Skip cache failure
////                    if (isImmediate && response.getBitmap() == null) return;
//
//                if (progressBar != null)
//                    progressBar.setVisibility(View.GONE);   // hide the spinner here
//
//                image.setVisibility(View.VISIBLE);          // set the image here
//                image.setImageBitmap(response.getBitmap());
//                Logs.MessageLogs(TAG, "loadImageWithVolleyAuthorized", "i");
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                if (progressBar != null)
//                    progressBar.setVisibility(View.GONE);   // hide the spinner here
//
//                if (showDefault) {
//                    image.setVisibility(View.VISIBLE);          // set the image here
//                    image.setImageDrawable(ContextCompat.getDrawable(mContext, defaultImage));
//                }
//                Logs.MessageLogs(TAG, "loadImageWithVolleyAuthorized: " + error.getMessage(), "e");
//            }
//        }, maxWidth, maxHeight);
//    }
//
//    public void volleySetDefaultPolicyRetry(StringRequest request, String tagRequest) {
//
//        RetryPolicy policy = new DefaultRetryPolicy(SOCKET_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        request.setRetryPolicy(policy);
//
//        //Set the default tag if tag is empty
//        request.setTag(TextUtils.isEmpty(tagRequest) ? TAG : tagRequest);
//        getRequestQueue().add(request);
//    }
//
//    public <T> void addToRequestQueue(Request<T> req, String tag) {
//
//        // set the default tag if tag is empty
////        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
////        getRequestQueue().add(req);
//
//        RetryPolicy policy = new DefaultRetryPolicy(SOCKET_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        req.setRetryPolicy(policy);
//
//        // set the default tag if tag is empty
//        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
//        getRequestQueue().add(req);
//    }
//
//    public <T> void addToRequestQueue(Request<T> req) {
//        getRequestQueue().add(req);
//    }
//
//    public void cancelPendingRequests(Object tag) {
//
//        if (mRequestQueue != null) {
//            mRequestQueue.cancelAll(tag);
//        }
//    }
//
//    public ImageLoader getImageLoader() {
//
//        getRequestQueue();
//
//        if (mImageLoader == null) {
//            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
//        }
//        return this.mImageLoader;
//    }
}