package com.example.bookhub.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.bookhub.R
import com.example.bookhub.adapter.DashboardRecyclerAdapter
import com.example.bookhub.model.Book


class DashboardFragment : Fragment() {
    lateinit var recyclerDashboard:RecyclerView

    lateinit var layoutManager: LayoutManager

    val  bookList= arrayListOf<String>("P.S I Love You","The Rudest Book ever","The Monk who Sold his ferrari",
        "War and Peace","Beauty and the Beast","A place called here")


  lateinit var recyclerAdapter:DashboardRecyclerAdapter


    val bookInfoList= arrayListOf<Book>(
        Book("P.S I Love you","Cecelia Ahern","299","4.5",R.drawable.ps_ily),
        Book("The rudest Book Ever","Shwetab gangwar","250","4.8",R.drawable.rudest),
        Book("War and Peace","Leo Tolstoy","306","4.4",R.drawable.war_and_peace),
        Book("The Monk Who Sold his ferrari","Robin Sharma","390","4.2",R.drawable.monk),
        Book("Beauty and the beast","Gabrielle-Suzanne de Villeneuve","300","3.0",R.drawable.beauty),
        Book(" A place called here","cecelia Ahren","290","3.6",R.drawable.here)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_dashboard, container, false)



        recyclerDashboard=view.findViewById(R.id.recyclerViewDashboard)
        layoutManager=LinearLayoutManager(activity)
        recyclerAdapter=DashboardRecyclerAdapter(activity as Context,bookInfoList)
        recyclerDashboard.adapter=recyclerAdapter
        recyclerDashboard.layoutManager= layoutManager

        recyclerDashboard.addItemDecoration(DividerItemDecoration(recyclerDashboard.context,
            (layoutManager as LinearLayoutManager).orientation))
        return view
    }



}
