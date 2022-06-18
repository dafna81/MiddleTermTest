package cohen.dafna.middletermtest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cohen.dafna.middletermtest.Logger
import cohen.dafna.middletermtest.databinding.FragmentMainBinding
import cohen.dafna.middletermtest.adapters.CurrencyAdapter
import cohen.dafna.middletermtest.ui.flipCard

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.coinsLiveData.observe(viewLifecycleOwner) { coins ->
            val adapter = CurrencyAdapter(coins) { v1, v2 ->
                flipCard(requireContext(), v1, v2)
            }
            coins.forEach { coin ->
                adapter.notifyItemChanged(coins.indexOf(coin))
            }
            binding.recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.recyclerView.adapter = adapter
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}