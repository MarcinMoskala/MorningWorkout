package com.marcinmoskala.ktworkout.view


import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.view.*
import com.marcinmoskala.ktworkout.*
import com.marcinmoskala.ktworkout.presentation.*

class WorkoutFragment : Fragment() {

    val speaker by lazy { (activity!!.application as App).speaker }

    private val viewModel by lazy {
        WorkoutViewModel(activity!!.application,
            exercises = arguments?.getParcelableArrayList<Exercise>(ARG_EXERCISES).orEmpty(),
            timer = AndroidTimer(),
            speaker = speaker
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(activity!!, R.layout.fragment_workout)
            .apply { setVariable(BR.vm, viewModel) }
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onStop() {
        super.onStop();
        viewModel.onStop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        speaker.release()
    }

    companion object {
        private const val ARG_EXERCISES = "ARG_EXERCISES"

        @JvmStatic
        fun newInstance(exercises: List<Exercise>) =
            WorkoutFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_EXERCISES, ArrayList(exercises))
                }
            }
    }
}