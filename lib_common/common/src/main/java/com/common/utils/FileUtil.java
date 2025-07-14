package com.common.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


public class FileUtil {


    //------------------------------------------获取assets目录下的图片-----------------------------------------------

    /**
     * 获取assets目录下的图片
     *
     * @param context   context
     * @param directory 目录
     * @param fileName  文件名
     */
    public static Bitmap getAssetImage(Context context, String directory, String fileName) {
        try {
            String path;
            if (StringUtil.isTrimEmpty(directory)) {
                path = String.format("%s", StringUtil.valueOf(fileName));
            } else {
                path = String.format("%s/%s", directory, StringUtil.valueOf(fileName));
            }
            InputStream inputStream = context.getAssets().open(path);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    //------------------------------------------获取assets目录下的图片-----------------------------------------------

    //------------------------------------------根据URI获取本地文件绝对路径-----------------------------------------------

    /**
     * @param context 上下文对象
     * @param uri     当前相册照片的Uri
     * @return 解析后的Uri对应的String
     */
    public static String getPath(final Context context, final Uri uri) {

        // 1. DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                DocumentsContract.isDocumentUri(context, uri)) {
            // 1.1 ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return getPathHead(false) + Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // 1.2 DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.
                        withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getPathHead(false) + getDataColumn(context,
                        contentUri, null, null);
            }
            // 1.3 MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getPathHead(false) + getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // 2. MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {//判断是否是google相册图片
                return uri.getLastPathSegment();
            } else if (isGooglePlayPhotosUri(uri)) {//判断是否是Google相册图片
                return getImageUrlWithAuthority(context, uri);
            } else {//其他类似于media这样的图片，和android4.4以下获取图片path方法类似
                return getFilePath_below19(context, uri);
            }
        }
        // 3. 判断是否是文件形式 File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return getPathHead(false) + uri.getPath();
        }
        return null;
    }

    private static String getPathHead(boolean isNeedPathHead) {
        String pathHead = "file:///";
        if (isNeedPathHead) {
            return pathHead;
        } else {
            return "/";
        }
    }

    /**
     * 获取小于api19时获取相册中图片真正的uri
     * 对于路径是：content://media/external/images/media/33517这种的，需要转成/storage/emulated/0/DCIM/Camera/IMG_20160807_133403.jpg路径，也是使用这种方法
     *
     * @param context 上下文
     * @param uri     uri
     */
    private static String getFilePath_below19(Context context, Uri uri) {
        //这里开始的第二部分，获取图片的路径：低版本的是没问题的，但是sdk>19会获取不到
        Cursor cursor = null;
        String path = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            //好像是android多媒体数据库的封装接口，具体的看Android文档
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            //获得用户选择的图片的索引值
            int column_index;
            if (cursor != null) {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                //将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                //最后根据索引值获取图片路径   结果类似：/mnt/sdcard/DCIM/Camera/IMG_20151124_013332.jpg
                path = cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        final String column = "_data";
        final String[] projection = {column};
        try (Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                null)) {
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 判断是否是Google相册的图片，类似于content://com.google.android.apps.photos.content/...
     **/
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 判断是否是Google相册的图片，类似于content://com.google.android.apps.photos.contentprovider/0/1/mediakey:/local%3A821abd2f-9f8c-4931-bbe9-a975d1f5fabc/ORIGINAL/NONE/1075342619
     **/
    private static boolean isGooglePlayPhotosUri(Uri uri) {
        return "com.google.android.apps.photos.contentprovider".equals(uri.getAuthority());
    }

    /**
     * Google相册图片获取路径
     **/
    private static String getImageUrlWithAuthority(Context context, Uri uri) {
        InputStream is = null;
        if (uri.getAuthority() != null) {
            try {
                is = context.getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);
                return writeToTempImageAndGetPathUri(context, bmp).toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将图片流读取出来保存到手机本地相册中
     **/
    private static Uri writeToTempImageAndGetPathUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //------------------------------------------根据URI获取本地文件绝对路径-----------------------------------------------

    //------------------------------------------无文件后缀判断文件格式-----------------------------------------------

    /**
     * 判断文件格式
     *
     * @param filePath 文件路径
     */
    public static String getFileType(String filePath) {
        return getFileTypes().get(getFileHeader(filePath));
    }

    private static Map<String, String> getFileTypes() {
        HashMap<String, String> fileTypes = new HashMap<>();
        //images
        fileTypes.put("FFD8FF", "jpg");

        fileTypes.put("89504E47", "png");

        fileTypes.put("47494638", "gif");

        fileTypes.put("49492A00", "tif");

        fileTypes.put("424D", "bmp");

        //

        fileTypes.put("41433130", "dwg"); //CAD

        fileTypes.put("38425053", "psd");

        fileTypes.put("7B5C727466", "rtf"); //日记本

        fileTypes.put("3C3F786D6C", "xml");

        fileTypes.put("68746D6C3E", "html");

        fileTypes.put("44656C69766572792D646174653A", "eml"); //邮件

        fileTypes.put("D0CF11E0", "doc");

        fileTypes.put("5374616E64617264204A", "mdb");

        fileTypes.put("252150532D41646F6265", "ps");

        fileTypes.put("255044462D312E", "pdf");

        fileTypes.put("504B0304", "zip");

        fileTypes.put("52617221", "rar");

        fileTypes.put("57415645", "wav");

        fileTypes.put("41564920", "avi");

        fileTypes.put("2E524D46", "rm");

        fileTypes.put("000001BA", "mpg");

        fileTypes.put("000001B3", "mpg");

        fileTypes.put("6D6F6F76", "mov");

        fileTypes.put("3026B2758E66CF11", "asf");

        fileTypes.put("4D546864", "mid");

        fileTypes.put("1F8B08", "gz");

        fileTypes.put("", "");

        return fileTypes;
    }

    //获取文件头信息

    private static String getFileHeader(String filePath) {

        FileInputStream is = null;

        String value = null;

        try {

            is = new FileInputStream(filePath);

            byte[] b = new byte[3];
            int read = is.read(b, 0, b.length);

            value = bytesToHexString(b);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (null != is) {

                try {
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        return value;

    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }

        String hv;

        for (byte b : src) {

            hv = Integer.toHexString(b & 0xFF).toUpperCase();

            if (hv.length() < 2) {

                builder.append(0);

            }

            builder.append(hv);

        }

        return builder.toString();

    }
    //------------------------------------------判断文件格式-----------------------------------------------

    /*public static void writeFileSDcard(ResponseBody responseBody, File file, ProgressListener downloadListener) {
        if (downloadListener != null) {
            downloadListener.onProgress(0, responseBody.contentLength(), false);
        }
//        KLog.d("writeFileSDcard");
        long currentLength = 0;
        OutputStream os = null;
        InputStream is = responseBody.byteStream();
        long totalLength = responseBody.contentLength();
//        KLog.d("totalLength=" + totalLength);
        try {
            os = new FileOutputStream(file);
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                currentLength += len;
//                KLog.d("当前长度: " + currentLength);
                int progress = (int) (currentLength / totalLength) * 100;
//                KLog.d("当前进度: " + progress);
                if (downloadListener != null) {
                    downloadListener.onProgress(progress, responseBody.contentLength(), false);
                }
            }
            if (downloadListener != null) {
                downloadListener.onFinish(file.getAbsolutePath());
            }
        } catch (FileNotFoundException e) {
//            KLog.d("Exception=" + e.getMessage());
            if (downloadListener != null) {
                downloadListener.onFailure("未找到文件！");
            }
            e.printStackTrace();
        } catch (IOException e) {
//            KLog.d("Exception=" + e.getMessage());
            if (downloadListener != null) {
                downloadListener.onFailure("IO错误！");
            }
            e.printStackTrace();
        } catch (Exception e){
            if (downloadListener != null){
                downloadListener.onFailure("出现异常");
            }
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }*/

    /**
     * 判断PDF文件是否存在 父目录不存在则新建
     *
     * @param file 文件名
     * @return 创建结果
     */
    public static boolean checkFile(File file) throws IOException {
        boolean fileExist = true;

        boolean parentFileExist = true;
        if (!file.getParentFile().exists()) {//DCIM/Camera目录不存在，新建
            parentFileExist = file.getParentFile().mkdirs();
        }

        if (parentFileExist && !file.exists()) {
            fileExist = file.createNewFile();
        }
        return fileExist;
    }

    /**
     * 在同级目录下创建临时文件
     *
     * @param resultPath 路径
     * @return 临时文件
     */
    public static File createTempFile(String resultPath) throws IOException {
        File file = new File(resultPath);
        String tempFileName = "temp_" + file.getName();
        File tempFile = new File(file.getParent(), tempFileName);
        if (checkFile(tempFile)) {
            return tempFile;
        }
        return null;
    }


    /*public static void writeFile(String fileName, ResponseBody responseBody,
                                 ContentResolver contentResolver) throws IOException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
//        contentValues.put(MediaStore.Downloads.MIME_TYPE,"application/pdf");
//        contentValues.put(MediaStore.Files.FileColumns.DISPLAY_NAME,fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            KLog.e("---->=android Q");
            //RELATIVE_PATH 字段表示相对路径-------->(1)
            contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);
//            contentValues.put(MediaStore.Files.FileColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES);
        } else {
            KLog.e("----<android Q");
            String dstPath = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS
                    + File.separator + fileName;
            //DATA字段在Android 10.0 之后已经废弃
            contentValues.put(MediaStore.Downloads.DATA, dstPath);
//            contentValues.put(MediaStore.Files.FileColumns.DATA, dstPath);
        }
        //插入相册------->(2)
//        Uri contentUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Uri externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Uri contentUri1 = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL);
        KLog.e("externalContentUri="+externalContentUri+"\n"+
                "contentUri1="+contentUri1);
        Uri contentUri = contentResolver.insert(contentUri1, contentValues);
        KLog.e("contentUri:"+contentUri.toString());

        InputStream inputStream = responseBody.byteStream();
        OutputStream outputStream = contentResolver.openOutputStream(contentUri);
        int len;
        byte[] buff = new byte[1024];
        while ((len = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
        }
        outputStream.flush();
        outputStream.close();


        KLog.e("pdf文件写入成功"+fileName);
    }*/
}


