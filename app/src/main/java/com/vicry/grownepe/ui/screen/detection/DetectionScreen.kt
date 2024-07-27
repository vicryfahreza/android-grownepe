package com.vicry.grownepe.ui.screen.detection

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicry.grownepe.utils.TensorfLowLiteHelper.ClassifyImage

private var imageSize = 224


@Composable
fun DetectionScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
                contentDescription = "Image from the gallery",
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

            val scaledBitmap = Bitmap.createScaledBitmap(it, imageSize, imageSize, false);
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
                    Text(text = "Image is classified as:", color = Color.Yellow, fontSize = 16.sp)
                    Text(text = it, color = Color.White, fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier
            .width(10.dp)
            .height(50.dp)
        )

        Button(onClick = {
            launcher.launch("image/*")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Deteksi Jenis Nepenthes")
        }
    }
}

