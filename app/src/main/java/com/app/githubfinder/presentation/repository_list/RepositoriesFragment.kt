package com.app.githubfinder.presentation.repository_list

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.githubfinder.R
import com.app.githubfinder.databinding.FragmentRepositoryDetailBinding
import com.app.githubfinder.databinding.RepositoriesFragmentBinding
import com.app.githubfinder.domain.model.RepositoriesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesFragment: Fragment() {

    private val vm: RepositoriesViewModel by viewModels()

    private lateinit var binding: RepositoriesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.repositories_fragment, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RepositoriesFragmentBinding.inflate(layoutInflater)
        var mList: List<RepositoriesModel> = listOf()

        val et: EditText = view.findViewById(R.id.et)

        et.doAfterTextChanged {
            vm.getAllRepositories(et.text.toString())
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            vm.state.collect {
                mList = it.listOfRepositories
                val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(requireActivity())
                recyclerView.adapter = mAdapter(mList)
            }
        }
    }

    inner class mAdapter(private val reposModels: List<RepositoriesModel>): RecyclerView.Adapter<mAdapter.mViewHolder>() {

        inner class mViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.reposName)
            val language: TextView = itemView.findViewById(R.id.reposLanguage)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
            val item = LayoutInflater.from(parent.context).inflate(R.layout.repositories_item_view, parent, false)
            return mViewHolder(item)
        }

        override fun onBindViewHolder(holder: mViewHolder, position: Int) {
            try {
                holder.name.text = reposModels[position].name
                holder.language.text = reposModels[position].language

                holder.name.setOnClickListener {
                    showCustomAlertDialog(
                        name = reposModels[position].name,
                        html_url = reposModels[position].owner.html_url,
                        description = reposModels[position].description,
                        language = reposModels[position].language,
                        visibility = reposModels[position].visibility,
                        created_at = reposModels[position].created_at,
                        updated_at = reposModels[position].updated_at,
                        pushed_at = reposModels[position].pushed_at,
                        watchers = reposModels[position].watchers.toString(),
                    )
                }
            } catch (e: Exception) {
                Log.i("MyApp", "onBindViewHolder error ${e.localizedMessage}")
            }
        }

        override fun getItemCount(): Int = reposModels.size
    }

    fun showCustomAlertDialog(
        name: String,
        html_url: String,
        description: String?,
        language: String,
        visibility: String,
        created_at: String,
        updated_at: String,
        pushed_at: String,
        watchers: String
    ) {
        val dialogBinding = FragmentRepositoryDetailBinding.inflate(layoutInflater)

        dialogBinding.name.text = "Project name: $name"
        dialogBinding.htmlUrl.text = "URL: $html_url"
        dialogBinding.description.text = "Description: $description"
        dialogBinding.language.text = "Language: $language"
        dialogBinding.visibility.text = "Visibility: $visibility"
        dialogBinding.createdAt.text = "Created: $created_at"
        dialogBinding.updatedAt.text = "Updated: $updated_at"
        dialogBinding.pushedAt.text = "Pushed: $pushed_at"
        dialogBinding.watchers.text = "Wathcers: $watchers"

        val customTitle = layoutInflater.inflate(R.layout.custom_title, null)


        val dialog = AlertDialog.Builder(requireContext())
            .setIcon(android.R.drawable.ic_menu_info_details)
            .setCancelable(true)
            .setCustomTitle(customTitle)
            .setView(dialogBinding.root)
            .create()
        dialog.show()
    }
}