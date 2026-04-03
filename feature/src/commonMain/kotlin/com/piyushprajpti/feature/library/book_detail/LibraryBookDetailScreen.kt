package com.piyushprajpti.feature.library.book_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LibraryBookDetailScreen(
    onBackClick: () -> Unit,
    viewModel: LibraryBookDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Book Details"
        )

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        state.book?.let { book ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                item {
                    BookHeroSection(book)
                }

                item {
                    BookInfoSection(book)
                }

                item {
                    AvailabilitySection(book)
                }

                item {
                    DescriptionSection(book)
                }

                item {
                    ReserveButtonSection {
                        viewModel.onEvent(LibraryBookDetailEvent.ReserveBook)
                    }
                }

                item {
                    RelatedBooksSection(state.relatedBooks)
                }
                
                item {
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }
        }
    }
}

@Composable
fun BookHeroSection(book: BookDetail) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = book.coverImage,
            contentDescription = null,
            modifier = Modifier
                .height(250.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BookInfoSection(book: BookDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = book.title,
            color = CustomTheme.colors.primaryText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.authors.joinToString(", "),
            color = CustomTheme.colors.primary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Chip(text = book.publisher)
            Chip(text = book.year)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "ISBN: ${book.isbn}",
                color = CustomTheme.colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun AvailabilitySection(book: BookDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CustomTheme.colors.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${book.totalCopies} Copies Available",
                color = CustomTheme.colors.primaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${book.issuedCopies} Currently Issued",
                color = CustomTheme.colors.secondaryText,
                fontSize = 12.sp
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(CustomTheme.colors.success.copy(alpha = 0.1f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = if (book.isAvailable) "IN STOCK" else "OUT OF STOCK",
                color = CustomTheme.colors.success,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DescriptionSection(book: BookDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = "ABOUT THE BOOK",
            color = CustomTheme.colors.secondaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.description,
            color = CustomTheme.colors.secondaryText,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun ReserveButtonSection(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(50.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomTheme.colors.primary
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Reserve Book",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white
            )
        }
    }
}

@Composable
fun RelatedBooksSection(books: List<BookSummary>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "RELATED BOOKS",
                color = CustomTheme.colors.secondaryText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "View All",
                color = CustomTheme.colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(books) { book ->
                RelatedBookCard(book)
            }
        }
    }
}

@Composable
fun RelatedBookCard(book: BookSummary) {
    Column(modifier = Modifier.width(110.dp)) {
        AsyncImage(
            model = book.coverImage,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.title,
            color = CustomTheme.colors.primaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            lineHeight = 16.sp
        )
    }
}

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.outline, CircleShape)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = CustomTheme.colors.secondaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
