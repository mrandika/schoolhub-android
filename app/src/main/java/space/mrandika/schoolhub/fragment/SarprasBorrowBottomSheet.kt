package space.mrandika.schoolhub.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncorti.slidetoact.SlideToActView

import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.Presence.PresencePresenter
import space.mrandika.schoolhub.network.Api

/**
 * A simple [Fragment] subclass.
 */
class SarprasBorrowBottomSheet : BottomSheetDialogFragment() {

    internal lateinit var view: View
    private lateinit var borrow_confirmation: SlideToActView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_presence_bottom_sheet, container, false)

        borrow_confirmation = view.findViewById(R.id.borrow_confirmation)

        borrow_confirmation.onSlideCompleteListener = object: SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                val code = arguments?.getString("code")?.replace("PRS=", "")
                val sharedPreference = activity?.getSharedPreferences("token", Context.MODE_PRIVATE)
                val token = sharedPreference?.getString("token", null)
//                presencePresenter = PresencePresenter(context!!, this@SarprasBorrowBottomSheet, Api)
//                presencePresenter.postPresence(token!!, code!!)
            }
        }

        return view
    }


}
