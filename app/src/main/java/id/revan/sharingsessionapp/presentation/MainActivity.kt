package id.revan.sharingsessionapp.presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.revan.sharingsessionapp.R
import id.revan.sharingsessionapp.R.layout
import id.revan.sharingsessionapp.data.entity.Banner
import id.revan.sharingsessionapp.data.entity.NewsCategory
import id.revan.sharingsessionapp.di.Injector
import id.revan.sharingsessionapp.di.ViewModelFactory
import id.revan.sharingsessionapp.external.DataState.Error
import id.revan.sharingsessionapp.external.DataState.Loading
import id.revan.sharingsessionapp.external.DataState.Success
import id.revan.sharingsessionapp.external.hideView
import id.revan.sharingsessionapp.external.showView
import id.revan.sharingsessionapp.presentation.MainIntent.FetchBanner
import id.revan.sharingsessionapp.presentation.MainIntent.FetchNewsCategory
import id.revan.sharingsessionapp.presentation.adapter.BannerAdapter
import id.revan.sharingsessionapp.presentation.adapter.NewsCategoryAdapter
import id.revan.sharingsessionapp.presentation.adapter.ShimmerBannerAdapter
import id.revan.sharingsessionapp.presentation.adapter.ShimmerNewsCategoryAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var swrMain: SwipeRefreshLayout
    private lateinit var rvNewsCategory: RecyclerView
    private lateinit var newsCategoryAdapter: NewsCategoryAdapter
    private lateinit var rvShimmerNewsCategory: RecyclerView
    private lateinit var shimmerNewsCategoryAdapter: ShimmerNewsCategoryAdapter
    private lateinit var llError: LinearLayoutCompat
    private lateinit var btnTryAgain: Button

    private lateinit var rvBanner: RecyclerView
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var rvShimmerBanner: RecyclerView
    private lateinit var shimmerBannerAdapter: ShimmerBannerAdapter
    private lateinit var llBannerError: LinearLayoutCompat
    private lateinit var btnBannerTryAgain: Button
    
    private val newsCategories = mutableListOf<NewsCategory>()
    private val banners = mutableListOf<Banner>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                Injector.provideMainUseCase()
            )
        )[MainViewModel::class.java]
        observeViewModel()
        setupView()
        setupViewListener()

        mainViewModel.setStateEvent(FetchBanner(3))
        mainViewModel.setStateEvent(FetchNewsCategory)
    }

    private fun setupView() {
        swrMain = findViewById(R.id.swrMain)
        rvNewsCategory = findViewById(R.id.rvNewsCategory)
        newsCategoryAdapter = NewsCategoryAdapter(newsCategories)
        rvNewsCategory.run {
            adapter = newsCategoryAdapter
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(this@MainActivity, 3, RecyclerView.VERTICAL, false)
        }

        rvShimmerNewsCategory = findViewById(R.id.rvShimmerNewsCategory)
        shimmerNewsCategoryAdapter =
            ShimmerNewsCategoryAdapter(listOf(Any(), Any(), Any(), Any(), Any(), Any(), Any(), Any(), Any()))
        rvShimmerNewsCategory.run {
            adapter = shimmerNewsCategoryAdapter
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(this@MainActivity, 3, RecyclerView.VERTICAL, false)
        }

        llError = findViewById(R.id.llError)
        btnTryAgain = findViewById(R.id.btnTryAgain)

        rvBanner = findViewById(R.id.rvBanner)
        bannerAdapter = BannerAdapter(banners)
        rvBanner.run {
            adapter = bannerAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
        }

        rvShimmerBanner = findViewById(R.id.rvShimmerBanner)
        shimmerBannerAdapter = ShimmerBannerAdapter(listOf(Any(), Any(), Any()))
        rvShimmerBanner.run {
            adapter = shimmerBannerAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
        }

        llBannerError = findViewById(R.id.llBannerError)
        btnBannerTryAgain = findViewById(R.id.btnBannerTryAgain)
    }

    private fun setupViewListener() {
        swrMain.setOnRefreshListener {
            mainViewModel.setStateEvent(FetchBanner(3))
            mainViewModel.setStateEvent(FetchNewsCategory)
        }
        btnTryAgain.setOnClickListener {
            mainViewModel.setStateEvent(FetchNewsCategory)
        }
        btnBannerTryAgain.setOnClickListener {
            mainViewModel.setStateEvent(FetchBanner(3))
        }
    }

    private fun observeViewModel() {
        mainViewModel.newsCategoryState.observe(this, {
            when (it) {
                is Loading -> {
                    rvShimmerNewsCategory.showView()
                    llError.hideView()
                    rvNewsCategory.hideView()
                }
                is Success -> {
                    swrMain.isRefreshing = false
                    rvShimmerNewsCategory.hideView()
                    llError.hideView()
                    with(newsCategories) {
                        clear()
                        addAll(it.data)
                    }
                    newsCategoryAdapter.notifyDataSetChanged()
                    rvNewsCategory.showView()
                }
                is Error -> {
                    swrMain.isRefreshing = false
                    rvShimmerNewsCategory.hideView()
                    rvNewsCategory.hideView()
                    llError.showView()
                }
            }
        })
        mainViewModel.bannerState.observe(this, {
            when (it) {
                is Loading -> {
                    rvShimmerBanner.showView()
                    llBannerError.hideView()
                    rvBanner.hideView()
                }
                is Success -> {
                    rvShimmerBanner.hideView()
                    llBannerError.hideView()
                    with(banners) {
                        clear()
                        addAll(it.data)
                    }
                    bannerAdapter.notifyDataSetChanged()
                    rvBanner.showView()
                }
                is Error -> {
                    rvShimmerBanner.hideView()
                    llBannerError.showView()
                    rvShimmerBanner.hideView()
                }
            }
        })
    }
}