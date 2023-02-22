package ag.jiva.mandi.domain.viewmodels

import ag.jiva.mandi.domain.model.SellerInfo
import ag.jiva.mandi.domain.model.SellingPriceInfo
import ag.jiva.mandi.domain.usecase.CalculateGrossPrice
import ag.jiva.mandi.domain.usecase.GetSellerInfo
import ag.jiva.mandi.domain.usecase.GetSellerLoyaltyCardId
import ag.jiva.mandi.domain.usecase.GetSellingPrice
import ag.jiva.mandi.util.Resource
import ag.jiva.mandi.util.printDebugLog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MandiViewModel @Inject constructor(
    private val getSellerInfo: GetSellerInfo,
    private val getGrossPrice: CalculateGrossPrice,
    private val getSellingPrice: GetSellingPrice,
) : ViewModel() {

    private val _sellerInfo: MutableStateFlow<Resource<List<SellerInfo>>> =
        MutableStateFlow(Resource.Loading())
    val sellerInfo: StateFlow<Resource<List<SellerInfo>>> = _sellerInfo

    private val _salesPriceInfo: MutableStateFlow<Resource<List<SellingPriceInfo>>> =
        MutableStateFlow(Resource.Loading())
    val salesPriceInfo: StateFlow<Resource<List<SellingPriceInfo>>> = _salesPriceInfo

    private val _grossPrice: MutableStateFlow<Float> = MutableStateFlow(0.0f)
    val grossPrice: StateFlow<Float> = _grossPrice

    init {
        getVillageSellingPrice()
        getAllSellersInfo()
    }


    private fun getVillageSellingPrice() =
        viewModelScope.launch {
            delay(500L)
            getSellingPrice.getVillageSellingPrice()
                .flowOn(Dispatchers.Main)
                .catch {
                    _salesPriceInfo.emit(
                        Resource.Failure(
                            it.message.toString(),
                            emptyList()
                        )
                    )
                }
                .collect {
//                    _salesPriceInfo.emit(it)
                    _salesPriceInfo.value = it
                }
        }

    private var searchJob: Job? = null


    fun getSellersInfo(id: String, sellerName: String) {
        searchJob?.cancel()
        viewModelScope.launch {
            delay(500L)
            getSellerInfo(id, sellerName).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        printDebugLog("success emit in viewmodel")
                        //  _sellerInfo.emit(result)
                        _sellerInfo.value = result
                    }
                    is Resource.Failure -> {}
                    is Resource.Loading -> {}
                }
            }.launchIn(this)
        }
    }


    private fun getAllSellersInfo() {

        viewModelScope.launch {
            delay(500L)
            getSellerInfo.getAllSellerInfo()
                .catch { _sellerInfo.emit(Resource.Failure(it.message.toString(), emptyList())) }
                .collect {
                    _sellerInfo.emit(it)
                }
        }

    }

    fun calculateGrossPrice(quantity: Float, loyaltyIndex: Float, sellingPrice: Float) {
        viewModelScope.launch {
            getGrossPrice.calculateGrossPrice(quantity, loyaltyIndex, sellingPrice)
                .collect {
                    _grossPrice.emit(it)
                }
        }
    }

}

