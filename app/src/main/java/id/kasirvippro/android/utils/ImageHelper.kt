package id.kasirvippro.android.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.IOException
import android.view.View
import android.widget.Toast
import android.provider.MediaStore
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import androidx.core.app.NotificationCompat
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.R
import java.io.FileNotFoundException
import java.io.FileOutputStream


/**
 * Created by adriyoutomo on 26/10/2018.
 */
object ImageHelper {

    private fun rotateImage(img: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedImg = Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
        img.recycle()
        return rotatedImg
    }

    /**
     * Calculate an inSampleSize for use in a [BitmapFactory.Options] object when decoding
     * bitmaps using the decode* methods from [BitmapFactory]. This implementation calculates
     * the closest inSampleSize that will result in the final decoded bitmap having a width and
     * height equal to or larger than the requested width and height. This implementation does not
     * ensure a power of 2 is returned for inSampleSize which can be faster when decoding but
     * results in a larger bitmap which isn't as useful for caching purposes.
     *
     * @param options   An options object with out* params already populated (run through a decode*
     * method with inJustDecodeBounds==true
     * @param reqWidth  The requested width of the resulting bitmap
     * @param reqHeight The requested height of the resulting bitmap
     * @return The value to be used for inSampleSize
     */
    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int, reqHeight: Int
    ): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            val totalPixels = (width * height).toFloat()

            // Anything more than 2x the requested pixels we'll sample down further
            val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++
            }
        }
        return inSampleSize
    }

    /**
     * https://www.samieltamawy.com/how-to-fix-the-camera-intent-rotated-image-in-android/
     * This method is responsible for solving the rotation issue if exist. Also scale the images to
     * 1024x1024 resolution
     *
     * @param context       The current context
     * @param selectedImage The Image URI
     * @return Bitmap image results
     * @throws IOException
     */
    @Throws(IOException::class)
    fun handleSamplingAndRotationBitmap(context: Context, selectedImage: Uri): Bitmap? {
        val MAX_HEIGHT = 1024
        val MAX_WIDTH = 1024

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var imageStream = context.contentResolver.openInputStream(selectedImage)
        BitmapFactory.decodeStream(imageStream, null, options)
        imageStream?.close()

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        imageStream = context.contentResolver.openInputStream(selectedImage)
        var img = BitmapFactory.decodeStream(imageStream, null, options)
        img = rotateImageIfRequired(img!!, selectedImage)
        imageStream?.close()
        return img
    }

    /**
     * Rotate an image if required.
     *
     * @param img           The image bitmap
     * @param selectedImage Image URI
     * @return The resulted Bitmap after manipulation
     */
    @Throws(IOException::class)
    fun rotateImageIfRequired(img: Bitmap, selectedImage: Uri): Bitmap {
        val ei = ExifInterface(selectedImage.path!!)
        val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> return rotateImage(img, 180)
            ExifInterface.ORIENTATION_ROTATE_180 -> return rotateImage(img, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> return rotateImage(img, 0)
            else -> return img
        }
    }

    fun getBitmap(file: File): Bitmap {
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun getCameraPhotoOrientation(context: Context, imageFile: File): Int {
        var rotate = 0
        try {
            context.contentResolver.notifyChange(Uri.fromFile(imageFile), null)

            val exif = ExifInterface(imageFile.absolutePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }

            Log.i("RotateImage", "Exif orientation: $orientation")
            Log.i("RotateImage", "Rotate value: $rotate")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rotate
    }

    fun takeScreenshot(context: Context, nestedScrollView:NestedScrollView) {

        //get screenshot scrollview
        if (nestedScrollView == null) {
            return
        }
        val totalHeight = nestedScrollView.getChildAt(0).height ?: 0
        val totalWidth = nestedScrollView.getChildAt(0).width ?: 0


        val mBitmap = getBitmapFromView(nestedScrollView, totalHeight, totalWidth)

        //Save bitmap
        if (mBitmap != null) {


            val direct = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/kasirvippro" )
            if (!direct.exists()) {
                val mkdir = direct.mkdir()
            }
            val extr = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val fileName = "kasirvippro/img_" + System.currentTimeMillis().toString() + ".jpg"
            val myPath = File(extr, fileName)
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(myPath)
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos!!.flush()
                fos!!.close()
                val path =
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), mBitmap, "kasirvippro", "kasirvippro")

                //share bitmap from uri
                Helper.shareBitmapToApps(context, Uri.parse(path))
                return
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
    }

    fun downloadScreenshot(context: Context, nestedScrollView:NestedScrollView) {

        //get screenshot scrollview
        if (nestedScrollView == null) {
            return
        }
        val totalHeight = nestedScrollView.getChildAt(0).height ?: 0
        val totalWidth = nestedScrollView.getChildAt(0).width ?: 0


        val mBitmap = getBitmapFromView(nestedScrollView, totalHeight, totalWidth)

        //Save bitmap
        if (mBitmap != null) {


            val direct = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/kasirvippro" )
            if (!direct.exists()) {
                val mkdir = direct.mkdir()
            }
            val extr = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val fileName = "kasirvippro/img_" + System.currentTimeMillis().toString() + ".jpg"
            val myPath = File(extr, fileName)
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(myPath)
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos!!.flush()
                fos!!.close()
                val path =
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), mBitmap, "kasirvippro", "kasirvippro")

                //download
                generateNotif(context,path, mBitmap)
                return
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
    }

    private fun getBitmapFromView(view: View, totalHeight: Int, totalWidth: Int): Bitmap? {
        return try {
            val returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(returnedBitmap)
            val bgDrawable = view.background
            if (bgDrawable != null)
                bgDrawable!!.draw(canvas)
            else
                canvas.drawColor(Color.WHITE)
            view.draw(canvas)
            returnedBitmap
        } catch (e: Exception) {
            null
        }

    }

    private fun generateNotif(context: Context, path: String, bitmap: Bitmap) {
        val notifyID = 1
        val CHANNEL_ID = "notif_receipt"
        val CHANNEL_NAME = "notif_receipt"
        val importance = NotificationManager.IMPORTANCE_HIGH

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(path))
        //        intent.setAction(android.content.Intent.ACTION_VIEW);
        //        intent.setDataAndType(Uri.fromFile(path), "images/*");
        val contentIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context)

        builder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Download complete")
            .setContentText("Downloaded successfully")
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
            .setContentIntent(contentIntent)
            .setContentInfo("Info")
            .setLargeIcon(bitmap)
            .setChannelId(CHANNEL_ID)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
            )


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            notificationManager.createNotificationChannel(mChannel)
        }
        notificationManager.notify(notifyID, builder.build())
        Toast.makeText(context, "Downloaded successfully", Toast.LENGTH_SHORT).show()
    }
}
