package com.chilik1020.mustachepaws.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.chilik1020.mustachepaws.viewstates.PostListViewState

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface PostListView : MvpView {

    fun render(state: PostListViewState)
}