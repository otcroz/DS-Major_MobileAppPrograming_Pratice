package com.example.ch11_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.ch11_12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
        // 프레그먼트들의 대한 배열
        val fragments: List<Fragment>
        init {
            fragments = listOf(Fragment1(), Fragment2(), Fragment3()) // 3개의 프레그먼트 등록
        }

        override fun getItemCount(): Int { // 등록된 프레그먼트의 개수
            //TODO("Not yet implemented")
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment { // 전달받은(position 번째에 해당하는) 프레그먼트 리턴
            //TODO("Not yet implemented")
            return fragments[position]
        }
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    lateinit var toggle : ActionBarDrawerToggle // 토글 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 툴바를 액션바 형태로 적용하기
        setSupportActionBar(binding.toolbar)

        // fragment 적용
        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
            // 1) fragment 추가
            //var fragment = Fragment1()
            //transaction.add(R.id.fragmentView, fragment) // activity_main > fragmentView 자리에 fragment 추가
            //transaction.commit()
        // transaction.addToBackStack()

        // ViewPager2 추가
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL //가로 방향으로 프레그먼트 넘기기
        binding.viewpager.adapter = MyFragmentAdapter(this)

        // 토글 적용하기
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close) // 액티비티, xml, string
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 액션바 추가
        toggle.syncState() // 토글 동기화
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 액션바에서 메뉴 생성
            //1) 하드코딩으로 작성
            //val menuItem1 : MenuItem? = menu?.add(0,0,0, "메뉴1")
            //val menuItem2 : MenuItem? = menu?.add(0,1,0, "메뉴2")

            //2) menu xml 파일 만들기: menuInflater
            menuInflater.inflate(R.menu.menu_main, menu)

        //2. 액션 뷰: 검색 메뉴에 대한 코드
        val menuSearch = menu?.findItem(R.id.menu_search) // 메뉴 바인딩
        val searchView = menuSearch?.actionView as SearchView // 액션 뷰 가져오기
 
        searchView.setOnQueryTextListener (object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 변경 이벤트
                Log.d("test_app", "검색어 변경~!")

                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드 검색 버튼을 클릭한 순간의 이벤트
                Log.d("test_app", "버튼 클릭")
                return true
            }
        } ) 

        return super.onCreateOptionsMenu(menu)

    }

    // 토글 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 토글에 대한 확인: 아이템 선택에 대한 확인
        if (toggle.onOptionsItemSelected(item)) return true

        when(item.itemId){
            R.id.menu1 -> {
                //binding.tv1.setTextColor(Color.BLUE)
                true //리턴값에 대한 값을 가져야 한다. -> true로 설정
            }
            R.id.menu2 -> {
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}