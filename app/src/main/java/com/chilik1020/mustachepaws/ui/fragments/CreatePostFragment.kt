package com.chilik1020.mustachepaws.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.chilik1020.mustachepaws.BuildConfig

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.Screens
import com.chilik1020.mustachepaws.presenters.CreatePostPresenterImpl
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.utils.LOG_TAG
import com.chilik1020.mustachepaws.utils.createImageFile
import com.chilik1020.mustachepaws.views.CreatePostView
import com.chilik1020.mustachepaws.viewstates.CreatePostViewState
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.fragment_create_post.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.Router
import toothpick.ktp.KTP
import java.io.File
import javax.inject.Inject

const val REQUEST_IMAGE_CAPTURE = 1

class CreatePostFragment : MvpAppCompatFragment(), CreatePostView {

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: CreatePostPresenterImpl

    lateinit var photoOriginalFile: File
    lateinit var photoCroppedFile: File

    lateinit var photoOriginalUri: Uri
    lateinit var photoCroppedUri: Uri


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        KTP.openScope(APPSCOPE).inject(this)
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        ivPhotoCreatePostF.setOnClickListener { onClickImageCapture() }
        btnCreatePost.setOnClickListener {
            presenter.createPost(
                tietDescriptionCreatePostF.text.toString(),
                photoCroppedFile.absolutePath) }
    }

    private fun onClickImageCapture() {
        createImageFiles()
        startImageCapture()
    }

    private fun createImageFiles() {
        photoOriginalFile = createImageFile(requireContext(),"original")
        photoCroppedFile = createImageFile(requireContext(), "cropped")

        if (!photoOriginalFile.exists() || !photoCroppedFile.exists())
            return

        photoCroppedUri = Uri.fromFile(photoCroppedFile)
        photoOriginalUri = FileProvider.getUriForFile(
            requireContext(),
            "${BuildConfig.APPLICATION_ID}.provider",
            photoOriginalFile
        )

        Log.d(LOG_TAG, "ORIGINAL PATH: ${photoOriginalFile.absolutePath}")
        Log.d(LOG_TAG, "CROPPED PATH: ${photoCroppedFile.absolutePath}")
        Log.d(LOG_TAG, "ORIGINAL URI: $photoOriginalUri")
        Log.d(LOG_TAG, "CROPPED URI: $photoCroppedUri")
    }

    private fun startImageCapture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoOriginalUri)
        }
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun startUCropActivity() {
        UCrop.of(photoOriginalUri, photoCroppedUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1080, 1080)
            .start(requireActivity(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(LOG_TAG, "ON ACTIVITY REQUEST CODE : $requestCode")
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    Glide.with(this).load(photoOriginalUri).into(ivPhotoCreatePostF)
                    startUCropActivity()
                }

                UCrop.REQUEST_CROP -> {
                    Glide.with(this).load(photoCroppedUri).into(ivPhotoCreatePostF)
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val throwable = data?.let { UCrop.getError(it) }
            Log.d(LOG_TAG, throwable?.message.toString())
        }
    }

    override fun render(state: CreatePostViewState) {
        when(state) {
            is CreatePostViewState.PostCreateLoadingState -> {
                pbCreatePostLoading.visibility = View.VISIBLE
            }

            is CreatePostViewState.PostCreatedState -> {
                pbCreatePostLoading.visibility = View.GONE
                Toast.makeText(activity, "Post created", Toast.LENGTH_LONG).show()
                router.replaceScreen(Screens.PostListScreen())
            }

            is CreatePostViewState.PostCreateErrorState -> {
                pbCreatePostLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
