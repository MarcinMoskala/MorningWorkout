package com.marcinmoskala.ktworkout.presentation

import com.marcinmoskala.ktworkout.*
import io.mockk.*
import org.junit.*
import org.junit.Assert.*

class WorkoutViewModelTest {

    val someExercise = Exercise("A", R.drawable.wrist_stretch_2, "Name")

    @Before
    fun setUp() {
    }

    @Test
    fun singleExerciseTimerTest() {
        val exerciseName = "Name"
        val exercise = someExercise.copy(nameText = exerciseName, time = 4, prepareTime = 2)
        val timer = FakeTimer()
        val speaker: Speaker = mockk(relaxed = true)
        val vm = WorkoutViewModel(mockk(), listOf(exercise), timer, speaker)

        vm.onStart() // Now we should be at the break before exercise
        assertEquals("Prepare for Name", vm.title.get())
        assertEquals("2", vm.timeDisplay.get())
        assertEquals(0, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onTick(1)
        assertEquals("Prepare for Name", vm.title.get())
        assertEquals("1", vm.timeDisplay.get())
        assertEquals(50, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get() )
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onTick(0)
        assertEquals("Prepare for Name", vm.title.get())
        assertEquals("0", vm.timeDisplay.get())
        assertEquals(100, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onFinish()
        assertEquals("Name", vm.title.get())
        assertEquals("4", vm.timeDisplay.get())
        assertEquals(0, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onTick(3)
        assertEquals("Name", vm.title.get())
        assertEquals("3", vm.timeDisplay.get())
        assertEquals(25, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onTick(2)
        assertEquals("Name", vm.title.get())
        assertEquals("2", vm.timeDisplay.get())
        assertEquals(50, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onTick(1)
        assertEquals("Name", vm.title.get())
        assertEquals("1", vm.timeDisplay.get())
        assertEquals(75, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onTick(0)
        assertEquals("Name", vm.title.get())
        assertEquals("0", vm.timeDisplay.get())
        assertEquals(100, vm.progressBarPercentage.get())
        assertTrue(vm.progressVisible.get())
        assertEquals(someExercise.imgId, vm.image.get())
        assertEquals("1/1", vm.exercisesCounterDisplay.get())

        timer.onFinish()
        assertEquals("Done", vm.title.get())
        assertFalse(vm.progressVisible.get())
        assertNotEquals(someExercise.imgId, vm.image.get())
        assertEquals("", vm.exercisesCounterDisplay.get())
    }

    @Test
    fun sidesTest() {
        val exerciseName = "Name"
        val exercise = someExercise.copy(nameText = exerciseName, sides = listOf("A", "B", "C"), switchSidesTime = 333)
        val timer = FakeTimer()
        val speaker: Speaker = mockk(relaxed = true)
        val vm = WorkoutViewModel(mockk(), listOf(exercise), timer, speaker)

        vm.onStart()
        assertEquals("Prepare for Name A", vm.title.get())

        vm.onNext()
        assertEquals("Name A", vm.title.get())

        vm.onNext()
        assertEquals("Prepare for Name B", vm.title.get())
        assertEquals("333", vm.timeDisplay.get())

        vm.onNext()
        assertEquals("Name B", vm.title.get())

        vm.onNext()
        assertEquals("Prepare for Name C", vm.title.get())
        assertEquals("333", vm.timeDisplay.get())

        vm.onNext()
        assertEquals("Name C", vm.title.get())
    }

    @Test
    fun `Exercises counter is displayed correctly both during exercise and during preparation`() {
        val exercise = someExercise.copy(nameText = "E1")
        val exercise2 = someExercise.copy(nameText = "E2")
        val exercise3 = someExercise.copy(nameText = "E3")
        val exercise4 = someExercise.copy(nameText = "E4")
        val timer = FakeTimer()
        val speaker: Speaker = mockk(relaxed = true)
        val vm = WorkoutViewModel(mockk(), listOf(exercise, exercise2, exercise3, exercise4), timer, speaker)

        vm.onStart()
        assertEquals("1/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("E1", vm.title.get())
        assertEquals("1/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("2/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("E2", vm.title.get())
        assertEquals("2/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("3/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("E3", vm.title.get())
        assertEquals("3/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("4/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("E4", vm.title.get())
        assertEquals("4/4", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("", vm.exercisesCounterDisplay.get())
    }

    @Test
    fun `Exercises counter has the same value for all sides`() {
        val exercise = someExercise.copy(nameText = "E1", sides = listOf("A", "B", "C"))
        val exercise2 = someExercise.copy(nameText = "E2")
        val timer = FakeTimer()
        val speaker: Speaker = mockk(relaxed = true)
        val vm = WorkoutViewModel(mockk(), listOf(exercise, exercise2), timer, speaker)

        vm.onStart()
        assertTrue("A" in vm.title.get().orEmpty())
        assertEquals("1/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertTrue("A" in vm.title.get().orEmpty())
        assertEquals("1/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertTrue("B" in vm.title.get().orEmpty())
        assertEquals("1/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertTrue("B" in vm.title.get().orEmpty())
        assertEquals("1/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertTrue("C" in vm.title.get().orEmpty())
        assertEquals("1/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertTrue("C" in vm.title.get().orEmpty())
        assertEquals("1/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertTrue("E2" in vm.title.get().orEmpty())
        assertEquals("2/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("E2", vm.title.get())
        assertEquals("2/2", vm.exercisesCounterDisplay.get())

        vm.onNext()
        assertEquals("", vm.exercisesCounterDisplay.get())
    }

    class FakeTimer(): Timer {
        var seconds = 0
        var onTick: (Int) -> Unit = {}
        var onFinish: () -> Unit = {}

        override fun start(seconds: Int, onTick: (Int) -> Unit, onFinish: () -> Unit) {
            this.seconds = seconds
            this.onTick = onTick
            this.onFinish = onFinish
        }

        override fun stop() {
            // no-op
        }

        override fun start() {
            // no-op
        }
    }
}