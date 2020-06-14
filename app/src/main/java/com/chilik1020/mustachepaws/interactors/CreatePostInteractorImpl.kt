package com.chilik1020.mustachepaws.interactors

import android.util.Log
import com.chilik1020.mustachepaws.models.data.PostVO
import com.chilik1020.mustachepaws.models.repository.PostRepository
import com.chilik1020.mustachepaws.utils.LOG_TAG
import com.chilik1020.mustachepaws.utils.getMessageFromThrowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class CreatePostInteractorImpl : CreatePostInteractor {

    @Inject
    lateinit var repository: PostRepository

    override fun createPost(description: String, image: String,
        listener: CreatePostInteractor.OnPostCreatedListener
    ) {
        val descriptionRB = RequestBody.create(MediaType.parse("text/plain"), description)
        val creatorUsernameRB = RequestBody.create(MediaType.parse("text/plain"), "chilik1020")

        val imageFile = File(image)

        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
        val imageMultiPart = MultipartBody.Part.createFormData("image", imageFile.name, fileRequestBody)

        val subscribe =
            repository.createPostWithImage(descriptionRB, creatorUsernameRB, imageMultiPart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { listener.onSuccess(it) },
                { listener.onError(getMessageFromThrowable(it)) })
    }
}