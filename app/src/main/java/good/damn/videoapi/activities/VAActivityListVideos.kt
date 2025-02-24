package good.damn.videoapi.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import good.damn.videoapi.adapters.videos.VAAdapterVideos
import good.damn.videoapi.adapters.videos.VAListenerOnSelectVideo
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.viewModels.VAViewModelVideoList
import good.damn.videoapi.extensions.toast

@AndroidEntryPoint
class VAActivityListVideos
: AppCompatActivity(),
VAListenerOnSelectVideo {

    companion object {
        private val TAG = VAActivityListVideos::class.simpleName
    }

    private val mViewModelVideoList: VAViewModelVideoList
        by viewModels()

    private val mListVideos: MutableList<
        VAModelVideoListItem
    > = mutableListOf()

    private val mAdapter = VAAdapterVideos(
        mListVideos,
        onSelectVideo = this@VAActivityListVideos
    )

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        val layoutSwipeRefresh = SwipeRefreshLayout(
            context
        ).apply {
            setBackgroundColor(0)

            setOnRefreshListener {
                getListVideosAsync()
                isRefreshing = false
            }
        }

        RecyclerView(
            context
        ).apply {
            setBackgroundColor(0)

            adapter = mAdapter

            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            layoutSwipeRefresh.addView(
                this
            )
        }

        setContentView(
            layoutSwipeRefresh
        )

        getListVideosAsync()
    }

    override fun onStop() {
        mViewModelVideoList.apply {
            getList.removeObservers(
                this@VAActivityListVideos
            )

            getListDao.removeObservers(
                this@VAActivityListVideos
            )
        }
        super.onStop()
    }

    override fun onSelectVideo(
        video: VAModelVideoListItem
    ) {
        startActivity(
            Intent(
                this,
                VAActivityVideo::class.java
            ).apply {
                putExtra(
                    VAActivityVideo.KEY_EXTRA_ID,
                    video.id
                )
            }
        )
    }



    private inline fun getListVideosAsync() = mViewModelVideoList.run {
        getList.removeObservers(
            this@VAActivityListVideos
        )

        getListDao.removeObservers(
            this@VAActivityListVideos
        )

        getList.observe(
            this@VAActivityListVideos
        ) {
            Log.d(TAG, "getListVideosAsync: getList")
            when (it) {
                is VAStateResponse.Error -> {
                    toast(
                        "Error: ${it.error}"
                    )
                }

                is VAStateResponse.Loading -> {
                    toast("Updating")
                }

                is VAStateResponse.Success -> {
                    it.data?.apply {
                        add(this)
                    }
                }
            }
        }

        getListDao.observe(
            this@VAActivityListVideos
        ) {
            Log.d(TAG, "getListVideosAsync: getListDao: ${mListVideos.isNotEmpty()}")
            if (mListVideos.isNotEmpty()) {
                mListVideos.clear()
                mAdapter.notifyDataSetChanged()
            }

            mListVideos.addAll(it)
            mAdapter.notifyItemRangeInserted(
                0,
                mListVideos.size
            )
        }
    }
}