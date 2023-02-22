package ag.jiva.mandi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ag.jiva.mandi.R
import ag.jiva.mandi.databinding.FragmentInfoSellProductBinding
import ag.jiva.mandi.databinding.FragmentThankYouBinding
import androidx.navigation.fragment.findNavController

class ThankYouFragment : Fragment() {
    private lateinit var binding: FragmentThankYouBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThankYouBinding.inflate(inflater, container, false)


        val sellerName = arguments?.getString(InfoSellProductFragment.SELLER_NAME)
        val grossPrice = arguments?.getString(InfoSellProductFragment.GROSS_PRICE)
        val grossWeight = arguments?.getString(InfoSellProductFragment.GROSS_WEIGHT)


        binding.apply {

            textViewThankYouSeller.text = resources.getString(R.string.thank_you_seller, sellerName)
            textViewEnsureToCollectAmount.text =
                resources.getString(R.string.ensure_to_collect_amount, grossPrice, grossWeight)


            buttonSellMyProduct.setOnClickListener {
                findNavController().navigate(R.id.action_thankYouFragment_to_infoSellProductFragment)
            }
        }

        return binding.root
    }


}