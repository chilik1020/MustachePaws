package com.chilik1020.mustachepaws.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import com.chilik1020.mustachepaws.presenters.CreatePostPresenterImpl
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.utils.LOG_TAG
import com.chilik1020.mustachepaws.views.CreatePostView
import com.chilik1020.mustachepaws.viewstates.CreatePostViewState
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.fragment_create_post.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.Router
import toothpick.ktp.KTP
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

const val REQUEST_IMAGE_CAPTURE = 1

class CreatePostFragment : MvpAppCompatFragment(), CreatePostView {

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: CreatePostPresenterImpl

    lateinit var imageFilePath: String
    lateinit var imageCroppedFilePath: String

    lateinit var photoOriginalUri: Uri
    lateinit var photoCroppedUri: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        KTP.openScope(APPSCOPE).inject(this)
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        ivPhotoCreatePostF.setOnClickListener { onClickImageCapture() }
        btnCreatePost.setOnClickListener { presenter.createPost(tietDescriptionCreatePostF.text.toString(),
                                                                imageCroppedFilePath) }
    }

    private fun onClickImageCapture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {  intent ->
            intent.resolveActivity(requireActivity().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile("original")
                } catch (ex: Exception) {
                    null
                }

                val photoCroppedFile: File? = try {
                    createImageFile("cropped")
                } catch (ex: Exception) {
                    null
                }
                Log.d(LOG_TAG, "Original: ${photoFile?.exists()}")
                Log.d(LOG_TAG, "Cropped: ${photoCroppedFile?.exists()}")

                photoCroppedFile?.also {
                    photoCroppedUri = Uri.fromFile(it)
//                    photoCroppedUri = FileProvider.getUriForFile(
//                        this,
//                        "${BuildConfig.APPLICATION_ID}.provider",
//                        it
//                    )
                    imageCroppedFilePath = it.absolutePath
                    Log.d(LOG_TAG, "CROPPED URI: ${it.absolutePath}")
                }

                photoFile?.also {
                    photoOriginalUri = FileProvider.getUriForFile(
                        requireContext(),
                        "${BuildConfig.APPLICATION_ID}.provider",
                        it
                    )
                    imageFilePath = it.absolutePath
                    Log.d(LOG_TAG, "ORIGINAL URI: ${it.absolutePath}")
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoOriginalUri)
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }


    private fun startUCropActivity() {
        UCrop.of(photoOriginalUri, photoCroppedUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1080, 1080)
            .start(requireActivity())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    startUCropActivity()
                    Glide.with(this).load(imageFilePath).into(ivPhotoCreatePostF)
                }

                UCrop.REQUEST_CROP -> {
                    data?.let {
                        UCrop.getOutput(it).also {
                            photoCroppedUri = it!!
                            Glide.with(this).load(photoCroppedUri).into(ivPhotoCreatePostF)
                        }
                    }
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val throwable = UCrop.getError(data!!)
            Log.d(LOG_TAG, throwable?.message)
        }
    }

    override fun render(state: CreatePostViewState) {
        when(state) {
            is CreatePostViewState.PostCreateLoadingState -> {
                pbCreatePostLoading.visibility = View.VISIBLE
            }

            is CreatePostViewState.PostCreatedState -> {
                pbCreatePostLoading.visibility = View.GONE

            }

            is CreatePostViewState.PostCreateErrorState -> {
                pbCreatePostLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createImageFile(prefix: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "img_${prefix}_${timeStamp}_"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }
}
