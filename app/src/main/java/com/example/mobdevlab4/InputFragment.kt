package com.example.mobdevlab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mobdevlab4.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    private lateinit var binding: FragmentInputBinding
    private lateinit var storage: MemberStorage
    private val adapter = InputAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = MemberStorage(requireContext())
        adapter.submitList(storage.loadMembers())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.fragment = this
        binding.adapter = adapter
        binding.recycler.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        return binding.root
    }

    fun addMember(name: String) {
        if (name != "") {
            if (!adapter.currentList.any{it.name == name}) {
                val list = adapter.currentList + Member(name)
                adapter.submitList(list)
                storage.saveMembers(list)
            } else {
                val text = getString(R.string.repeat_name)
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            }
        } else {
            val text = getString(R.string.empty_name)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun updateMember(name: String) {
        val selected = adapter.currentList.mapIndexedNotNull { index, member ->
            if (!member.checked) null
            else index to member
        }
        if (selected.size == 1 && selected.first().second.name != name) {
            selected.first().second.name = name
            adapter.notifyItemChanged(selected.first().first)
            storage.saveMembers(adapter.currentList)
        } else {
            val text = getString(R.string.select_one_member)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteMember() {
        val selected = adapter.currentList.filter { it.checked }

        if (selected.isEmpty()) {
            val text = getString(R.string.empty_delete)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.confirm_clear))
                .setNegativeButton(getString(R.string.no), null)
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    val list = adapter.currentList.filter { it !in selected }
                    adapter.submitList(list)
                    storage.saveMembers(list)
                }.show()
        }
    }

    fun raffle() {
        val array = adapter.currentList.filter { it.checked }.toTypedArray()
        findNavController().navigate(InputFragmentDirections.displayTeams(array))
    }
}