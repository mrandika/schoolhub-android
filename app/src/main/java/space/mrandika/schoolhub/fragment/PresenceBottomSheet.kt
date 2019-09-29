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
import space.mrandika.schoolhub.logic.Presence.PresenceView
import space.mrandika.schoolhub.network.Api

/**
 * A simple [Fragment] subclass.
 */
class PresenceBottomSheet : BottomSheetDialogFragment(), PresenceView {

    override fun showLoading() {
        //
    }

    override fun hideLoading() {
        //
    }

    internal lateinit var view: View
    private lateinit var presence_confirmation: SlideToActView

    private lateinit var presencePresenter: PresencePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_presence_bottom_sheet, container, false)

        presence_confirmation = view.findViewById(R.id.presence_confirmation)

        presence_confirmation.onSlideCompleteListener = object: SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                val code = arguments?.getString("code")?.replace("PRS=", "")
                presencePresenter = PresencePresenter(context!!, this@PresenceBottomSheet, Api)
                presencePresenter.postPresence(code!!)
            }
        }

        return view
    }


}
