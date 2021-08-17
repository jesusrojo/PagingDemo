package com.jesusrojo.pagingdemo.ghrepos.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.DetailsLayoutBinding
import com.jesusrojo.pagingdemo.ghrepos.MainGHReposActivity
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHRepo
import com.jesusrojo.pagingdemo.ghrepos.presentation.hilt.GlideApp

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GHRepoDetailsFragment : Fragment() {

    private lateinit var binding: DetailsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: GHRepoDetailsFragmentArgs by navArgs()
        val ghRepo = args.selectedData
        initUi(ghRepo)
        (activity as MainGHReposActivity).setUpMenuOptionsDetailsFragment()
    }

    private fun initUi(ghRepo: GHRepo) {
        val myTitle = activity?.resources?.getString(R.string.details)
        (activity as MainGHReposActivity).title = myTitle

        val textUi = prepareFullText(ghRepo)
        binding.textViewExplanationDetails.text = textUi

        GlideApp.with(binding.imageViewAvatarDetails.context)
            .load(ghRepo.avatarUrl)
            .into(binding.imageViewAvatarDetails)
    }

    private fun prepareFullText(ghRepo: GHRepo): String {
        val activity = activity ?: return ""
        val resources = activity.resources
        val sb = StringBuilder()
        sb.append(resources.getString(R.string.details))
            .append(":\n")
            .append(ghRepo.toString())
            .append("\n")
        return sb.toString()
    }
}