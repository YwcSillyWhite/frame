package com.purewhite.ywc.purewhite.imageload.file;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;


/**
 * @author yuwenchao
 */
public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("you can not create object");
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFilePath(String filePath) {
        return TextUtils.isEmpty(filePath) ? null : new File(filePath);
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file) {
        /**
         * file.exists() 测试文件是否存在
         * true file.isDirectory()判断是否为文件目录
         *false file.mkdirs()判断创建目录是否成功
         */
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFilePath(dirPath));
    }





    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null)
            return false;
        /**
         * 如果文件存在，则判断是否是文件
         */
        if (file.exists())
            return file.isFile();
        /**
         * 判断文件更目录是不是目录，如果不是目录返回 false
         */
        if (!createOrExistsDir(file.getParentFile()))
            return false;
        /**
         * 如果是就创建新文件，返回创建是否成功，如果出现异常就返回false
         */
        try
        {
            return file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }




}
