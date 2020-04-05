package com.example.the_child_specialist

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

class Home : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment,container,false)

        val imageSlider = view.findViewById<ImageSlider>(R.id.slider)
        val slideModels = ArrayList<SlideModel>()
        slideModels.add(SlideModel(R.drawable.image_1))
        slideModels.add(SlideModel(R.drawable.image_2))
        slideModels.add(SlideModel(R.drawable.image_3))
        slideModels.add(SlideModel(R.drawable.image_5))
        slideModels.add(SlideModel(R.drawable.image_6))
        slideModels.add(SlideModel(R.drawable.image_7))
        slideModels.add(SlideModel(R.drawable.image_8))
        slideModels.add(SlideModel(R.drawable.image_9))
        slideModels.add(SlideModel(R.drawable.image_10))

        imageSlider.setImageList(slideModels, true)






    return view
    }
}