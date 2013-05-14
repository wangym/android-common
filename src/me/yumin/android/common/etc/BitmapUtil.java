/**
 * 
 */
package me.yumin.android.common.etc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * @author yumin
 * 
 */
public class BitmapUtil {

	/**
	 * 
	 */
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";
	private static final int DEFAULT_CONNECT_TIMEOUT = 5000;
	private static final int DEFAULT_READ_TIMEOUT = 6000;
	public static final float DEFAULT_IMAGE_SCALE_SIZE_X = 0.5F;
	public static final float DEFAULT_IMAGE_SCALE_SIZE_Y = 0.5F;
	public static final int DEFAULT_IMAGE_SCALE_QUALITY = 30;

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getBitmap(String url) {

		Bitmap bitmap = null;

		bitmap = getBitmap(url, DEFAULT_CHARSET_NAME, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);

		return bitmap;
	}

	/**
	 * 
	 * @param url
	 * @param charsetName
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static Bitmap getBitmap(String url, String charsetName, int connectTimeout, int readTimeout) {

		/* 定义变量 */
		Bitmap bitmap = null;
		HttpURLConnection httpConnection = null;
		InputStream inputStream = null;

		try {
			// 建立连接
			httpConnection = HttpUtil.getHttpURLConnection(url, charsetName, connectTimeout, readTimeout);
			httpConnection.connect();
			//　返回结果
			inputStream = httpConnection.getInputStream();
			if (null != httpConnection.getContentEncoding() && 0 == "gzip".compareTo(httpConnection.getContentEncoding())) {
				inputStream = new GZIPInputStream(inputStream);
			}
			inputStream = new BufferedInputStream(inputStream);
			bitmap = BitmapFactory.decodeStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* 释放变量 */
			try {
				if (null != inputStream) {
					inputStream.close();
					inputStream = null;
				}
				if (null != httpConnection) {
					httpConnection.disconnect();
					httpConnection = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bitmap;
	}

	/**
	 * 
	 * @param inputPath
	 * @param outputPath
	 * @return
	 */
	public static Bitmap resizeBitmap(String inputPath, String outputPath) {

		Bitmap resizeBitmap = null;

		resizeBitmap(inputPath, outputPath, DEFAULT_IMAGE_SCALE_SIZE_X, DEFAULT_IMAGE_SCALE_SIZE_Y, DEFAULT_IMAGE_SCALE_QUALITY);

		return resizeBitmap;
	}

	/**
	 * 
	 * @param inputPath
	 * @param outputPath
	 * @param scaleSizeX
	 * @param scaleSizeY
	 * @param scaleQuality
	 * @return
	 */
	public static Bitmap resizeBitmap(String inputPath, String outputPath, float scaleSizeX, float scaleSizeY, int scaleQuality) {

		Bitmap resizeBitmap = null;

		if (CommonUtil.isNotEmpty(inputPath) && CommonUtil.isNotEmpty(outputPath)) {
			Bitmap sourceBitmap = null;
			FileOutputStream outputStream = null;
			try {
				sourceBitmap = BitmapFactory.decodeFile(inputPath);
				if (null != sourceBitmap) {
					outputStream = new FileOutputStream(new File(outputPath));
					Matrix matrix = new Matrix();
					matrix.postScale(scaleSizeX, scaleSizeY);
					resizeBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight(), matrix, false);
					resizeBitmap.compress(Bitmap.CompressFormat.JPEG, scaleQuality, outputStream);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != outputStream) {
						outputStream.close();
						outputStream = null;
					}
					if (null != sourceBitmap && !sourceBitmap.isRecycled()) {
						// sourceBitmap.recycle();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return resizeBitmap;
	}
}
