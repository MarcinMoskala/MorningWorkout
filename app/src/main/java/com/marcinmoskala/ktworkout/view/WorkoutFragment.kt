package com.marcinmoskala.ktworkout.view


import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.view.*
import com.marcinmoskala.ktworkout.*
import com.marcinmoskala.ktworkout.presentation.*
import org.koin.android.ext.android.*
import org.koin.core.parameter.*

class WorkoutFragment : Fragment() {
    private val speaker by inject<Speaker>()
    private val exercises by lazy { arguments?.getParcelableArrayList<Exercise>(ARG_EXERCISES).orEmpty() }
    private val viewModel by inject<WorkoutViewModel> { parametersOf(exercises) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(activity!!, R.layout.fragment_workout)
            .apply { setVariable(BR.vm, viewModel) }

        if (savedInstanceState == null) {
            viewModel.onStart()
        }
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