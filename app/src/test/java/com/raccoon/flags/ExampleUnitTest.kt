package com.raccoon.flags

import com.raccoon.flags.api.ApiService
import com.raccoon.flags.api.DataModel
import com.raccoon.flags.api.Translations
import com.raccoon.flags.model.BusinessModel
import com.raccoon.flags.repository.Repository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    private val apiService = mock(ApiService::class.java)
    private lateinit var repository: Repository
    private lateinit var model: BusinessModel
    private val ukraineModel: DataModel = DataModel(name = "Ukraine", alpha2Code = "UA", alpha3Code = "UKR", capital = "Kyiv", region = "Europe",
            subregion = "East", population = 42692393, demonym = "Ukrainian", area = 603700.0, gini = 26.4, nativeName = "Україна",
            numericCode = "804", translations = Translations("UA", "UA", "UA", "UA", "UA", "UA", "UA"), cioc = "UKR")
    private val notUkraineModel: DataModel = DataModel(name = "Other", alpha2Code = "OT", alpha3Code = "OTH", capital = "HaveNoIdea", region = "Sun",
            subregion = "Nowhere", population = 42692393, demonym = "Martian", area = 603700.0, gini = 26.4, nativeName = "lkjahsdfkh",
            numericCode = "804", translations = Translations("OT", "OT", "OT", "OT", "OT", "OT", "OT"), cioc = "OTH")

    @Before
    fun setUp() {
        `when`(apiService.countriesList()).thenReturn(Observable.just(listOf(notUkraineModel, ukraineModel)))
        repository = Repository(apiService, TestSchedulers)
        model = BusinessModel(repository)
    }

    @Test
    fun requestDataInRepoReturnList() {
        // Arrange
        val observer = TestObserver<List<DataModel>>()

        // Action
        repository.subject.subscribe(observer)

        // Assert
        observer.assertNoErrors()
                .assertValue(listOf(notUkraineModel, ukraineModel))
    }

    @Test
    fun requestDataInModelReturnList() {
        // Arrange
        val observer = TestObserver<List<DataModel>>()

        // Action
        model.dataList().subscribe(observer)

        // Assert
        observer.assertNoErrors()
                .assertValue(listOf(notUkraineModel, ukraineModel))
    }

    @Test
    fun requestItemInModelReturnItem() {
        // Arrange
        val observer = TestObserver<DataModel>()

        // Action
        model.singleItemData("UKR").subscribe(observer)

        // Assert
        observer.assertNoErrors()
                .assertValue(ukraineModel)
    }

    @Test
    fun requestItemInModelReturnNothing() {
        // Arrange
        val observer = TestObserver<DataModel>()

        // Action
        model.singleItemData("APP").subscribe(observer)

        // Assert
        observer.assertNoErrors()
                .assertNoValues()
    }

}
