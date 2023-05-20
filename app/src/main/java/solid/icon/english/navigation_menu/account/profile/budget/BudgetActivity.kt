package solid.icon.english.navigation_menu.account.profile.budget

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.R
import solid.icon.english.architecture.DividerItemDecorator
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.BudgetActivityBinding
import solid.icon.english.navigation_menu.account.profile.budget.firebase.FirebaseOperation
import solid.icon.english.navigation_menu.account.profile.budget.firebase.HistoryModel
import solid.icon.english.navigation_menu.account.profile.budget.interfaces.OnGetListOfHistory

class BudgetActivity : ActivityGlobal() {

    private lateinit var binding: BudgetActivityBinding
    private val firebaseOperation = FirebaseOperation()
    private lateinit var adapter: HistoryAdapter
    var listUserInfo: MutableLiveData<MutableList<HistoryModel>> =
        MutableLiveData()
    private lateinit var budgetKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BudgetActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showActionBar(true, "Budget")

        budgetKey = intent.getStringExtra("budgetKey")!!

        lifecycleScope.launch {
            delay(800)
            withContext(Dispatchers.Main) {
                initUI()
                binding.linearLayout.animate().alpha(1f).duration = 500
            }
        }
    }

    private fun initUI() {
        getData()
        settingAdapter()
    }

    private fun getData() {
        firebaseOperation.getListOfHistory(budgetKey, object : OnGetListOfHistory {
            override fun onSuccess(list: MutableList<HistoryModel>) {
                listUserInfo.value = list.asReversed()
                binding.loadingLayout.root.isVisible = false
            }

            override fun onStart() {
                binding.loadingLayout.root.isVisible = true
            }
        })

        firebaseOperation.getPaidLessons(budgetKey, onGetPaidLessons = { paidLessons ->
            binding.paidLesson.text = paidLessons.toString()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun settingAdapter() {
        binding.recycler.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecorator(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.divider
                )!!
            )
        binding.recycler.addItemDecoration(dividerItemDecoration)

        adapter = HistoryAdapter(listOf())
        listUserInfo.observe(this) {
            adapter.listItems = it
            adapter.notifyDataSetChanged()
        }
        binding.recycler.adapter = adapter
    }
}