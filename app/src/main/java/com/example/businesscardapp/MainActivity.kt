package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                BusniessCard()
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun FullNameBox(modifier: Modifier = Modifier, fullName: String = "Tran Trung Kien", title: String = "Developer") {
    val image = painterResource(R.drawable.android_logo)
    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier.background(color= Color(0xFF153844)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = null,
                Modifier.size(160.dp)
            )
        }
        Text(
            text = fullName,
            fontSize = 32.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 4.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF3ddc84)
        )
    }
}

@Composable
fun ContactInfoRow(image: ImageVector, info: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(start = 60.dp)
                .size(25.dp),
            tint = Color(0xFF3ddc84)
        )
        Text(
            text = info,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactInfoBox(modifier: Modifier = Modifier, phone: String = "+123456789", socialMedia: String = "@socialmedia", email: String = "email@domail.com") {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        ContactInfoRow(Icons.Default.Phone, phone)
        ContactInfoRow(Icons.Default.Share, socialMedia)
        ContactInfoRow(Icons.Default.Email, email)
    }
}

@Preview(showBackground = true)
@Composable
fun BusniessCard(modifier : Modifier = Modifier, fullName:String = "Tran Trung Kien", title:String = "Developer", phone: String = "+ 84 0877635710", socialMedia: String = "@TranTrungKien", email: String = "ttk08112005@gmail.com") {
    BusinessCardAppTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFD6E9D6)),
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            FullNameBox(modifier = Modifier.padding(top = 160.dp))
            ContactInfoBox(phone=phone, socialMedia=socialMedia, email=email, modifier = Modifier.padding(bottom = 40.dp))
        }
    }
}


