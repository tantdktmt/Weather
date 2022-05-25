package com.nab.weather.forecast.presentation.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nab.weather.forecast.BR
import com.nab.weather.forecast.databinding.ItemDailyForecastBinding
import com.nab.weather.forecast.databinding.ItemDividerBinding
import com.nab.weather.forecast.presentation.model.BaseListModel
import com.nab.weather.forecast.presentation.model.DailyForecastModel

class ForecastAdapter(
) : ListAdapter<BaseListModel, RecyclerView.ViewHolder>(itemDiff) {

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<BaseListModel>() {

            override fun areItemsTheSame(
                oldItem: BaseListModel,
                newItem: BaseListModel
            ) = if (oldItem is DailyForecastModel && newItem is DailyForecastModel) {
                oldItem.date == newItem.date
            } else true

            override fun areContentsTheSame(
                oldItem: BaseListModel,
                newItem: BaseListModel
            ) = if (oldItem is DailyForecastModel && newItem is DailyForecastModel) {
                oldItem == newItem
            } else true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseListModel.ViewType.FORECAST -> {
                val binding = ItemDailyForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ForecastViewHolder(binding)
            }
            BaseListModel.ViewType.DIVIDER -> {
                val binding = ItemDividerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DividerViewHolder(binding)
            }
            else -> {
                val binding = ItemDailyForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ForecastViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastViewHolder -> holder.bindData(getItem(position) as DailyForecastModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private inner class ForecastViewHolder(private val binding: ItemDailyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: DailyForecastModel) = with(binding) {
            setVariable(BR.item_data, item)
            executePendingBindings()
        }
    }

    private inner class DividerViewHolder(binding: ItemDividerBinding) :
        RecyclerView.ViewHolder(binding.root)
}
