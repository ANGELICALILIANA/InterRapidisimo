package com.example.interrapidisimo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.interrapidisimo.R
import com.example.interrapidisimo.sharedpreferences.Verification
import com.example.interrapidisimo.databinding.CreateEntityBinding
import com.example.interrapidisimo.viewmodel.TableEntityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEntityFragment : Fragment() {

    private var _binding: CreateEntityBinding? = null
    private val binding get() = _binding!!
    private val entityViewModel by viewModels<TableEntityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateEntityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entityViewModel.createDataBase()
        listener()
        binding.viewTablesButton.isVisible = Verification(requireContext()).sharedPreferences.getBoolean(getString(R.string.database_created), false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listener(){
        binding.createTablesButton.setOnClickListener {
            if(!Verification(requireContext()).sharedPreferences.getBoolean(getString(R.string.database_created), false)){
                entityViewModel.loadTables()
                binding.viewTablesButton.isVisible = true
            }else{
                Toast.makeText(context, getString(R.string.created), Toast.LENGTH_SHORT).show()
            }
        }
        binding.viewTablesButton.setOnClickListener {
            replaceFragment(ShowEntitiesFragment.newInstance())
        }
    }

    /**
     * Reemplaza el fragmento necesario
     */
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }

    companion object {

        fun newInstance(): CreateEntityFragment {
            return CreateEntityFragment().apply {
                arguments = Bundle()
            }
        }

    }

}