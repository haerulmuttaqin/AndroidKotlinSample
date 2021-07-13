package co.id.cpn.navsample.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.id.cpn.entity.InventoryItem
import co.id.cpn.entity.getFormattedPrice
import co.id.cpn.navsample.databinding.FragmentDetailBinding
import co.id.cpn.navsample.presentation.InventoryViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: DetailFragmentArgs by navArgs()
    lateinit var inventoryItem: InventoryItem
    
    lateinit var inventory: InventoryItem

    private val inventoryViewModel: InventoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = safeArgs.itemId
        Log.i("DetailFragment", "safeArgs: $id")
        inventoryViewModel.getInventory(id).observe(viewLifecycleOwner, { seletedItem ->
            inventory = seletedItem
            bind(inventory)
        })
    }

    private fun bind(inventory: InventoryItem) {
        binding.apply { 
            itemName.text = inventory.itemName
            itemPrice.text = inventory.getFormattedPrice()
            itemCount.text = inventory.quantityInStock.toString()
            sellItem.setOnClickListener { inventoryViewModel.sellInventory(inventory) }
            deleteItem.setOnClickListener { showConfirmationDialog() }
            editItem.setOnClickListener { editInventory() }
        }
    }

    private fun editInventory() {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToEditorFragment(inventory.id))
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage("Are you sure want to delete?")
            .setCancelable(false)
            .setNegativeButton("No") { _,_ -> }
            .setPositiveButton("Yes") { _,_ ->
                inventoryViewModel.deleteInventory(inventory)
                findNavController().navigateUp()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}