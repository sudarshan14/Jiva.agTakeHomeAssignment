package ag.jiva.mandi.ui

import ag.jiva.mandi.R
import ag.jiva.mandi.databinding.FragmentInfoSellProductBinding
import ag.jiva.mandi.domain.model.SellerInfo
import ag.jiva.mandi.domain.model.SellingPriceInfo
import ag.jiva.mandi.domain.viewmodels.MandiViewModel
import ag.jiva.mandi.util.Resource
import ag.jiva.mandi.util.printDebugLog
import ag.jiva.mandi.util.printErrorLog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class InfoSellProductFragment : Fragment() {
    private lateinit var binding: FragmentInfoSellProductBinding
    private val mainViewModel: MandiViewModel by viewModels()
    private val salesPriceInfoList = mutableListOf<SellingPriceInfo>()
    private val sellerInfoList = mutableListOf<SellerInfo>()
    private val villageNamesList = mutableListOf("Select Village")
    private val sellerNamesList = mutableListOf("")
    private val sellerLoyaltyIndexList = mutableListOf<String>()
    private lateinit var sellingPriceInfoAdapter: ArrayAdapter<String>
    private lateinit var sellerNameAdapter: ArrayAdapter<String>
    private lateinit var sellerLoyaltyIndexAdapter: ArrayAdapter<String>
    private var loyaltyIndex: Float = 0.0f
    private var sellingPrice: Float = 0.0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInfoSellProductBinding.inflate(inflater, container, false)

        // setting and selecting seller name and based on the seller setting loyalty card identifier value
        sellerNameAdapter =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                sellerNamesList
            )

        binding.editTextSellerName.threshold = 1
        binding.editTextSellerName.setAdapter(sellerNameAdapter)
        binding.editTextSellerName.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val selectedSellerName = sellerNameAdapter.getItem(position)
                val filteredNameList = sellerInfoList.filter { it.sellerName == selectedSellerName }
                binding.editTextLoyaltyCardIdentifier.setText(filteredNameList[0].loyaltyCardId)
                loyaltyIndex = filteredNameList[0].loyaltyIndex
                binding.textViewAppliedLoyaltyIndex.apply {
                    visibility = View.VISIBLE
                    text =
                        resources.getString(R.string.applied_loyalty_index, loyaltyIndex)
                }
            }

        // setting and selecting loyalty card identifier and based on the value setting seller name


        sellerLoyaltyIndexAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            sellerLoyaltyIndexList
        )

        binding.editTextLoyaltyCardIdentifier.apply {
            threshold = 1
            setAdapter(sellerLoyaltyIndexAdapter)
            onItemClickListener =
                OnItemClickListener { parent, view, position, id ->
                    val selectedLoyaltyCardId = sellerLoyaltyIndexAdapter.getItem(position)
                    val filteredLoyaltyCardIdList =
                        sellerInfoList.filter { it.loyaltyCardId == selectedLoyaltyCardId }

                    binding.editTextSellerName.setText(filteredLoyaltyCardIdList[0].sellerName)
                    loyaltyIndex = filteredLoyaltyCardIdList[0].loyaltyIndex
                    binding.textViewAppliedLoyaltyIndex.text =
                        resources.getString(R.string.applied_loyalty_index, loyaltyIndex)
                }
        }

        // setting village and price per kg adapter
        sellingPriceInfoAdapter =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                villageNamesList
            )

        binding.spinnerVillageSalesPriceInfo.apply {
            adapter = sellingPriceInfoAdapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        if (sellerInfoList.isNotEmpty()) {
                            val villageName = sellingPriceInfoAdapter.getItem(position)
//                        loyaltyIndex = sellerInfoList[position].loyaltyIndex
                            sellingPrice =
                                salesPriceInfoList.filter { it.villageName == villageName }
                                    .map { it.sellingPrice }[0]

                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }


        binding.editTextGrossWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //     val sellingPrice = binding.spinnerVillageSalesPriceInfo.
                if (s != null && s.isNotBlank()) {
                    val quantity = s.toString().toFloat()
                    mainViewModel.calculateGrossPrice(quantity, loyaltyIndex, sellingPrice)
                } else {
                    binding.textViewGrossPrice.text = resources.getString(R.string.gross_price, 0)
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


        binding.buttonSellMyProduct.setOnClickListener {

            val sellerName = binding.editTextSellerName.text.toString()
            val grossAmount = binding.textViewGrossPrice.text.toString()
            val grossWeight = binding.editTextGrossWeight.text.toString()
            val bundle = bundleOf(
                SELLER_NAME to sellerName,
                GROSS_PRICE to grossAmount,
                GROSS_WEIGHT to grossWeight
            )
            findNavController().navigate(
                R.id.action_infoSellProductFragment_to_thankYouFragment,
                bundle
            )
        }

        observe()

        return binding.root
    }


    private fun observe() {
        lifecycleScope.launch {
            mainViewModel.salesPriceInfo.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is Resource.Success -> {
                            printDebugLog("success sales price ${it.data}")
                            it.data?.let { list ->
                                salesPriceInfoList.addAll(list)
                                villageNamesList.clear()
                                villageNamesList.add("")
                                villageNamesList.addAll(list.map { it.villageName })
                                sellingPriceInfoAdapter.notifyDataSetChanged()
                            }

                        }
                        is Resource.Loading -> {
                            printDebugLog("loading sales price ${it.data}")
                        }
                        is Resource.Failure -> {
                            printDebugLog("failure sales price ${it.data}")
                        }
                    }
                }
        }
        lifecycleScope.launch {
            mainViewModel.sellerInfo.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { it ->
                    when (it) {
                        is Resource.Loading -> {
                            printDebugLog("loading sellerInfo ${it.data}")
                        }
                        is Resource.Success -> {

                            it.data?.let {
                                if (it.isNotEmpty()) {
                                    sellerInfoList.clear()
                                    sellerInfoList.addAll(it)

                                    sellerNamesList.addAll(it.map { it.sellerName })
                                    sellerNameAdapter.notifyDataSetChanged()

                                    sellerLoyaltyIndexList.addAll(it.map { it.loyaltyCardId })
                                    sellerLoyaltyIndexAdapter.notifyDataSetChanged()

                                }
                                printDebugLog("success collect ${it.size}")
                            }

                        }
                        is Resource.Failure -> {
                            it.message?.let { it1 -> printErrorLog("failure $it1") }
                        }
                    }
                }


        }

        lifecycleScope.launch {
            mainViewModel.grossPrice.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    binding.textViewGrossPrice.text = resources.getString(R.string.gross_price, it)
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    companion object {
        const val SELLER_NAME = "seller-name"
        const val GROSS_PRICE = "gross-price"
        const val GROSS_WEIGHT = "gross-weight"
    }
}