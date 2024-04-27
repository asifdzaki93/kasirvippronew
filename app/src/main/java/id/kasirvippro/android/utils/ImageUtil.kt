package id.kasirvippro.android.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import android.provider.MediaStore



object ImageUtil {

    //    https://stackoverflow.com/questions/36189503/take-picture-and-convert-to-base64
    private fun convertUriToBitmap(data: Intent, context: Context): Bitmap {
        val imageUri = data.data as Uri
        val imageStream = context.contentResolver.openInputStream(imageUri) as InputStream
        return BitmapFactory.decodeStream(imageStream)
    }

    fun encodeToBase64Str(data: Intent, context: Context): String {
        return encodeToBase64Str(
                convertUriToBitmap(data, context)
        )
    }

    fun encodeToBase64Str(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val bytes = baos.toByteArray()
        return android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
    }

    fun encodeToBase64Str(filePath: String): String {
        val bytes = File(filePath).readBytes()
        val base64 = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
        return base64
    }

    fun decodeFromBase64Str(base64Str: String, pathFile: String): Unit {
        val data = android.util.Base64.decode(base64Str, android.util.Base64.DEFAULT)
        File(pathFile).writeBytes(data)
    }

    fun getSizeBitmap(bitmapOrg: Bitmap): Int {
        val stream = ByteArrayOutputStream()
        bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val imageInByte = stream.toByteArray()
        val lengthbmp = imageInByte.size.toLong()
        return (lengthbmp / 1024).toInt()
    }

    fun getSizeFile(stringPath: String): Int {
        val file = File(stringPath)
        val length = file.length() / 1024 // Size in KB
        return length.toInt()
    }

    fun getSizeBitmap(data: Intent, context: Context): Int {
        val bitmap = convertUriToBitmap(data, context)
        return getSizeBitmap(bitmap)
    }

    // Method to compress a bitmap
    fun compressBitmap(bitmap:Bitmap, quality:Int):Bitmap{
        // Initialize a new ByteArrayStream
        val stream = ByteArrayOutputStream()

        /*
            **** reference source developer.android.com ***

            public boolean compress (Bitmap.CompressFormat format, int quality, OutputStream stream)
                Write a compressed version of the bitmap to the specified outputstream.
                If this returns true, the bitmap can be reconstructed by passing a
                corresponding inputstream to BitmapFactory.decodeStream().

                Note: not all Formats support all bitmap configs directly, so it is possible
                that the returned bitmap from BitmapFactory could be in a different bitdepth,
                and/or may have lost per-pixel alpha (e.g. JPEG only supports opaque pixels).

                Parameters
                format : The format of the compressed image
                quality : Hint to the compressor, 0-100. 0 meaning compress for small size,
                    100 meaning compress for max quality. Some formats,
                    like PNG which is lossless, will ignore the quality setting
                stream: The outputstream to write the compressed data.

                Returns
                    true if successfully compressed to the specified stream.


            Bitmap.CompressFormat
                Specifies the known formats a bitmap can be compressed into.

                    Bitmap.CompressFormat  JPEG
                    Bitmap.CompressFormat  PNG
                    Bitmap.CompressFormat  WEBP
        */

        // Compress the bitmap with JPEG format and quality 50%
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)

        val byteArray = stream.toByteArray()

        // Finally, return the compressed bitmap
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun getImageUri(context: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, inImage, "IMG", null)
        return Uri.parse(path)
    }
}