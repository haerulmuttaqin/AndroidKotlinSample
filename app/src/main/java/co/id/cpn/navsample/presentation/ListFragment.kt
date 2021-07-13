package co.id.cpn.navsample.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import co.id.cpn.navsample.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by activityViewModels()
    
    private val inventoryAdapter: InventoryAdapter by lazy { 
        InventoryAdapter(
            onItemClicked = {
                Log.i("ListFragment", " ${it.itemName}")
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailFragment(it.id)
                )
            }
        )
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToEditorFragment(0))
        }
        inventoryViewModel.inventoryItems.observe(viewLifecycleOwner, { items ->
            items.let {
                inventoryAdapter.submitList(it)
            }
        })
        binding.apply { 
            recyclerView.adapter = inventoryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}