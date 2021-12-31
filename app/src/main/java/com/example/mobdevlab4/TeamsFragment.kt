package com.example.mobdevlab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mobdevlab4.databinding.FragmentTeamsBinding
import kotlin.random.Random.Default.nextInt

class TeamsFragment : Fragment() {
    private lateinit var binding: FragmentTeamsBinding
    private val args by navArgs<TeamsFragmentArgs>()
    private val adapter = TeamsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val team1 = getString(R.string.team_1)
        val team2 = getString(R.string.team_2)
        val list = args.members.toList().shuffled()
        val limit = list.size / 2 + nextInt(1 + list.size % 2)
        list.forEachIndexed { index, it -> it.team = if (index < limit) team1 else team2 }
        adapter.submitList(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.adapter = adapter
        binding.recycler.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        return binding.root
    }
}