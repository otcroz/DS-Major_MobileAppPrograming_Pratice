package com.example.ch11_12

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11_12.databinding.Fragment1Binding
import com.example.ch11_12.databinding.ItemRecyclerviewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */

class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int { // 아이템 개수 리턴
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { // 뷰 홀더 준비
        // layoutInflator는 Activity에서만 적용 => Fragment에서는 함수를 통해 만들어준다: LayoutInflater.from(parent.context)
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { // 뷰 홀더 뷰에 데이터 출력: 바인딩
        val binding = (holder as MyViewHolder).binding
        binding.itemTv.text = datas[position]
    }
}

class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 리사이클러 뷰에 넣을 data String 생성
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("item $i")
        }

        //리사이클러 뷰 적용하기
        val binding = Fragment1Binding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager // 기본: vertical로 설정되어 있음
        binding.recyclerView.adapter = MyAdapter(datas)
        //binding.recyclerView.addItemDecoration(MyDecoration(activity as Context)) // 데코레이션

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}