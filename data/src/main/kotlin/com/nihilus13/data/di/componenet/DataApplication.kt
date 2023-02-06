package com.nihilus13.data.di.componenet

import com.nihilus13.domain.repository.SearchRepository

interface DataApplication {
    val repository: SearchRepository
}
