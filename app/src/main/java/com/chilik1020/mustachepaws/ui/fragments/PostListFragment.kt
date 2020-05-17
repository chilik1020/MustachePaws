package com.chilik1020.mustachepaws.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.ui.adapters.PostListAdapter
import com.chilik1020.mustachepaws.presenters.PostListPresenter
import com.chilik1020.mustachepaws.views.PostListView
import com.chilik1020.mustachepaws.viewstates.PostListViewState
import kotlinx.android.synthetic.main.fragment_post_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class PostListFragment : MvpAppCompatFragment(), PostListView {

    @InjectPresenter
    lateinit var postListPresenter : PostListPresenter

    private val postListAdapter = PostListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerViewPostList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = postListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        postListPresenter.fetchPosts()
    }

    override fun render(state: PostListViewState) {
        when(state) {
            is PostListViewState.PostListLoadingState -> {
                progressBarPostListLoading.visibility = View.VISIBLE
            }

            is PostListViewState.PostListLoadedState -> {
                progressBarPostListLoading.visibility = View.GONE
                postListAdapter.setData(state.posts)
            }

            is PostListViewState.PostListErrorState -> {
                progressBarPostListLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
