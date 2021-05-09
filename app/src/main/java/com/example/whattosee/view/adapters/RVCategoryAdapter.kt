import com.example.whattosee.view.adapters.RVFilmAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whattosee.databinding.RvCategoryItemBinding
import com.example.whattosee.model.Category

class RVCategoryAdapter(
    private val context: Context,
    private val onCategoryClick: OnCategoryClick? = null,
    private val onFilmClickListener: RVFilmAdapter.OnFilmClickListener? = null
) : RecyclerView.Adapter<RVCategoryAdapter.ViewHolder>() {

    var categoryList: List<Category> = listOf()
    private var _binding: RvCategoryItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVCategoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        _binding = RvCategoryItemBinding.inflate(layoutInflater)
        val view = binding.root
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) = with(binding) {
            val adapter = RVFilmAdapter(context, onFilmClickListener).apply {
                films = category.films
                rvFilms.adapter = this
            }
            title.apply {
                text = category.title
                setOnClickListener { onCategoryClick?.onClick(category) }
            }
        }
    }

    interface OnCategoryClick {
        fun onClick(category: Category)
    }
}
