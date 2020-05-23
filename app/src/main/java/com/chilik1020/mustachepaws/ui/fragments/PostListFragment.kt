package com.chilik1020.mustachepaws.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.Screens
import com.chilik1020.mustachepaws.ui.adapters.PostListAdapter
import com.chilik1020.mustachepaws.presenters.PostListPresenterImpl
import com.chilik1020.mustachepaws.ui.base.BackButtonListener
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.views.PostListView
import com.chilik1020.mustachepaws.viewstates.PostListViewState
import kotlinx.android.synthetic.main.fragment_post_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.Router
import toothpick.ktp.KTP
import javax.inject.Inject

class PostListFragment : MvpAppCompatFragment(), PostListView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var postListPresenterImpl : PostListPresenterImpl

    private val postListAdapter = PostListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        KTP.openScope(APPSCOPE).inject(this)
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvPostList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = postListAdapter
        }

        ivCreatePost.setOnClickListener { navigateToCreatePostFragment() }
    }

    override fun onResume() {
        super.onResume()
        postListPresenterImpl.fetchPosts()
    }

    override fun render(state: PostListViewState) {
        when(state) {
            is PostListViewState.PostListLoadingState -> {
                pbFetchPostsLoading.visibility = View.VISIBLE
            }

            is PostListViewState.PostListLoadedState -> {
                pbFetchPostsLoading.visibility = View.GONE
                postListAdapter.setData(state.posts)
            }

            is PostListViewState.PostListErrorState -> {
                pbFetchPostsLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToCreatePostFragment() {
        router.navigateTo(Screens.CreatePostScreen())
    }

    override fun onBackPressed() {
        router.exit()
    }
}
