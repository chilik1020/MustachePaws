package com.chilik1020.mustachepaws.views

import com.chilik1020.mustachepaws.viewstates.CreatePostViewState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CreatePostView : MvpView {
    fun render(state : CreatePostViewState)
}