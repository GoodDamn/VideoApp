package good.damn.videoapi.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import good.damn.videoapi.adapters.videos.VAAdapterVideos
import good.damn.videoapi.adapters.videos.VAListenerOnSelectVideo
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.state.VAStateVideoList
import good.damn.videoapi.arch.viewModels.VAViewModelVideoList
import good.damn.videoapi.extensions.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                val s = mListVideos.size
                mListVideos.clear()
                mAdapter.notifyItemRangeRemoved(
                    0,
                    s
                )
                getListAsync()
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

        getListAsync {

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

    private inline fun getListAsync(
        noinline success: (
            (List<VAModelVideoListItem>) -> Unit
        )? = null
    ) {
        mViewModelVideoList.getList.observe(
            this@VAActivityListVideos
        ) {
            if (it.error != null || it.data == null) {
                return@observe
            }

            mViewModelVideoList.add(
                it.data
            )
        }

        mViewModelVideoList.getListDao.observe(
            this@VAActivityListVideos
        ) {
            /*if (it.isLoading) {
                toast("Loading")
                return
            }

            if (state.error != null) {
                toast("Error: ${state.error}")
                return
            }*/

            success?.invoke(it)
        }
    }
}