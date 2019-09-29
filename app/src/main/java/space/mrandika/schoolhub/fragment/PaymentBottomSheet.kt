package space.mrandika.schoolhub.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncorti.slidetoact.SlideToActView
import kotlinx.android.synthetic.main.fragment_payment_bottom_sheet.view.*

import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.Payment.PaymentPresenter
import space.mrandika.schoolhub.logic.Presence.PresencePresenter
import space.mrandika.schoolhub.network.Api

/**
 * A simple [Fragment] subclass.
 */
class PaymentBottomSheet : BottomSheetDialogFragment() {

    internal lateinit var view: View
    private lateinit var payment_confirmation: SlideToActView

    private lateinit var paymentPresenter: PaymentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment_bottom_sheet, container, false)

        payment_confirmation = view.payment_confirmation

        payment_confirmation.onSlideCompleteListener = object: SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                val code = arguments?.getString("code")?.replace("KTN=", "")
                paymentPresenter = PaymentPresenter(context!!, Api)
                paymentPresenter.payment(code!!)
            }
        }

        return view
    }


}
