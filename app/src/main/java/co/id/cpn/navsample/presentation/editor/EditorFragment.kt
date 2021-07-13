package co.id.cpn.navsample.presentation.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.id.cpn.entity.InventoryItem

import co.id.cpn.navsample.databinding.FragmentEditorBinding
import co.id.cpn.navsample.presentation.InventoryViewModel
import co.id.cpn.navsample.presentation.InventoryViewModelFactory

class EditorFragment : Fragment() {

    private var _binding: FragmentEditorBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: EditorFragmentArgs by navArgs()
    lateinit var inventoryItem: InventoryItem

    /*private val inventoryViewModel: InventoryViewModel
        get() {
            TODO()
        }*/
     private val inventoryViewModel: InventoryViewModel by activityViewModels {
         InventoryViewModelFactory.getInstance(requireContext())
     }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = safeArgs.itemId
        
        if (id > 0) {
            inventoryViewModel.getInventory(id).observe(viewLifecycleOwner, { item ->
                inventoryItem = item
                bind(inventoryItem)
            })
        }
        else {
            binding.apply {
                saveAction.setOnClickListener {
                    inventoryViewModel.addNewItem(
                        binding.itemName.text.toString(),
                        binding.itemPrice.text.toString(),
                        binding.itemQuantity.text.toString(),
                    )
                    findNavController().navigate(EditorFragmentDirections.actionEditorFragmentToListFragment())
                }
            }
        }
    }

    private fun bind(inventory: InventoryItem) {
        binding.apply { 
            itemName.setText(inventory.itemName, TextView.BufferType.SPANNABLE)
            itemPrice.setText(inventory.itemPrice.toString(), TextView.BufferType.SPANNABLE)
            itemQuantity.setText(inventory.quantityInStock.toString(), TextView.BufferType.SPANNABLE)
            saveAction.setOnClickListener { 
                updateInventory()
            }
        }
    }

    private fun updateInventory() {
        binding.apply {
            inventoryViewModel.updateInventory(
                id = safeArgs.itemId,
                itemName = itemName.text.toString(),
                itemPrice = itemPrice.text.toString(),
                quantityInStock = itemQuantity.text.toString()
            )
        }
        findNavController().navigate(EditorFragmentDirections.actionEditorFragmentToListFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}