package com.vicry.grownepe.ui.screen.detection

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicry.grownepe.R
import com.vicry.grownepe.ui.screen.article.ArticleScreen
import com.vicry.grownepe.ui.theme.GrowNepeTheme
import com.vicry.grownepe.utils.TensorfLowLiteHelper.ClassifyImage

private var imageSize = 160

@Composable
fun DetectionScreen(
    modifier: Modifier = Modifier,) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier
                .padding(10.dp)
                .height(5.dp)
            )
            ImagePicker()
        }
    }

}

@Composable
fun ImagePicker() {
    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            photoUri = it
        }
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            photoUri?.let {
                if (Build.VERSION.SDK_INT < 28)
                    bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap = ImageDecoder.decodeBitmap(
                        source,
                        ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
                            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                            decoder.isMutableRequired = true
                        })
                }
            }
        }


        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Pilih Gambar Dari Galeri",
                Modifier
                    .size(400.dp)
                    .padding(vertical = 70.dp)
            )
            Row {
                Spacer(modifier = Modifier
                    .padding(20.dp)
                    .height(15.dp)
                    .weight(1f)
                )
            }

            val scaledBitmap = Bitmap.createScaledBitmap(it, imageSize, imageSize, false)
            ClassifyImage(scaledBitmap) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier
                        .padding(10.dp)
                        .height(5.dp)
                    )
                    Text(text = "Spesies Kantong Semar Yang Terdeteksi: ", fontSize = 16.sp)
                    Text(text = it, fontSize = 24.sp)

                    Spacer(modifier = Modifier
                        .padding(10.dp)
                        .height(200.dp)
                    )
                    TextDetection(it)
                }
            }
        }

        Spacer(modifier = Modifier
            .width(10.dp)
            .height(50.dp)
        )

        Row(
            modifier = Modifier.width(400.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                launcher.launch("image/*")
            }, modifier = Modifier
                .padding(10.dp)) {
                Text(text = "Image Detection")
            }
            RoboflowDetection()
        }
    }
}

@Composable
fun RoboflowDetection(){
    val url = "nepenthes-detection/1?publishable_key="
    val publishableKey = "rf_IiCPIQrNIKVCWdtJasxc5BJL9qr1"
    val ctx = LocalContext.current

    Button(onClick = {
        Log.e("tag","URL IS "+url)
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://demo.roboflow.com/$url/$publishableKey")
        )
        ctx.startActivity(urlIntent)
    }, modifier = Modifier
        .padding(10.dp)) {
        Text(text = "Live Detection")
    }
}

@Composable
fun TextDetection(detect: String, modifier: Modifier = Modifier){
    when (detect) {
        "acak" -> {
            Text(text = stringResource(R.string.acak_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "alata" -> {
            Text(text = stringResource(R.string.alata_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.alata_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "ampullaria" -> {
            Text(text = stringResource(R.string.ampullaria_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.ampullaria_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "clipeata" -> {
            Text(text = stringResource(R.string.clipeata_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.clipeata_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "gracilis" -> {
            Text(text = stringResource(R.string.gracilis_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.gracilis_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "merilliana" -> {
            Text(text = stringResource(R.string.merillian_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.merillian_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "rafflesiana" -> {
            Text(text = stringResource(R.string.rafflesiana_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.rafflesiana_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "veitchii" -> {
            Text(text = stringResource(R.string.veitchii_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.veitchii_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }
        "ventricosa" -> {
            Text(text = stringResource(R.string.ventricosa_description), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
            Text(text = stringResource(R.string.ventricosa_soil), fontSize = 16.sp, textAlign = TextAlign.Justify, modifier = modifier.padding(10.dp))
        }

    }
}

@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    GrowNepeTheme {
        DetectionScreen()
    }
}



