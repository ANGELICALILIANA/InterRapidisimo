package com.example.interrapidisimo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.interrapidisimo.adapter.EntityAdapter
import com.example.interrapidisimo.data.TableData
import com.example.interrapidisimo.databinding.ShowEntitiesBinding
import com.example.interrapidisimo.viewmodel.TableEntityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowEntitiesFragment : Fragment() {

    private var _binding: ShowEntitiesBinding? = null
    private val binding get() = _binding!!
    private val entityViewModel by viewModels<TableEntityViewModel>()
    private var tableData: List<TableData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShowEntitiesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entityViewModel.getAllInformationTables()
        observer()
        filterByName()
    }

    private fun observer(){
        entityViewModel.getContentTable().observe(viewLifecycleOwner) {
            tableData = it
            binding.tableRecyclerView.adapter = EntityAdapter(tableData)
        }
    }

    /**
     * Filtra y actualiza la vista según cambios en el campo de autocomplete para mostrar nombres de tablas con nombres coincidentes.
     */
    private fun filterByName(){
        binding.nameAutocomplete.addTextChangedListener {
            val filterName = tableData.filter { data -> data.name.uppercase().contains(it.toString().uppercase()) }
            binding.tableRecyclerView.adapter = EntityAdapter(filterName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): ShowEntitiesFragment {
            return ShowEntitiesFragment().apply {
                arguments = Bundle()
            }
        }

    }

}