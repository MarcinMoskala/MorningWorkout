package com.marcinmoskala.ktworkout.view

import android.content.*
import android.databinding.*
import android.os.*
import android.support.v7.app.*
import com.marcinmoskala.ktworkout.*
import com.marcinmoskala.ktworkout.presentation.*
import org.koin.android.ext.android.*
import org.koin.core.parameter.*

class WorkoutActivity : AppCompatActivity() {

    private val speaker by inject<Speaker>()
    private val exercises by lazy {
        intent.extras?.getParcelableArrayList(ARG_EXERCISES) ?: randomExercises
    }
    private val viewModel by inject<WorkoutViewModel> { parametersOf(exercises) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_workout)
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

    override fun onDestroy() {
        super.onDestroy()
        speaker.release()
    }

    companion object {
        private const val ARG_EXERCISES = "ARG_EXERCISES"

        @JvmStatic
        fun start(context: Context, exercises: List<Exercise>) {
            context.startActivity(Intent(context, WorkoutActivity::class.java).apply {
                putParcelableArrayListExtra(ARG_EXERCISES, ArrayList(exercises))
            })
        }
    }
}
